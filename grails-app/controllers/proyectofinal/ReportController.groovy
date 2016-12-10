package proyectofinal

import grails.plugin.springsecurity.annotation.Secured
import net.sf.jasperreports.engine.*
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import net.sf.jasperreports.engine.export.JRPdfExporter
import reports.ReportGenerals

@Secured(["ROLE_ADMIN"])
class ReportController {
    def dataSource
    ByteArrayOutputStream  pdfStream

    def index() {
        //
    }

    def invoice( Integer id ){
        Sale sale = Sale.findById( id );
        try {
            String reportName, jrxmlFileName, dotJasperFileName
            jrxmlFileName = "invoice"
            reportName = grailsApplication.mainContext.getResource('reports/' + jrxmlFileName + '.jrxml').file.getAbsoluteFile()
            dotJasperFileName = grailsApplication.mainContext.getResource('reports/' + jrxmlFileName + '.jasper').file.getAbsoluteFile()

            // Report parameter

            def dataList = ReportGenerals.getInvoceItems( sale )

            JRBeanCollectionDataSource beanColDataSource = new
                    JRBeanCollectionDataSource(dataList, false);

            Map<String, Object> reportParam = new HashMap<String, Object>()

            reportParam.put("customerName", sale.user.complete_name )
            reportParam.put("customerEmail", sale.user.email)
            reportParam.put("invoiceNumber", sale.id)
            reportParam.put("invoiceAmount",sale.total)
            reportParam.put("invoiceStatus",sale.paypal_transaction_id == "" ? "Not Paid" : "Paid" )
            reportParam.put("invoiceDate", sale.dateCreated)
            reportParam.put("NCF", sale.NCF)
            // compiles jrxml
            JasperCompileManager.compileReportToFile(reportName);
            // fills compiled report with parameters and a connection
            JasperPrint print = JasperFillManager.fillReport(dotJasperFileName, reportParam, beanColDataSource);

            pdfStream = new ByteArrayOutputStream();

            // exports report to pdf
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, pdfStream); // your output goes here

            exporter.exportReport();
            //println 'pdfStream = '+pdfStream.size()
            render(file: pdfStream.toByteArray(), contentType: 'application/pdf')

        } catch (Exception e) {
            throw new RuntimeException("It's not possible to generate the pdf report.", e);
        }
    }

    def storage( Integer id ){
        Sale sale = Sale.findById( id );
        try {
            String reportName, jrxmlFileName, dotJasperFileName
            jrxmlFileName = "storage"
            reportName = grailsApplication.mainContext.getResource('reports/' + jrxmlFileName + '.jrxml').file.getAbsoluteFile()
            dotJasperFileName = grailsApplication.mainContext.getResource('reports/' + jrxmlFileName + '.jasper').file.getAbsoluteFile()

            // Report parameter

            def dataList = ReportGenerals.getInvoceItems( sale )

            JRBeanCollectionDataSource beanColDataSource = new
                    JRBeanCollectionDataSource(dataList, false);

            Map<String, Object> reportParam = new HashMap<String, Object>()

            reportParam.put("customerName", sale.user.complete_name )
            reportParam.put("customerEmail", sale.user.email)
            reportParam.put("invoiceNumber", sale.id)
            reportParam.put("invoiceAmount",sale.total)
            reportParam.put("invoiceStatus",sale.paypal_transaction_id == "" ? "Not Paid" : "Paid" )
            reportParam.put("invoiceDate", sale.dateCreated)
            reportParam.put("NCF", sale.NCF)
            // compiles jrxml
            JasperCompileManager.compileReportToFile(reportName);
            // fills compiled report with parameters and a connection
            JasperPrint print = JasperFillManager.fillReport(dotJasperFileName, reportParam, beanColDataSource);

            pdfStream = new ByteArrayOutputStream();

            // exports report to pdf
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, pdfStream); // your output goes here

            exporter.exportReport();
            //println 'pdfStream = '+pdfStream.size()
            render(file: pdfStream.toByteArray(), contentType: 'application/pdf')

        } catch (Exception e) {
            throw new RuntimeException("It's not possible to generate the pdf report.", e);
        }
    }
}
