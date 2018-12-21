package com.pppp.mvicoreapp.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.pppp.mvicoreapp.R
import com.pppp.mvicoreapp.binders.MviBinding
import com.pppp.mvicoreapp.di.Injector
import com.pppp.mvicoreapp.main.MainViewModel.GetingComics
import com.pppp.mvicoreapp.main.MainViewModel.Starting
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), Consumer<MainViewModel> {
    @Inject
    lateinit var mviBinding: MviBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Injector.inject(this)
        mviBinding.bind(this)
    }

    override fun accept(viewModel: MainViewModel) {
        when (viewModel) {
            Starting -> return
            GetingComics -> onGettingComics()
        }
    }

    private fun onGettingComics() {
        progress.visibility = View.VISIBLE
    }

}
