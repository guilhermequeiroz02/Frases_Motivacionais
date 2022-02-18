package com.example.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.motivation.R
import com.example.motivation.infra.MotivationConstantes
import com.example.motivation.infra.SecurityPreferences
import kotlinx.android.synthetic.main.activity_letsstart.*

class LetsStartActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_letsstart)

        mSecurityPreferences = SecurityPreferences(this)

        supportActionBar?.hide()

        btSave.setOnClickListener(this)

        verifyName()

        val securityPreferences = SecurityPreferences(this)
        securityPreferences.storeString("", "")
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.btSave) {
            handleSave()
        }
    }

    private fun handleSave() {
        val name = EtName.text.toString()

        if (name != "") {
            mSecurityPreferences.storeString(MotivationConstantes.KEY.PERSON_NAME, name)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Informe seu nome!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun verifyName() {
        val name = mSecurityPreferences.getString(MotivationConstantes.KEY.PERSON_NAME)
        if (name != "") {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}