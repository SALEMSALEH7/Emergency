package com.example.muradesal.last_time_proj.ADMEN

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import com.example.muradesal.last_time_proj.R
import com.example.muradesal.last_time_proj.HOSIPTAL.hospital_registration

class adme_page : AppCompatActivity() {

    lateinit var but1:Button
    lateinit var but2:Button
    lateinit var but3:Button
    lateinit var but4:Button
    lateinit var web_page:WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adme_page)
        but1=findViewById(R.id.admen_1)
        but2=findViewById(R.id.admen_2)
        but3=findViewById(R.id.admen_3)
        but4=findViewById(R.id.admen_4)
        web_page=findViewById(R.id.web)
        but1.setOnClickListener {

            var intent:Intent= Intent(this, admen_add::class.java)
            startActivity(intent)
        }
        but2.setOnClickListener {

            var intent:Intent= Intent(this, hospital_registration::class.java)
            startActivity(intent)

        }
        but3.setOnClickListener {

            web_page.loadUrl("https://console.firebase.google.com/project/realdatebase/database/firestore/data~2FPatient")


        }

        but4.setOnClickListener {


            map_marker()
        }
    }

    fun map_marker(){


        var intent:Intent= Intent(this, place_mark_add::class.java)
        startActivity(intent)
    }
}
