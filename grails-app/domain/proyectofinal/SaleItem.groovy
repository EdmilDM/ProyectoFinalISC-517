package proyectofinal

class SaleItem {

    int quantity
    double total

    static belongsTo = [ sale: Sale, product: Product ]

    static constraints = {
        total blank: false
    }
}
