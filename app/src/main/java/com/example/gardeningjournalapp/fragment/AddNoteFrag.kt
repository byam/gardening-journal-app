package com.example.gardeningjournalapp.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.gardeningjournalapp.databinding.FragAddNoteBinding
import com.example.gardeningjournalapp.db.Note
import com.example.gardeningjournalapp.db.NoteDatabase
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [AddNoteFrag] factory method to
 * create an instance of this fragment.
 */
class AddNoteFrag : Fragment() {
    private lateinit var binding: FragAddNoteBinding
    private var note: Note? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragAddNoteBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Set the listener for the FAB
        binding.buttonSave.setOnClickListener {
            // Retrieve the values from the EditText fields
            val noteTitle = binding.title.text.toString()
            val noteBody = binding.editNote.text.toString()

            // Check the input values are empty, then set the error message and give the focus
            if(noteTitle.isEmpty()){
                binding.title.error = "Title Required"
                binding.title.requestFocus()
                return@setOnClickListener // stop further execution ie returning at the end of the setOnClickListener
            }

            if(noteBody.isEmpty()){
                binding.editNote.error = "Title Required"
                binding.editNote.requestFocus()
                return@setOnClickListener // stop further execution ie returning at the end of the setOnClickListener
            }
            lifecycleScope.launch {
                val note = Note(title = noteTitle, note = noteBody)
                context?.let {
                    NoteDatabase(it).getNoteDao().addNote(note)
                    Toast.makeText(it, "Saved note!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // deletion logic
        binding.buttonDelete.setOnClickListener {
            if (note != null)
                deleteNote()
            else
                Toast.makeText(context, "Cannot Delete", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteNote() {
        AlertDialog.Builder(context).apply {
            setTitle("Are you sure?")
            setMessage("You cannot undo this operation")
            setPositiveButton("Yes") {dialog, which ->
                lifecycleScope.launch {
                    NoteDatabase(context).getNoteDao().deleteNote(note!!)
                    val action =
                        com.example.gardeningjournalapp.ui.AddNoteFragDirections.actionSaveNote()
                    Navigation.findNavController(requireView()).navigate(action)
                }
            }
            setNegativeButton("No") {dialog, which->
            }
        }.create().show()
    }
}
