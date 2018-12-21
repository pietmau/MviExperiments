package com.pppp.mvi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.pppp.network.model.MarvelViewModel
import com.spotify.mobius.Connection
import com.spotify.mobius.MobiusLoop
import com.spotify.mobius.android.MobiusAndroid

class MainActivity : AppCompatActivity(), Connection<MarvelModel> {
    private lateinit var controller: MobiusLoop.Controller<MarvelModel, MarvelEvent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller =
                MobiusAndroid.controller(MobiusLoop.Factory<MarvelModel, MarvelEvent, MarvelEffect> {
                    createLoop(getMarvelViewModel())
                }, MarvelModel.Starting)
        controller.connect { events -> this }
    }

    private fun getMarvelViewModel() = ViewModelProviders.of(this).get(MarvelViewModel::class.java)

    override fun accept(value: MarvelModel) {

    }

    override fun dispose() {

    }

    override fun onDestroy() {
        super.onDestroy()
        controller.disconnect()
    }

    public override fun onResume() {
        super.onResume()
        controller.start()
    }

    public override fun onPause() {
        super.onPause()
        controller.stop()
    }
}

