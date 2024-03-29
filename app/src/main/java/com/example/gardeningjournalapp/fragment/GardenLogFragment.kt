package com.example.gardeningjournalapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.gardeningjournalapp.adapter.GardensAdapter
import com.example.gardeningjournalapp.databinding.FragmentGardenLogBinding
import com.example.gardeningjournalapp.model.GardenLogViewModel

class GardenLogFragment : Fragment() {
    private lateinit var binding: FragmentGardenLogBinding
    private lateinit var plantViewModel: GardenLogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGardenLogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        plantViewModel = ViewModelProvider(this)[GardenLogViewModel::class.java]

        // Setup RecyclerView
        binding.recyclerViewPlants.apply {
            setHasFixedSize(true)
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
        // Fetch all plants
        plantViewModel.plants.observe(viewLifecycleOwner) { plants ->
            binding.recyclerViewPlants.adapter = GardensAdapter(plants) { plant ->
                // Handle click here. Navigate to the details fragment
                val action = GardenLogFragmentDirections.actionGardenLogFragmentToGardenPlantDetailsFragment(plant.id)
                findNavController().navigate(action)
            }
        }

        // Add plant
        binding.buttonAddPlant.setOnClickListener{
            val action = GardenLogFragmentDirections.actionGardenLogFragmentToGardenAddPlantFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}
