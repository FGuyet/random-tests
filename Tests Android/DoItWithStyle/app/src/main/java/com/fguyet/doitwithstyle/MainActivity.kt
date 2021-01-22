package com.fguyet.doitwithstyle

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    // region Score
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        savedInstanceState?.let {
            score_1.integerText = savedInstanceState.getInt(STATE_SCORE_1)
            score_2.integerText = savedInstanceState.getInt(STATE_SCORE_2)
        }

        increaseTeam1.setOnClickListener { increaseScore(score_1) }
        increaseTeam2.setOnClickListener { increaseScore(score_2) }
        decreaseTeam1.setOnClickListener { decreaseScore(score_1) }
        decreaseTeam2.setOnClickListener { decreaseScore(score_2) }
    }

    private fun decreaseScore(view: TextView) {
        --view.integerText
    }

    private fun increaseScore(view: TextView) {
        ++view.integerText
    }

    private var TextView.integerText: Int
        get() = Integer.parseInt(text.toString())
        set(value) {
            text = value.toString()
        }
    // endregion Score

    // region menu and themes
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        // Change the label of the menu based on the state of the app.
        val nightMode = AppCompatDelegate.getDefaultNightMode()
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode)
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Check if the correct item was clicked
        if (item.itemId == R.id.night_mode) {
            // Get the night mode state of the app.
            val nightMode = AppCompatDelegate.getDefaultNightMode()
            //Set the theme mode for the restarted activity
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            // Recreate the activity for the theme change to take effect.
            recreate()
        }
        return true
    }
    // endregion menu and themes


    override fun onSaveInstanceState(outState: Bundle) {
        // Save the scores.
        outState.putInt(STATE_SCORE_1, score_1.integerText)
        outState.putInt(STATE_SCORE_2, score_2.integerText)
        super.onSaveInstanceState(outState)
    }


    private companion object {
        const val STATE_SCORE_1 = "Team 1 Score"
        const val STATE_SCORE_2 = "Team 2 Score"
    }
}