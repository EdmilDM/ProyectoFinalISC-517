package proyectofinal

import grails.plugin.springsecurity.annotation.Secured

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
}
