package com.fguyet.doitwithstyle

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        increaseTeam1.setOnClickListener { increaseScore(score_1) }
        increaseTeam2.setOnClickListener { increaseScore(score_2) }
        decreaseTeam1.setOnClickListener { decreaseScore(score_1) }
        decreaseTeam2.setOnClickListener { decreaseScore(score_2) }
    }

    private fun decreaseScore(view: TextView) {
        var score = Integer.parseInt(view.text.toString())
        view.text = (--score).toString()
    }

    private fun increaseScore(view: TextView) {
        var score = Integer.parseInt(view.text.toString())
        view.text = (++score).toString()
    }

}