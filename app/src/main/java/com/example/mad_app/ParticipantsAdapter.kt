package com.example.mad_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ParticipantsAdapter(
    private val participants: MutableList<Pair<String, String>>,
    private val onDelete: (Int) -> Unit // Callback for delete action
) : RecyclerView.Adapter<ParticipantsAdapter.ParticipantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticipantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_participant, parent, false)
        return ParticipantViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParticipantViewHolder, position: Int) {
        val (name, amount) = participants[position]
        holder.bind(name, amount)
        holder.itemView.setOnLongClickListener {
            onDelete(position)
            true
        }
    }

    override fun getItemCount(): Int = participants.size

    class ParticipantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.participantNameTextView)
        private val amountTextView: TextView = itemView.findViewById(R.id.amountOwedTextView)

        fun bind(name: String, amount: String) {
            nameTextView.text = name
            amountTextView.text = amount
        }
    }
}

