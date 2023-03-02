package com.example.noteapp.fragments.updatenote

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentUpdateNotesBinding
import com.example.noteapp.database.model.Note
import com.example.noteapp.fragments.viewmodel.NoteViewModel

class UpdateNotesFragment : Fragment() {

    private val args by navArgs<UpdateNotesFragmentArgs>()
    var _binding: FragmentUpdateNotesBinding? = null
    private lateinit var _noteViewModel: NoteViewModel

    var colorChoosen = ""

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
            colorChoosen = it.noteColor
            when (colorChoosen) {
                "blue" -> {
                    _binding?.updateTitleBackgroundColor?.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.light_blue
                        )
                    )
                    _binding?.blueColor?.setImageResource(R.drawable.light_blue_mark_selected)
                }
                "green" -> {
                    _binding?.updateTitleBackgroundColor?.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.light_green
                        )
                    )
                    _binding?.greenColor?.setImageResource(R.drawable.light_green_mark_selected)
                }
                "pink" -> {
                    _binding?.updateTitleBackgroundColor?.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.light_pink
                        )
                    )
                    _binding?.pinkColor?.setImageResource(R.drawable.light_pink_mark_selected)
                }
                "purple" -> {
                    _binding?.updateTitleBackgroundColor?.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.light_purple
                        )
                    )
                    _binding?.purpleColor?.setImageResource(R.drawable.light_purple_mark_selected)
                }
                "orange" -> {
                    _binding?.updateTitleBackgroundColor?.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.light_orange
                        )
                    )
                    _binding?.orangeColor?.setImageResource(R.drawable.light_orange_mark_selected)
                }
                else -> R.color.white
            }
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
        })

        _binding?.updateNoteBtn?.setOnClickListener {
            updateItem()
        }

        return _binding?.root
    }

    private fun updateItem() {
        val title = _binding?.updateNoteTitleText?.text.toString()
        val detail = _binding?.updateNoteDetailText?.text.toString()
        val color = colorChoosen
        if (inputCheck(title, detail, color)) {
            val updatedNote = Note(args.noteId, title, detail, color)
            _noteViewModel.updateNote(updatedNote)
            findNavController().navigate(R.id.UpdateNote2MainPage)
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
        _binding?.updateTitleBackgroundColor?.setBackgroundColor(
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