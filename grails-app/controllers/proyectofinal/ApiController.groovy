package proyectofinal

import grails.converters.JSON

class ApiController {
    def index() {
        //
    }

    def list_pending_orders = {
        def given = [ : ]
        given[ 'items' ] = Sale.findByGiven( false )
        render given as JSON
    }

    def sales = {
        List data = [ : ]

        def sales = Sale.findAll( )
        Integer total_sales = 0;
        Integer pending = 0;
        Integer worked = 0;
        for( Sale s in sales ){
            total_sales++;

            if( s.given ) worked++;
            else pending++;
        }

        data[ 'total' ] = total
        data[ 'pending' ] = pending
        data[ 'worked' ] = worked

        render data as JSON
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
