package proyectofinal

import grails.converters.JSON

class ApiController {
    def index() {
        //
    }

    def list_pending_orders = {
        def given = [ : ]
        given[ 'items' ] = Sale.findByGiven( false )
        return given as JSON
    }

    def mark_as_given ( int id ){
        Sale sale = Sale.findById( id )

        def result= [ : ]

        sale.given = true;

        if( !sale.save( ) ){
            result[ 'status' ] = 'error';
            result[ 'message' ] = 'Error handling request!';
            render result as JSON
        }

        result[ 'status' ] = 'success';
        result[ 'message' ] = 'Marked as given!';
        render result as JSON
    }
}
