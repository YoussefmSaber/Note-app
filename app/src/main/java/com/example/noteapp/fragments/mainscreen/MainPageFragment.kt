package com.example.noteapp.fragments.mainscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.R
import com.example.noteapp.viewmodel.NoteViewModel
import com.example.noteapp.databinding.FragmentMainPageBinding
import com.example.noteapp.fragments.mainscreen.adapter.NoteListAdapter

class MainPageFragment : Fragment() {

    var _binding: FragmentMainPageBinding? = null
    private lateinit var _noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainPageBinding.inflate(layoutInflater)


        val adapter = NoteListAdapter()
        _binding?.notesRecycleView?.adapter = adapter
        _binding?.notesRecycleView?.layoutManager = LinearLayoutManager(requireContext())

        _noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        _noteViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            adapter.setData(it)
        })

        _binding?.addNoteBtn?.setOnClickListener {
            findNavController().navigate(R.id.MainPage2AddNote)
        }

        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}