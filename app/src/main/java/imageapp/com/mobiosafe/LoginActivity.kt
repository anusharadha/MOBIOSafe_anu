package imageapp.com.mobiosafe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton = findViewById<Button>(R.id.employee_login_btn)
        loginButton.setOnClickListener {
            var intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}