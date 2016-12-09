package proyectofinal

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured

@Secured( [ "ROLE_USER" ] )
class ProcessController {

    def springSecurityService

    def index() {}

    def add_to_cart( Integer product_id, Integer quantity ) {
        User current_user = springSecurityService.currentUser
        def product = Product.findById( product_id )
        def result = [ : ]

        if( product.quantity_available < quantity ){
            result[ 'status' ] = 'error'
            result[ 'message' ] = "We don't have the neccesary amount available!"
            render result as JSON
        }

        result[ 'status' ] = 'success'
        result[ 'message' ] = "Product added to cart!"

        product.quantity_available -= quantity

        CartItem cartItem = new CartItem( quantity: quantity, total: product.price * Double.parseDouble( quantity + "" ), product: product, cart: current_user.cart )
        cartItem.save( )
        product.save( )

        render result as JSON
    }

    def delete_from_cart( Integer id ){

        User current_user = springSecurityService.currentUser
        def cartItem = CartItem.findById( id )

        def result = [ : ]

        if( cartItem.delete( ) ){
            result[ 'status' ] = 'error'
            result[ 'message' ] = "The cart wasn't updated!"
            render result as JSON
        }

        result[ 'status' ] = 'success'
        result[ 'message' ] = "Cart updated!"

        render result as JSON
    }

    def search( String q ){
        def lista = Contacto.findAllByEmailLikeOrNombreLike( q, q )
        if ( lista == null ) lista = []
        render lista as JSON
    }
}
