package com.example.noteapp.fragments.addnote

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.database.model.Note
import com.example.noteapp.fragments.viewmodel.NoteViewModel
import com.example.noteapp.databinding.FragmentAddingNotesBinding


class AddingNotesFragment : Fragment() {

    var _binding: FragmentAddingNotesBinding? = null

    private lateinit var _noteViewModel: NoteViewModel

    var colorChoosen: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddingNotesBinding.inflate(layoutInflater)

        _noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        _binding?.blueColor?.setOnClickListener {
            setNoteColor("blue", R.drawable.light_blue_mark_selected, it as ImageView)
        }

        _binding?.greenColor?.setOnClickListener {
            setNoteColor("green", R.drawable.light_green_mark_selected, it as ImageView)
        }

        _binding?.pinkColor?.setOnClickListener {
            setNoteColor("pink", R.drawable.light_pink_mark_selected, it as ImageView)
        }

        _binding?.orangeColor?.setOnClickListener {
            setNoteColor("orange", R.drawable.light_orange_mark_selected, it as ImageView)
        }

        _binding?.purpleColor?.setOnClickListener {
            setNoteColor("purple", R.drawable.light_purple_mark_selected, it as ImageView)
        }

        _binding?.saveNoteBtn?.setOnClickListener {
            insertDataToDatabase()
        }

        return _binding?.root
    }

    private fun insertDataToDatabase() {
        val title = _binding?.newNoteTitleText?.text.toString()
        val detail = _binding?.newNoteDetailText?.text.toString()
        val color = colorChoosen

        if (inputCheck(title, detail, color)) {
            val note = Note(0, title, detail, color)

            _noteViewModel.addNote(note)

            Toast.makeText(requireContext(), "Successfully add the note", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.AddingNote2MainPage)
        } else {
            Toast.makeText(
                requireContext(),
                "Please check you checked everything",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setNoteColor(color: String, colorResource: Int, selectedView: ImageView) {
        colorChoosen = color
        _binding?.blueColor?.setImageResource(R.drawable.light_blue_mark_notselected)
        _binding?.greenColor?.setImageResource(R.drawable.light_green_mark_notselected)
        _binding?.pinkColor?.setImageResource(R.drawable.light_pink_mark_notselected)
        _binding?.orangeColor?.setImageResource(R.drawable.light_orange_mark_notselected)
        _binding?.purpleColor?.setImageResource(R.drawable.light_purple_mark_notselected)
        selectedView.setImageResource(colorResource)
        _binding?.titleBackgroundColor?.setBackgroundColor(
            ContextCompat.getColor(
                requireContext(), when (colorChoosen) {
                    "blue" -> R.color.light_blue
                    "green" -> R.color.light_green
                    "pink" -> R.color.light_pink
                    "purple" -> R.color.light_purple
                    "orange" -> R.color.light_orange
                    else -> R.color.white
                }
            )
        )
    }

    private fun inputCheck(title: String, detail: String, color: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(detail) && TextUtils.isEmpty(color))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}