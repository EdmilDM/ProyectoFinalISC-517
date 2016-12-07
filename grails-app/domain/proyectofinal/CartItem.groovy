package proyectofinal

class CartItem {

    int quantity
    double total

    static belongsTo = [ cart: Cart, product: Product ]

    static constraints = {
        total min: 0
    }
}
