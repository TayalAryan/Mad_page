package com.example.mad_app

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AddParticipantsActivity : AppCompatActivity() {
    private val participants = mutableListOf<Pair<String, String>>() // List of participants
    private lateinit var adapter: ParticipantsAdapter // Declare adapter as a class-level variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_participants)

        // Find views
        val participantsRecyclerView = findViewById<RecyclerView>(R.id.participantsRecyclerView)
        val participantNameEditText = findViewById<EditText>(R.id.participantNameEditText)
        val amountEditText = findViewById<EditText>(R.id.amountEditText)
        val addParticipantButton = findViewById<Button>(R.id.addParticipantButton)
        val calculateSharesButton = findViewById<Button>(R.id.calculateSharesButton)

        // Initialize adapter
        adapter = ParticipantsAdapter(participants) { position ->
            // Delete participant on long press
            participants.removeAt(position)
            adapter.notifyDataSetChanged()
        }

        // Set up RecyclerView
        participantsRecyclerView.layoutManager = LinearLayoutManager(this)
        participantsRecyclerView.adapter = adapter

        // Add participant button click listener
        addParticipantButton.setOnClickListener {
            val name = participantNameEditText.text.toString()
            val amount = amountEditText.text.toString()

            if (name.isNotEmpty() && amount.isNotEmpty()) {
                participants.add(Pair(name, amount)) // Add name and amount to the list
                adapter.notifyDataSetChanged() // Refresh the RecyclerView
                participantNameEditText.text.clear()
                amountEditText.text.clear()
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        // Calculate shares button click listener
        calculateSharesButton.setOnClickListener {
            val totalAmount = amountEditText.text.toString().toDoubleOrNull()
            if (totalAmount != null && participants.isNotEmpty()) {
                calculateShares(totalAmount)
            } else {
                Toast.makeText(this, "Enter a valid amount and add participants", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Function to calculate and assign shares
    private fun calculateShares(totalAmount: Double) {
        val share = totalAmount / participants.size
        participants.replaceAll { it.first to "%.2f".format(share) } // Assign equal share to all
        adapter.notifyDataSetChanged()
        Toast.makeText(this, "Shares calculated", Toast.LENGTH_SHORT).show()
    }
}
