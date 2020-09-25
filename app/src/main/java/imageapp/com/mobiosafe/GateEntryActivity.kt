package imageapp.com.mobiosafe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class GateEntryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gate_entry)

        val vegNames = resources.getStringArray(R.array.veg_names)
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                applicationContext,
                android.R.layout.simple_spinner_item, vegNames
            )
            spinner.adapter = adapter
        }
    }
}