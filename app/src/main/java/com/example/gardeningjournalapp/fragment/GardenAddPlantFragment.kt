package com.example.gardeningjournalapp.fragment

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.gardeningjournalapp.databinding.FragmentGardenAddPlantBinding
import com.example.gardeningjournalapp.db.Plant
import com.example.gardeningjournalapp.model.GardenLogViewModel

class GardenAddPlantFragment : Fragment() {
    private lateinit var binding: FragmentGardenAddPlantBinding
    private lateinit var gardenLogViewModel: GardenLogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGardenAddPlantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gardenLogViewModel = ViewModelProvider(this)[GardenLogViewModel::class.java]

        binding.buttonSavePlant.setOnClickListener {
            savePlant(view)
        }
    }

    private fun savePlant(view: View) {
        val name = binding.editTextPlantName.text.toString().trim()
        val type = binding.editTextPlantType.text.toString().trim()
        val wateringFrequency = binding.editTextWateringFrequency.text.toString().toIntOrNull()
        val plantingDate = binding.editTextPlantingDate.text.toString().trim()

        if (validateInput(name, type, wateringFrequency, plantingDate)) {
            val newPlant = Plant(name = name, type = type, wateringFrequency = wateringFrequency!!, plantingDate = plantingDate)
            gardenLogViewModel.insert(newPlant)
            Toast.makeText(context, "Plant added", Toast.LENGTH_SHORT).show()

            // back to Garden Log
            val action = GardenAddPlantFragmentDirections.actionGardenAddPlantFragmentToGardenLogFragment()
            Navigation.findNavController(view).navigate(action)
        } else {
            Toast.makeText(context, "Please fill out all fields correctly", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateInput(name: String, type: String, wateringFrequency: Int?, plantingDate: String): Boolean {
        // Add your validation logic here (e.g., check if fields are not empty)
        return name.isNotEmpty() && type.isNotEmpty() && wateringFrequency != null && plantingDate.isNotEmpty()
    }
}
