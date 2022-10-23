package be.hogent.vic

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Bar met "VIC" titel verbergen
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
    }


}