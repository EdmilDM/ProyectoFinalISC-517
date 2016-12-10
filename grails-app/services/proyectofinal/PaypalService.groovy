package proyectofinal

import grails.converters.JSON
import groovy.transform.CompileStatic

import org.apache.http.auth.AuthScope
import org.apache.http.auth.UsernamePasswordCredentials
import org.apache.http.client.CredentialsProvider
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.BasicCredentialsProvider
import org.apache.http.impl.client.HttpClientBuilder

import com.paypal.api.payments.Address
import com.paypal.api.payments.Amount
import com.paypal.api.payments.Authorization
import com.paypal.api.payments.Capture
import com.paypal.api.payments.CreditCard
import com.paypal.api.payments.CreditCardToken
import com.paypal.api.payments.Details
import com.paypal.api.payments.FundingInstrument
import com.paypal.api.payments.Payer
import com.paypal.api.payments.Payment
import com.paypal.api.payments.PaymentExecution
import com.paypal.api.payments.RedirectUrls
import com.paypal.api.payments.Refund
import com.paypal.api.payments.RelatedResources
import com.paypal.api.payments.Sale
import com.paypal.api.payments.Transaction
import com.paypal.base.Constants
import com.paypal.base.rest.APIContext
import com.paypal.base.rest.OAuthTokenCredential
import com.paypal.base.rest.PayPalRESTException

@CompileStatic
class PaypalService {
    
}