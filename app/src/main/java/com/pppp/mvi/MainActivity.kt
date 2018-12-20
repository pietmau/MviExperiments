package com.pppp.mvi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pppp.mvi.network.model.createLoop
import com.spotify.mobius.Connection
import com.spotify.mobius.MobiusLoop
import com.spotify.mobius.android.MobiusAndroid

class MainActivity : AppCompatActivity(), Connection<MarvelModel> {

    private lateinit var controller: MobiusLoop.Controller<MarvelModel, MarvelEvent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        controller =
                MobiusAndroid.controller(MobiusLoop.Factory<MarvelModel, MarvelEvent, MarvelEffect> {
                    createLoop(savedInstanceState)
                }, MarvelModel.Starting)

        controller.connect { events -> this }
    }

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

