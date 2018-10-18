package com.example.muradesal.last_time_proj.STTAF

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.muradesal.last_time_proj.R


class emp_home_page : AppCompatActivity() {


    lateinit var but1:Button
    lateinit var but2:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emp_home_page)
        but1=findViewById(R.id.emp_page1)
        but2=findViewById(R.id.emp_page2)


        but1.setOnClickListener {

            var intent:Intent=Intent(this, employes_page::class.java)
            startActivity(intent)
        }

      /*  but2.setOnClickListener {

            var intent:Intent=Intent(this,hospital_note::class.java)
            startActivity(intent)
        }
        */
    }
}
