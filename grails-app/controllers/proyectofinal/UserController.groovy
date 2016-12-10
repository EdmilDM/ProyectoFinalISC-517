package proyectofinal

import grails.plugin.springsecurity.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured( [ "ROLE_USER" ] )
class UserController {


    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    def springSecurityService


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        respond User.list(params), model:[userCount: User.count(), current_user: springSecurityService.currentUser ]
    }

    def profile( String username ) {
        System.out.println( username );
        User current_user = springSecurityService.currentUser
        User user = User.findByUsername( username );

        [ user: user, current_user: current_user ]
    }

    def show(User user) {
        respond user
    }

    def create() {
        respond new User(params)
    }

    @Transactional
    def save( ) {
        User user = new User( )

        user.complete_name = params.complete_name
        user.address = params.address
        user.email = params.email
        user.is_entity = params.is_entity == "1"
        user.password = params.password
        user.username = params.username

        if (user.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond user.errors, view:'create'
            return
        }

        Cart cart = new Cart( );
        cart.save( );

        user.cart = cart;

        user.save flush:true
        Role userRole = Role.findByAuthority( "ROLE_USER" );
        UserRole.create user, userRole

        sendMail {
            to user.email
            text "Bienvenido a MiniZONE!, sus datos de acceso son:\nUsername = " + user.username + "\t Password: " + params.password
            subject "Login details to MiniZONE"
        }
    }

    def edit(User user) {
        respond user
    }

    @Transactional
    def update( ) {
        User user = User.findById( params.id )

        user.complete_name = params.complete_name
        user.address = params.address
        user.email = params.email
        user.is_entity = params.is_entity == "1"
        user.username = params.username

        if( params.password.length() > 0 ){
            user.password = params.password
        }

        if (user.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond user.errors, view:'edit'
            return
        }

        user.save flush:true
    }

    @Transactional
    def delete(User user) {

        if (user == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        user.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
