package proyectofinal

import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.OK

class ProductController {

    @Transactional
    def update(Product product) {
        if (product == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (product.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond user.errors, view:'edit'
            return
        }

        product.save flush:true
        redirect(controller: 'Products', action:'index')
    }
}
