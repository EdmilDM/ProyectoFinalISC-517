package proyectofinal

class Sale {

    String paypal_transaction_id
    String NCF
    Double total
    boolean given

    static belongsTo = [ user: User ]
    static hasMany = [ items: SaleItem ]

    static constraints = {
        total min: 0
    }
}
