package com.example.gardeningjournalapp.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gardeningjournalapp.databinding.FragmentGardenPlantDetailsBinding
import com.example.gardeningjournalapp.model.GardenLogViewModel

class GardenPlantDetailsFragment : Fragment() {
    private lateinit var binding: FragmentGardenPlantDetailsBinding
    private lateinit var plantViewModel: GardenLogViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGardenPlantDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        plantViewModel = ViewModelProvider(this)[GardenLogViewModel::class.java]

        val plantId = arguments?.let { GardenPlantDetailsFragmentArgs.fromBundle(it).plantId }
        if (plantId != null) {
            // Load plant details using ViewModel and display
            plantViewModel.getPlantById(plantId).observe(viewLifecycleOwner) { plant ->
                // Display plant details
                binding.textPlantName.text = plant.name
                binding.textPlantType.text = plant.type
                binding.textWateringFrequency.text = plant.wateringFrequency.toString()
                binding.textPlantingDate.text = plant.plantingDate
            }
        }

        // Implement delete functionality
        binding.buttonDeletePlant.setOnClickListener {
            // Delete the plant
            showDeleteConfirmationDialog(plantId)
        }
    }

    private fun showDeleteConfirmationDialog(plantId: Int?) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Plant")
            .setMessage("Are you sure you want to delete this plant?")
            .setPositiveButton("Yes") { dialog, _ ->
                plantId?.let {
                    deletePlant(it)
                }
                dialog.dismiss()
                navigateBackToGardenLog()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun deletePlant(plantId: Int) {
        // Assuming you have a method in your ViewModel to delete by ID
        plantViewModel.deleteById(plantId)
        Toast.makeText(context, "Plant Deleted", Toast.LENGTH_SHORT).show()
    }

    private fun navigateBackToGardenLog() {
        // Navigate back to GardenLogFragment
        findNavController().navigateUp()
    }
}
