package proyectofinal

import grails.plugin.springsecurity.annotation.Secured

@Secured( [ "ROLE_ADMIN", "ROLE_STORAGE" ] )
class ChartsController {

    def index() {
        List columns =  [ [ 'number', 'Total sales' ], [ 'number', 'Pending sales' ], [ 'number', 'Worked' ] ]
        List data = []
        def today = new Date( )
        def sales = Sale.findAllByDateCreatedBetween( today - 1, today )

        Integer total_sales = 0;
        Integer pending = 0;
        Integer worked = 0;
        for( Sale s in sales ){
            total_sales++;

            if( s.given ) worked++;
            else pending++;
        }

        data << [ total_sales, pending, worked ]

        render(view: "index", model: [ "columns" : columns, "data" : data ] )

    }
}
