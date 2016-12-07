package proyectofinal

class Cart {
    static belongsTo = [ User ]
    static hasMany = [ CartItem ]

    static constraints = {
    }
}
