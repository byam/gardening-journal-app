package com.example.gardeningjournalapp.ui.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gardeningjournalapp.databinding.NoteLayoutBinding
import com.example.gardeningjournalapp.db.Note

class NotesAdapter(private val notes: List<Note>): RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    class NoteViewHolder(val binding: NoteLayoutBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            NoteViewHolder {
        val binding = NoteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }
    override fun getItemCount() = notes.size
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.binding.textViewTitle.text = notes[position].title
        holder.binding.textViewNote.text = notes[position].note
    }
}
