package proyectofinal

import grails.converters.JSON

class ApiController {
    def index() {
        //
    }

    def list_pending_orders = {
        def given = [ : ]
        given[ 'items' ] = []
        def sales = Sale.findAllByGiven( false )
        for( Sale sale : sales ){
            given[ 'items' ] << [
                    user: sale.user.username,
                    total: sale.total,
                    dateCreated: sale.dateCreated,
                    id: sale.id
            ]
        }

        render given as JSON
    }

    def sales = {
        def data = [ : ]

        def sales = Sale.findAll( )
        Integer total_sales = 0;
        Integer pending = 0;
        Integer worked = 0;
        for( Sale s in sales ){
            total_sales++;

            if( s.given ) worked++;
            else pending++;
        }

        data[ 'total' ] = total_sales
        data[ 'pending' ] = pending
        data[ 'worked' ] = worked

        render data as JSON
    }

    def mark_as_given ( int id ){
        Sale sale = Sale.findById( id )

        def result= [ : ]

        if( sale == null ){
            result[ 'status' ] = 'error';
            result[ 'message' ] = "The sale doesn't exists!";
            render result as JSON
        }


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
