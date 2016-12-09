package proyectofinal

class CartItem {

    Integer quantity
    Double total

    static belongsTo = [ cart: Cart, product: Product ]

    static constraints = {
        total blank: false
    }
}
