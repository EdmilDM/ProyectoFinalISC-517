package proyectofinal

class Sale {

    String paypal_transaction_id
    String NCF
    Double total
    boolean given

    static belongsTo = [ User ]
    static hasMany = [ SaleItem ]

    static constraints = {
        total min: 0.0
    }
}
