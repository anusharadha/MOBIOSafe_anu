package imageapp.com.mobiosafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class GateEntryActivity : AppCompatActivity() {

    lateinit var dbHelper: DBBluetoothHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gate_entry)

        val vegNames = resources.getStringArray(R.array.veg_names)
        val spinner = findViewById<Spinner>(R.id.spinner_disease)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                applicationContext,
                android.R.layout.simple_spinner_item, vegNames
            )
            spinner.adapter = adapter
        }

        val submit_btn = findViewById<Button>(R.id.SUBMIT)
        submit_btn.setOnClickListener(){ v ->
            val intent = Intent(v.context, MainActivity::class.java)
            startActivity(intent)
        }
        dbHelper = DBBluetoothHelper(applicationContext)
        dbHelper.readData()
    }
}