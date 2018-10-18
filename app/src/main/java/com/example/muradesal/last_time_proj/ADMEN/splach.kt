package com.example.muradesal.last_time_proj.ADMEN

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.example.muradesal.last_time_proj.Patient.MainActivity
import com.example.muradesal.last_time_proj.R

class splach : AppCompatActivity() {

    lateinit var img:ImageView
    lateinit var text:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splach)
        img=findViewById(R.id.splach_img)
        text=findViewById(R.id.splach_text)
        var anami:Animation=AnimationUtils.loadAnimation(this, R.anim.spalch_tools)
        img.startAnimation(anami)
        text.startAnimation(anami)



        var x=thread()
        x.start()
    }


    inner class thread:Thread(){
        override fun run() {

            try {
                sleep(5000)
            }
            catch (e:InterruptedException){

                e.printStackTrace()
            }
            finally {
                var intentx:Intent= Intent(this@splach, MainActivity::class.java)
                startActivity(intentx)

                finish()
            }

        }

    }


}
