package proyectofinal

import com.paypal.api.payments.Links
import com.paypal.base.Constants
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import groovy.json.JsonSlurper

@Secured( [ "ROLE_USER" ] )
class ProductsController {
    def springSecurityService
    def NCFService
    def paypalService

    def index() {
        List<Product> products = Product.list( params )
        [ items: products , productCount: Product.count( ) ]
    }

    @Secured( [ "ROLE_ADMIN" ] )
    def manage( ){

    }

    def cart = {
        [ user: springSecurityService.currentUser ]
    }

    def view( ){
        Product product = Product.get( params.id )
        [ product: product ]
    }

    def process_cart( ) {
        User current_user = springSecurityService.currentUser

        //GET USER CART
        Cart cart = current_user.cart

        def total = 0.0;

        Sale sale = new Sale( );

        for ( CartItem item : cart.cartItems ){
            total += item.total
        }

        sale.given = false;
        sale.NCF = NCFService.get( current_user.is_entity ? "final" : "entity" )
        sale.user = current_user;
        sale.paypal_transaction_id = "";
        sale.total = total
        sale.save( )

        for ( CartItem item : cart.cartItems ){
            SaleItem si = new SaleItem( quantity: item.quantity, total: item.quantity * item.product.price, product: item.product, sale: sale );
            si.save( )
            item.delete( )
        }

        cart.cartItems.clear()
        cart.cartItems.removeAll()

        cart.save( )

        String clientId = grailsApplication.config.getProperty( 'paypal.sandbox.clientId' )
        String clientSecret = grailsApplication.config.getProperty( 'paypal.sandbox.clientSecret' )
        String endpoint = grailsApplication.config.getProperty( 'paypal.sandbox.endpoint' )

        Map sdkConfig = [(Constants.CLIENT_ID): clientId,
                         (Constants.CLIENT_SECRET): clientSecret,
                         (Constants.ENDPOINT): endpoint]
        def accessToken = paypalService.getAccessToken(clientId, clientSecret, sdkConfig)
        def apiContext = paypalService.getAPIContext(accessToken, sdkConfig)


        BigDecimal invoice_total = formatNumber(number: total, minFractionDigits: 2) as BigDecimal

        def details = paypalService.createDetails( subtotal: invoice_total )
        def amount = paypalService.createAmount(currency: 'USD', total: invoice_total, details: details)

        def transaction = paypalService.createTransaction( amount: amount, description: "Invoice #" + sale.id + "!", details: details)
        def transactions = [transaction]

        def payer = paypalService.createPayer(paymentMethod: 'paypal')
        def cancelUrl = "http://localhost:8080/products/cancel_payment?sale=" + sale.id
        def returnUrl = "http://localhost:8080/products/execute_payment?sale=" + sale.id

        def redirectUrls = paypalService.createRedirectUrls(cancelUrl: cancelUrl, returnUrl: returnUrl)

        def payment
        try {
            // create the paypal payment
            payment = paypalService.createPayment(
                    payer: payer, intent: 'sale',
                    transactionList: transactions,
                    redirectUrls: redirectUrls,
                    apiContext: apiContext)
        }
        catch (e) {
            System.out.println( e.message )
            System.out.println( params )
            flash.message = "Could not complete the transaction because: ${e.message ?: ''}"
            redirect controller: 'products', action: "sale", id: params.refId
            return
        }

        def approvalUrl = ""
        def retUrl = ""
        // retrieve links from returned paypal object
        for (Links links in payment?.links) {
            if (links?.rel == 'approval_url') {
                approvalUrl = links.href
            }
            if (links?.rel == 'return_url') {
                retUrl = links.href
            }
        }

        redirect url: approvalUrl ?: '/', method: 'POST'
    }

    def execute_payment() {

        String clientId = grailsApplication.config.getProperty( 'paypal.sandbox.clientId' )
        String clientSecret = grailsApplication.config.getProperty( 'paypal.sandbox.clientSecret' )
        String endpoint = grailsApplication.config.getProperty( 'paypal.sandbox.endpoint' )

        Map sdkConfig = [:] //= grailsApplication.config.paypal.sdkConfig//[mode: 'live']
        //sdkConfig['grant-type'] = "client_credentials"
        sdkConfig[Constants.CLIENT_ID] = clientId
        sdkConfig[Constants.CLIENT_SECRET] = clientSecret
        sdkConfig[Constants.ENDPOINT] = endpoint
        def accessToken = paypalService.getAccessToken(clientId, clientSecret, sdkConfig)
        def apiContext = paypalService.getAPIContext(accessToken, sdkConfig)
        //the paypal website will add params to the call to your app. Eg. PayerId, PaymentId
        // you will use the params to 'execute' the payment
        def paypalPayment = paypalService.createPaymentExecution(paymentId: params.paymentId, payerId: params.PayerID, apiContext)

        def map = new JsonSlurper().parseText(paypalPayment.toString())

        Sale sale = Sale.findById( params.sale );
        sale.paypal_transaction_id = params.paymentId;
        sale.save( );
        sendMail {
            to springSecurityService.currentUser.email
            text "test"
            subject "FacturaPDF"
        }
        System.out.println( map );

//        redirect url: "localhost:8080/products/sale/" + sale.id
        [ sale: sale ]

    }

    def cancel_paymanet = {
        //
    }

    def sale = {
        Sale sale = Sale.findById( params.id )
        [ sale: sale ]
    }

    def create() {
        respond new Product(params)
    }

    def edit(Product product) {
        respond product
    }

    @Transactional
    def update() {
        Product product = Product.findById( params.id )
        if (product == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        product.name = params.name
        product.price = Double.parseDouble( params.price )
        product.image = params.image
        product.quantity_available = Integer.parseInt( params.quantity_available )
        product.description = params.description

        product.save flush:true
        redirect url: "/products/edit/" + product.id
    }

    @Transactional
    def save() {
        Product product = new Product( )
        product.name = params.name
        product.price = Double.parseDouble( params.price )
        product.image = params.image
        product.quantity_available = Integer.parseInt( params.quantity_available )
        product.description = params.description

        product.save flush:true
        redirect url: "/products/edit/" + product.id
    }
}
