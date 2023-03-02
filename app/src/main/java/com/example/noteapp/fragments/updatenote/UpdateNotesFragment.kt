package com.example.noteapp.fragments.updatenote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentUpdateNotesBinding
import com.example.noteapp.viewmodel.NoteViewModel

class UpdateNotesFragment : Fragment() {

    private val args by navArgs<UpdateNotesFragmentArgs>()
    var _binding: FragmentUpdateNotesBinding? = null
    private lateinit var _noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateNotesBinding.inflate(layoutInflater)
        _noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        _noteViewModel.getNoteById(args.noteId).observe(viewLifecycleOwner, Observer {
            _binding?.updateNoteTitleText?.setText(it.noteTitle)
            _binding?.updateNoteDetailText?.setText(it.noteDescription)
        })
        return _binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}