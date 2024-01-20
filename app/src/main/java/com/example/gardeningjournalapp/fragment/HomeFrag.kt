package com.example.gardeningjournalapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.gardeningjournalapp.databinding.FragHomeBinding
import com.example.gardeningjournalapp.db.NoteDatabase
import com.example.gardeningjournalapp.adapter.NotesAdapter
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class HomeFrag : Fragment() {
    private lateinit var binding: FragHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragHomeBinding.inflate(inflater, container, false)

//        binding.apply {
//            buttonAdd.setOnClickListener {
//                // home -> add note
//                val directions = HomeFragDirections.actionAddNote()
//                findNavController().navigate(directions)
//            }
//        }

        return binding.root
    }

//    @Deprecated("Deprecated in Java")
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        binding.buttonAdd.setOnClickListener {
//        // After Rebuild you will get HomeFragmentDirections automatically,
//        // call the navigation action id given in the Navigation graph
//        val action = HomeFragDirections.actionAddNote()
//        // Navigate to the action by passing view and call navigate by passing action
//        Navigation.findNavController(it).navigate(action)
//        }
//    }

    // Add the following code in HomeFragment
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.buttonAdd.setOnClickListener {
            val action = HomeFragDirections.actionAddNote()
            Navigation.findNavController(it).navigate(action)
        }

        binding.recyclerViewNotes.setHasFixedSize(true)
        binding.recyclerViewNotes.layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        )
        // Retrieve all notes from database to RecyclerView using Coroutines
        lifecycleScope.launch {
            context?.let {
                val notes = NoteDatabase(it).getNoteDao().getAllNotes()
                binding.recyclerViewNotes.adapter = NotesAdapter(notes)
            }
        }
    }
}
