package com.example.learndb
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learndb.data.db.PersonDatabase
import com.example.learndb.databinding.ActivityMainBinding
import com.example.learndb.fragment.recyclerview.PeopleListAdapter
import com.example.learndb.presentation.MainViewModel
import com.example.learndb.presentation.MainViewModelFactory


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: PeopleListAdapter
    lateinit var db: PersonDatabase
    private val viewModel by viewModels<MainViewModel> {
        MainViewModelFactory(db)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = PersonDatabase.getInstance(applicationContext)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        setUp()
    }

    fun setUp() {
        val rv = binding.peopleRv
        adapter = PeopleListAdapter { person -> viewModel.onClickDelete(person) }
        rv.layoutManager = LinearLayoutManager(this)
        viewModel.people.observe(this, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        rv.adapter = adapter
        binding.addBtn.setOnClickListener {
            onClickAdd()
        }
    }

    fun onClickAdd() {
        val name = binding.nameInput.text
        val prof = binding.profInput.text
        if (name.isEmpty() || prof.isEmpty()) {
            val toast = Toast.makeText(applicationContext, "Please, enter a text", Toast.LENGTH_SHORT)
            toast.show()
        } else {
            viewModel.name = name.toString()
            viewModel.prof = prof.toString()
            viewModel.onClickAdd()
        }
    }
}