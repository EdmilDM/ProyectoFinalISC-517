package proyectofinal

class Product {

    String name
    String description
    Integer quantity_available
    Double price
    String image

    static hasMany = [ saleItem: SaleItem ]

    static constraints = {
        name blank: false
        quantity_available min: 0
        price blank: false
    }
}
