package ni.edu.uca.examplesoap

import android.content.Context
import android.net.ConnectivityManager

class Utils {
    companion object {

        val SOAP_URL = "http://www.dneonline.com/calculator.asmx?"
        val SOAP_NAMESPACE = "http://tempuri.org/"
        val METHOD_ADD = "Add"

        fun isConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
    }
}