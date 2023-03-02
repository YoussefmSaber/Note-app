package com.example.noteapp.fragments.mainscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.R
import com.example.noteapp.fragments.viewmodel.NoteViewModel
import com.example.noteapp.databinding.FragmentMainPageBinding
import com.example.noteapp.fragments.adapter.NoteListAdapter
import com.example.noteapp.database.model.Note

class MainPageFragment : Fragment(), SearchView.OnQueryTextListener {

    private val adapter: NoteListAdapter by lazy { NoteListAdapter() }

    var _binding: FragmentMainPageBinding? = null
    private lateinit var _noteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainPageBinding.inflate(layoutInflater)



        _binding?.notesRecycleView?.adapter = adapter
        _binding?.notesRecycleView?.layoutManager = LinearLayoutManager(requireContext())

        _binding?.searchView?.clearFocus()

        _binding?.searchView?.isSubmitButtonEnabled = true

        _binding?.searchView?.setOnClickListener{
            _binding?.searchView?.isIconified = false
        }

        _binding?.searchView?.setOnCloseListener{
            _binding?.searchView?.setQuery("", false)
            _binding?.searchView?.isIconified = true
            false
        }

        _binding?.searchView?.setOnQueryTextListener(this)

        _noteViewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        _noteViewModel.readAllData.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
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

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query != null) {
            searchDatabse(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText != null) {
            searchDatabse(newText)
        }
        return true
    }

    private fun searchDatabse(query: String) {
        val searchQuery = "%$query%"
        _noteViewModel.searchDatabase(searchQuery).observe(viewLifecycleOwner, Observer {
            it.let {
                adapter.submitList(it)
            }
        })
    }
}