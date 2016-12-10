package proyectofinal

import grails.plugin.springsecurity.annotation.Secured
import net.sf.jasperreports.engine.*
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import net.sf.jasperreports.engine.export.JRPdfExporter
import reports.ReportGenerals

@Secured(["ROLE_USER"])
class ReportController {

}
