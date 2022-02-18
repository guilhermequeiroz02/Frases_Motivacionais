package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.motivation.R
import com.example.motivation.infra.MotivationConstantes
import com.example.motivation.infra.SecurityPreferences
import com.example.motivation.repository.Mock
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences: SecurityPreferences
    private var mPhraseFilter: Int = MotivationConstantes.PHRASEFILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        mSecurityPreferences = SecurityPreferences(this)
        val name = mSecurityPreferences.getString(MotivationConstantes.KEY.PERSON_NAME)
        textName.text = "Olá, $name!"

        imageAll.setColorFilter(resources.getColor(R.color.darkGrey))
        handleNewPhrase()

        btNewPhrase.setOnClickListener(this)
        imageAll.setOnClickListener(this)
        imageHappy.setOnClickListener(this)
        imageMorning.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        val id = view.id

        val listFilter = listOf(R.id.imageAll, R.id.imageHappy, R.id.imageMorning)

        if (id == R.id.btNewPhrase) {
            handleNewPhrase()
        } else if (id in listFilter) {
            handleFilter(id)
        }
    }

    private fun handleFilter(id: Int) {

        imageAll.setColorFilter(resources.getColor(R.color.black))
        imageHappy.setColorFilter(resources.getColor(R.color.black))
        imageMorning.setColorFilter(resources.getColor(R.color.black))



        when (id) {
            R.id.imageAll -> {
                imageAll.setColorFilter(resources.getColor(R.color.darkGrey))
                mPhraseFilter = MotivationConstantes.PHRASEFILTER.ALL
            }

            R.id.imageHappy -> {
                imageHappy.setColorFilter(resources.getColor(R.color.darkGrey))
                mPhraseFilter = MotivationConstantes.PHRASEFILTER.HAPPY
            }

            R.id.imageMorning -> {
                imageMorning.setColorFilter(resources.getColor(R.color.darkGrey))
                mPhraseFilter = MotivationConstantes.PHRASEFILTER.MORNING
            }
        }
    }

    private fun handleNewPhrase() {
        textPhrase.text = Mock().getPhrase(mPhraseFilter)
    }

}