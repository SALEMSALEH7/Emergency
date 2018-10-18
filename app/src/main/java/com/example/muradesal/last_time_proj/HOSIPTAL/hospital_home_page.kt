package com.example.muradesal.last_time_proj.HOSIPTAL

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.muradesal.last_time_proj.ADMEN.dises
import com.example.muradesal.last_time_proj.R

class hospital_home_page : AppCompatActivity() {

    lateinit var but:Button
    lateinit var but1:Button
    lateinit var but2:Button
    lateinit var but3:Button
    lateinit var but4:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospital_home_page)
        but=findViewById(R.id.hospital_emp)
        but1=findViewById(R.id.order_take)
        but2=findViewById(R.id.revire_hos)
        but3=findViewById(R.id.action1)
        but4=findViewById(R.id.action2)

        but.setOnClickListener {

            var intent:Intent= Intent(this, hospital_emp_rigst::class.java)
            startActivity(intent)
        }
        but1.setOnClickListener {

            var intent:Intent= Intent(this, hospital_note::class.java)
            startActivity(intent)
        }
        but2.setOnClickListener {

            var intent:Intent= Intent(this, hospital_notfi::class.java)
            startActivity(intent)
        }
        but3.setOnClickListener {


            var intent:Intent= Intent(this, dises::class.java)
            startActivity(intent)
        }
        but4.setOnClickListener {

            var intent:Intent= Intent(this, hospital_sector::class.java)
            startActivity(intent)
        }
    }
}
