package proyectofinal

class BootStrap {

    def init = { servletContext ->

        User adminUser = new User(username: 'admin',
                        password: '12345', email: 'admin@local', complete_name: 'Super Administrator').save( failOnError: true, flush: true  )

        System.out.println("ADMIN CREADO!" + adminUser.username );


        Role adminRole = new Role( authority: 'ROLE_ADMIN' )

        UserRole.create adminUser, adminRole

        Role userRole = new Role( authority: 'ROLE_USER' )
        UserRole.create adminUser, userRole
    }

    def destroy = {
    }
}
