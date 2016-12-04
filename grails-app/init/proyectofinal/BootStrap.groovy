package proyectofinal

class BootStrap {

    def init = { servletContext ->

        def adminUser = User.findByUsername( 'Admin' )

        if( adminUser == null ) {
            adminUser = new User( username: 'Admin',
                    password: '1234', email: 'admin@local' ).save()
        }

        def adminRole = Role.findByAuthority( 'ROLE_ADMIN' );

        if( adminRole == null ){
            adminRole = new Role(
                    authority: 'ROLE_ADMIN')

            UserRole.create ( adminUser, adminRole )
        }

        if( adminRole == null ) {
            def userRole = new Role(
                    authority: 'ROLE_USER')
            UserRole.create ( adminUser, userRole )
        }
    }

    def destroy = {
    }
}
