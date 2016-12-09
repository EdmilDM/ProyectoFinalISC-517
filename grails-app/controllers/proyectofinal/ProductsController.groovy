package proyectofinal

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.OK

@Secured( [ "ROLE_USER" ] )
class ProductsController {
    def springSecurityService
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

    def create() {
        respond new Product(params)
    }

    def edit(Product product) {
        respond product
    }

    @Transactional
    def update(Product product) {
        if (product == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (product.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond user.errors, view:'edit'
            return
        }

        product.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'product.label', default: 'User'), user.id])
                redirect index()
            }
            '*'{ respond product, [status: OK] }
        }
    }

    @Transactional
    def save(Product product) {
        if (product == null) {
                transactionStatus.setRollbackOnly()
                notFound()
                return
            }

        if (product.hasErrors()) {
                transactionStatus.setRollbackOnly()
                respond product.errors, view:'create'
                return
        }

        product.save flush:true

        request.withFormat {
            form multipartForm {
                redirect index()
            }
            '*' { respond product, [status: CREATED] }
        }
    }
}
