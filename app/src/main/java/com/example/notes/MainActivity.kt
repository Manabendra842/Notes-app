package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_note.*
import android.widget.Toast.makeText as toastMakeText
import com.example.notes.NoteViewModel as NoteViewModel1

class MainActivity : AppCompatActivity(), INotesRVAdapter {

    lateinit var viewModel: NoteViewModel1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager=LinearLayoutManager(this)
        val adapter=NotesRVadapter(this,this)
        recyclerView.adapter=adapter

        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(
            NoteViewModel1::class.java)
        viewModel.allNotes.observe(this, Observer{list->

            list?.let {
                adapter.updateList(it)
            }
        })

    }

    override fun onItemClicked(notes: Notes) {
        viewModel.deleteNote(notes)
        Toast.makeText(this,"${notes.text} Deleted",Toast.LENGTH_LONG).show()
    }

    fun submitData(view: View) {
        val noteText=input.text.toString()
        if(noteText.isNotEmpty()){
            viewModel.insertNote((Notes(noteText)))
            Toast.makeText(this,"$noteText Inserted",Toast.LENGTH_LONG).show()
        }
    }
}