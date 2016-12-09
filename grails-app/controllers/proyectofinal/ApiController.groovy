package proyectofinal

import grails.converters.JSON

class ApiController {
    def index() {
        //
    }

    def list_pending_orders = {
        [ items: Sale.findByGiven( false ) ]
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
        result[ 'message' ] = 'Marked as done!';
        render result as JSON
    }
}
