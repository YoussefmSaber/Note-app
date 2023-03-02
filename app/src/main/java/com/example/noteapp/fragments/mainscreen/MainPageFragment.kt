package com.example.noteapp.fragments.mainscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentMainPageBinding

class MainPageFragment : Fragment() {

    var _binding: FragmentMainPageBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainPageBinding.inflate(layoutInflater)

        _binding?.addNoteBtn?.setOnClickListener {
            it.findNavController().navigate(R.id.MainPage2AddNote)
        }

        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}