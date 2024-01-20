package com.example.gardeningjournalapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.gardeningjournalapp.R
import com.example.gardeningjournalapp.databinding.FragmentSplash1Binding

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class Splash1Fragment : Fragment() {
    private lateinit var binding: FragmentSplash1Binding
    private val nargs: com.example.gardeningjournalapp.Splash1FragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplash1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Retrieve and set the Arguments received from the Home
        binding.tvName.text = "Name is ${nargs.pname}"
        // To show Fragments Toast
        Toast.makeText(activity,"Name is ${nargs.pname}, Age is ${nargs.page}",Toast.LENGTH_LONG).show()

        binding.tvAge.text = "Age is to ${nargs.page}"
        // Once the user click the Splash Screen TextView will take
        // Action to return Home Fragment using action directions id
        binding.home.setOnClickListener {
            findNavController().navigate(R.id.action_splash1Fragment_to_homeFragment)
        }
    }
}
