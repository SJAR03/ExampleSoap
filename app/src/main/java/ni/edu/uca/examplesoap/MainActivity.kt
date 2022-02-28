package ni.edu.uca.examplesoap

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val add = findViewById<TextView>(R.id.add)
        val enterInput1 = findViewById<TextInputEditText>(R.id.enterInput1)
        val enterInput2 = findViewById<TextInputEditText>(R.id.enterInput2)

        add.setOnClickListener {
            val input1 = enterInput1.text.toString().trim()
            val input2 = enterInput2.text.toString().trim()
            when {
                input1.length == 0 || input2.length == 0 -> Toast.makeText(this, getString(R.string.fill_field), Toast.LENGTH_SHORT).show()

                !Utils.isConnected(this) -> Toast.makeText(this, getString(R.string.no_internet), Toast.LENGTH_SHORT).show()

                else -> getCitiesOfCountry().execute(input1,input2)
            }
        }
    }

    inner class getCitiesOfCountry : AsyncTask<String, String, String>() {

        override fun doInBackground(vararg params: String?): String {
            val response = CallWebService().callApi(Utils.METHOD_ADD, params[0], params[1])
            Log.v("response", "response==" + response)
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            Log.v("response", "OnPostresponse==" + result)
            try {
                val resultValue = findViewById<TextView>(R.id.resultValue)
                resultValue.text = getString(R.string.result)+" "+result
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }
}