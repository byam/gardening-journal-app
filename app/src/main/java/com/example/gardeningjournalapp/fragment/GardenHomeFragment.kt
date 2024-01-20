package com.example.gardeningjournalapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.gardeningjournalapp.databinding.FragmentGardenHomeBinding

class GardenHomeFragment : Fragment() {
    private lateinit var binding: FragmentGardenHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGardenHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.btnGardenLong.setOnClickListener {
            val action = GardenHomeFragmentDirections.actionGardenHomeFragmentToGardenLogFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }
}
