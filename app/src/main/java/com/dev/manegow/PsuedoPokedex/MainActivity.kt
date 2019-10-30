package com.dev.manegow.PsuedoPokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.manegow.PseudoPokedex.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), MyAdapter.Listener {

    private var myAdapter : MyAdapter? = null
    private var myCompositeDisposable: CompositeDisposable? = null
    private var myPokemonDataArrayList: List<Results>? = null
    private val BASE_URL = "https://pokeapi.co/api/v2/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myCompositeDisposable = CompositeDisposable()
        initRecyclerView()
        loadData()
    }

    private fun loadData() {
        val requestInterface = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(GetData::class.java)
        myCompositeDisposable?.add(requestInterface.getPokedexData()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse))
    }

    private fun handleResponse(pokeList: PokeDexData){
        myPokemonDataArrayList = ArrayList(pokeList.Results)
        myAdapter = MyAdapter(
            (myPokemonDataArrayList as ArrayList<Results>)!!,
            this
        )
        cryptocurrency_list.adapter = myAdapter
    }

    private fun initRecyclerView() {
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        cryptocurrency_list.layoutManager = layoutManager
    }

    override fun OnItemClick(pokeData: Results) {
        Toast.makeText(this, "You clicked: ${pokeData.Name}", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        myCompositeDisposable?.clear()
    }
}
