package ni.edu.uca.examplesoap

import org.ksoap2.SoapEnvelope
import org.ksoap2.serialization.SoapObject
import org.ksoap2.serialization.SoapSerializationEnvelope
import org.ksoap2.transport.HttpTransportSE

class CallWebService {
    fun callApi(methodName: String, input1: String?, input2: String?): String {
        var result = ""
        val SOAP_ACTION = Utils.SOAP_NAMESPACE + methodName
        val soapObject = SoapObject(Utils.SOAP_NAMESPACE, methodName)


        soapObject.addProperty("intA", input1)
        soapObject.addProperty("intB", input2)

        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11)
        envelope.setOutputSoapObject(soapObject)
        envelope.dotNet = true

        val httpTransportSE = HttpTransportSE(Utils.SOAP_URL)

        try {
            httpTransportSE.call(SOAP_ACTION, envelope)
            val soapPrimitive = envelope.response
            result = soapPrimitive.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return result
    }
}