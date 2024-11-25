package com.example.mad_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreateBillActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_bill)

        val billNameEditText = findViewById<EditText>(R.id.billNameEditText)
        val totalAmountEditText = findViewById<EditText>(R.id.totalAmountEditText)
        val addParticipantsButton = findViewById<Button>(R.id.addParticipantsButton)
        val saveBillButton = findViewById<Button>(R.id.saveBillButton)

        addParticipantsButton.setOnClickListener {
            val intent = Intent(this, AddParticipantsActivity::class.java)
            startActivity(intent)
        }

        saveBillButton.setOnClickListener {
            val billName = billNameEditText.text.toString()
            val totalAmount = totalAmountEditText.text.toString()

            if (billName.isNotEmpty() && totalAmount.isNotEmpty()) {
                Toast.makeText(this, "Bill Saved!", Toast.LENGTH_SHORT).show()
                // Save to Firebase (in future steps)
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
