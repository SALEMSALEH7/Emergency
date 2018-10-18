package com.example.muradesal.last_time_proj.Patient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.muradesal.last_time_proj.R

class first_aid : AppCompatActivity() {

    lateinit var r1:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_aid)
        r1=findViewById(R.id.recy_item1)
        r1.layoutManager=LinearLayoutManager(this)
        r1.adapter= items1()
    }
}
