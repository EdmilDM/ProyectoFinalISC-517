package proyectofinal

class Cart {
    static belongsTo = [ user: User ]
    static hasMany = [ cartItems: CartItem ]

    static constraints = {
    }
}
