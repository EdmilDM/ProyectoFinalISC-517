package reports

import proyectofinal.Sale
import proyectofinal.SaleItem

/**
 * Created by AcMined on 12/10/2016.
 */
class ReportGenerals {
    static ArrayList< ArticleReport > getInvoceItems( Sale sale ){
        def ret = []
        for( SaleItem si: sale.items ){
            ArticleReport ar = new ArticleReport();
            ar.id = si.product.id
            ar.name = si.product.name
            ar.quantity = si.quantity
            ar.cost = si.product.price
            ar.price = si.quantity*si.product.price
            ret.add( ar )
        }
        ret
    }
}
