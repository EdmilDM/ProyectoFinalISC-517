package proyectofinal

class Product {

    String name
    String description
    int quantity_available
    Double price
    ArrayList< String > Images

    static hasMany = [ SaleItem ]

    static constraints = {
        name blank: false
        quantity_available min: 0
        price blank: false
    }
}
