package com.example.noteapp.fragments.addnote

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel
import com.example.noteapp.databinding.FragmentAddingNotesBinding


class AddingNotesFragment : Fragment() {

    var _binding: FragmentAddingNotesBinding? = null

    private lateinit var _noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddingNotesBinding.inflate(layoutInflater)

        _noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        _binding?.saveNoteBtn?.setOnClickListener {
            insertDataToDatabase()
        }

        return _binding?.root
    }

    private fun insertDataToDatabase() {
        val title = _binding?.newNoteTitleText?.text.toString()
        val detail = _binding?.newNoteDetailText?.text.toString()
        val color = "Green"

        if (inputCheck(title, detail, color)) {
            val note = Note(0, title, detail, color)

            _noteViewModel.addNote(note)

            Toast.makeText(requireContext(), "Successfully add the note", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.AddingNote2MainPage)
        } else {
            Toast.makeText(requireContext(), "Please check you checked everything", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(title: String, detail: String, color: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(detail) && TextUtils.isEmpty(color))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}