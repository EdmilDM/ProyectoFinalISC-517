package proyectofinal

class BootStrap {

    def init = { servletContext ->

        Cart cart = new Cart( )
        cart.save( )
        User adminUser = new User(username: 'admin', cart: cart,
                        password: '12345', email: 'admin@local', complete_name: 'Super Administrator', is_entity: true, address: 'Santiago, RD' ).save( failOnError: true, flush: true  )


        Role adminRole = new Role( authority: 'ROLE_ADMIN' )
        UserRole.create adminUser, adminRole

        Role userRole = new Role( authority: 'ROLE_USER' )
        UserRole.create adminUser, userRole

        Role storageRole = new Role( authority: 'ROLE_STORAGE' )
        storageRole.save( )

        Cart cart1 = new Cart( )
        cart.save( )

        User storageUser = new User( username: 'acmined', cart: cart1, password: '12345', email: 'acmined@gmail.com', complete_name: 'Cesar', is_entity: false, address: 'Santiago, RD' ).save( failOnError: true, flush: true )
        storageUser.save( )

        UserRole.create storageUser, userRole
        UserRole.create storageUser, storageRole

        Product p1 = new Product( name: 'Rice', quantity_available: 10, price: 10, description: "Rice", image: "https://ucarecdn.com/c67b4b7a-bc32-456f-906f-67565605017a/" );
        Product p2 = new Product( name: 'Beans', quantity_available: 5, price: 3, description: "Beans", image: "https://ucarecdn.com/c67b4b7a-bc32-456f-906f-67565605017a/" );

        p1.save(failOnError: true, flush: true)
        p2.save(failOnError: true, flush: true)
    }

    def destroy = {
    }
}
