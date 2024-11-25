package com.example.mad_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val createBillButton = findViewById<Button>(R.id.createBillButton)
        createBillButton.setOnClickListener {
            startActivity(Intent(this, CreateBillActivity::class.java))
        }
    }
}