package com.example.gardeningjournalapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gardeningjournalapp.databinding.FragmentHomeFragmentBinding

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflate layout
        binding = FragmentHomeFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            btnSend.setOnClickListener {
                // home -> splash
                val directions =
                    HomeFragmentDirections.actionHomeFragmentToSplash1Fragment(
                        etName.text.toString(),
                        etAge.text.toString().toInt()
                    )
                findNavController().navigate(directions)
            }
        }
        return binding.root
    }
}
