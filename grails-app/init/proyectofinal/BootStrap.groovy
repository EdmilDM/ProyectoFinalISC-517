package proyectofinal

class BootStrap {

    def init = { servletContext ->

        Cart cart = new Cart( )
        cart.save( )
        User adminUser = new User(username: 'admin', cart: cart,
                        password: '12345', email: 'admin@local', complete_name: 'Super Administrator').save( failOnError: true, flush: true  )


        Role adminRole = new Role( authority: 'ROLE_ADMIN' )

        UserRole.create adminUser, adminRole

        Role userRole = new Role( authority: 'ROLE_USER' )
        UserRole.create adminUser, userRole

        Product p1 = new Product( name: 'Sopa', quantity_available: 10, price: 10, description: '1' );
        Product p2 = new Product( name: 'Recao', quantity_available: 5, price: 3, description: '1' );

        p1.save(failOnError: true, flush: true)
        p2.save(failOnError: true, flush: true)
    }

    def destroy = {
    }
}
