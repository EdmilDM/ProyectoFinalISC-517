package proyectofinal

class Sale {
    String paypal_transaction_id = ""
    String NCF
    Double total
    boolean given

    Date dateCreated
    Date lastUpdated

    static belongsTo = [ user: User ]
    static hasMany = [ items: SaleItem ]

    static constraints = {
        total blank: false
    }
}
