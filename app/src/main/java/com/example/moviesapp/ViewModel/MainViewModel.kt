package com.example.moviesapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviesapp.Models.FilmModel
import com.example.moviesapp.Models.SliderItems
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel: ViewModel() {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _movies = MutableLiveData<MutableList<SliderItems>>()
    val movies: LiveData<MutableList<SliderItems>> = _movies

    private val _films = MutableLiveData<MutableList<FilmModel>>()
    val films: LiveData<MutableList<FilmModel>> = _films

    private val _ucfilms = MutableLiveData<MutableList<FilmModel>>()
    val ucfilms: LiveData<MutableList<FilmModel>> = _ucfilms

    fun loadMovies(){
        val ref = firebaseDatabase.getReference("Banners")
        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var lists = mutableListOf<SliderItems>()
                for (childSnapshot in snapshot.children ){
                    val list = childSnapshot.getValue(SliderItems::class.java)
                    if(list!=null){
                        lists.add(list)
                    }
                }
                _movies.value = lists
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun loadFilms(){
        val ref = firebaseDatabase.getReference("Items")
        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var lists = mutableListOf<FilmModel>()
                for (child in snapshot.children){
                    val list = child.getValue(FilmModel::class.java)
                    if(list!=null){
                        lists.add(list)
                    }
                }
                _films.value = lists
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    fun loadUpcoming(){
        val ref = firebaseDatabase.getReference("Upcomming")
        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var lists = mutableListOf<FilmModel>()
                for (child in snapshot.children){
                    val list = child.getValue(FilmModel::class.java)
                    if(list!=null){
                        lists.add(list)
                    }
                }
                _ucfilms.value = lists
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

}