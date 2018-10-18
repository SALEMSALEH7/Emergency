package com.example.muradesal.last_time_proj.Patient

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.widget.*
import com.example.muradesal.last_time_proj.HOSIPTAL.hopital_patient_bik
import com.example.muradesal.last_time_proj.HOSIPTAL.hospital_list_info
import com.example.muradesal.last_time_proj.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_patient_after_home.*

class Patient_after_home : AppCompatActivity() {



    lateinit var but:ImageView
    lateinit var but1:ImageView
    lateinit var but2:ImageView
    lateinit var but3:ImageView
    lateinit var text1:TextView
    lateinit var text2:TextView
    lateinit var draw:DrawerLayout
    lateinit var tag:ActionBarDrawerToggle
    lateinit var loction_info:FusedLocationProviderClient

    val request_code=123
    var my_auth=FirebaseAuth.getInstance()
    var my_store=FirebaseFirestore.getInstance()
     var data_back:DocumentReference=my_store.collection("Patient").document(my_auth.currentUser!!.uid)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_after_home)
        //val toolbar: Toolbar = findViewById(R.id.toolbar)
        but=findViewById(R.id.order_car)
        but1=findViewById(R.id.blood_click)
        but2=findViewById(R.id.mark_gps)
        but3=findViewById(R.id.mark_point)
       text1=findViewById(R.id.text12)
        text2=findViewById(R.id.text121)
        draw=findViewById(R.id.drawer_layout)
        tag= ActionBarDrawerToggle(this,draw, R.string.open, R.string.close)
        draw.addDrawerListener(tag)
        tag.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        cheack_user()

        var x=intent.getStringExtra("emp_name")
        text1.text=x
        but1.setOnClickListener {

           var intent:Intent= Intent(this, blood_search::class.java)
            startActivity(intent)
        }
        but.setOnClickListener {

            my_location()
        }

        but2.setOnClickListener {

            gps_data()
        }

        but3.setOnClickListener {

            var intent:Intent= Intent(this, hospital_list_info::class.java)
            startActivity(intent)
        }


        // Initialize the action bar drawer toggle instance
        /*
        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
                this,
                draw,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        ){
            override fun onDrawerClosed(view: View){
                super.onDrawerClosed(view)
                //toast("Drawer closed")
            }

            override fun onDrawerOpened(drawerView: View){
                super.onDrawerOpened(drawerView)
                //toast("Drawer opened")
            }
        }
      drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        navigation_view.setNavigationItemSelectedListener{

            when(it.itemId){
                R.id.item2->go()
                R.id.item_help->gog()
                R.id.sing_out->sing_out()
            }
            draw.closeDrawer(GravityCompat.START)
            true
        }
        */
        navigation_view.setNavigationItemSelectedListener{

            when(it.itemId){
                R.id.item2 ->go()
                R.id.item_help ->gog()
                R.id.sing_out ->sing_out()
            }
            draw.closeDrawer(GravityCompat.START)
            true
        }


    }

    override fun onBackPressed() {

        super.onBackPressed()
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(tag.onOptionsItemSelected(item)){

            return true
        }
        return super.onOptionsItemSelected(item)
    }


    fun gog(){

        var intent:Intent= Intent(this, first_aid::class.java)
        startActivity(intent)

    }
    fun cheack_user(){

        val user=FirebaseAuth.getInstance().uid
        if(user==null){
            val intent=Intent(this, MainActivity::class.java)
            intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
    fun send(){

        var mintent:Intent=Intent(applicationContext, Patient_info_send::class.java)
        var x=text1.text.toString()
       mintent.putExtra("name",x)
        startActivity(mintent)

    }
    fun go(){

        var intent:Intent= Intent(this, second_aid::class.java)
        startActivity(intent)
    }
    fun sing_out(){

       var my_authe:FirebaseAuth
        my_authe=FirebaseAuth.getInstance()
        my_authe.signOut()
        finish()
    }

    fun gps_data(){


        //var intent:Intent= Intent(this,MapsActivity_place::class.java)
        //startActivity(intent)



        loction_info=LocationServices.getFusedLocationProviderClient(this)
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
                ==PackageManager.PERMISSION_GRANTED){
            loction_info.lastLocation.addOnSuccessListener {
                location ->
                if(location!==null){
                    var intent:Intent= Intent(this, MapsActivity_place::class.java)
                    startActivity(intent)

                } else{
                    AlertDialog.Builder(this).setTitle("الموقع")
                            .setMessage("الرجاء تشغيل الموقع")
                            .setPositiveButton("موافق",object :DialogInterface.OnClickListener{
                                override fun onClick(dialog: DialogInterface?, which: Int) {
                                    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                                }

                            }).setNegativeButton("غير موافق",object :DialogInterface.OnClickListener{
                                override fun onClick(dialog: DialogInterface?, which: Int) {
                                    Toast.makeText(this@Patient_after_home,"عفوا لايمكن عرض الخريطة",Toast.LENGTH_LONG).show()
                                }

                            }).show()
                }
            }
        }
        else{
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),request_code)
        }









/*
        var x="14.539569,49.121806"
        var y="14.544471، 49.125304"
        var f="14.548864,49.126269"
        //val gmmIntentUr3i = Uri.parse("google.navigation:q=14.558324,49.136397,14.539569,49.121806")
        val mapIntent =Intent(Intent.ACTION_VIEW,Uri.parse("google.navigation:q=$" + x + ","+y))
        mapIntent.`package` = "com.google.android.apps.maps"
        startActivity(mapIntent)
        */
    }

    fun my_data(){


        /*

        data_back.get().addOnSuccessListener(object :OnSuccessListener<DocumentSnapshot>{
            override fun onSuccess(p0: DocumentSnapshot) {
                    //var name=p0.getString("user_name")
                    //var phone=p0.getString("phone")
                    //var gender=p0.getString("GENDER")
                    //var dis=p0.getString("diseases")
                    //var blood=p0.getString("TYPE_OF_BLOOD")

                    var intent:Intent=Intent(applicationContext,hopital_patient_bik::class.java)
                   // var x_name=name
                   // var  x_phone=phone
                    //var x_gender=gender
                    //var x_dis=dis
                    //var x_blood=blood


                   // text1.text=name
                    //intent.putExtra("name",x_name)
                    //intent.putExtra("phone",x_phone)
                    //intent.putExtra("gender",x_gender)
                    //intent.putExtra("dis",x_dis)
                    //intent.putExtra("blood",x_blood)


             startActivity(intent)



            }


        })

        */

    }

    fun my_location(){




        loction_info=LocationServices.getFusedLocationProviderClient(this)

        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
                ==PackageManager.PERMISSION_GRANTED){

            loction_info.lastLocation.addOnSuccessListener { location ->
                if(location!==null){

                    Toast.makeText(this@Patient_after_home,"الرجاء اختيار المستشفى",Toast.LENGTH_LONG).show()
                    var intent:Intent=Intent(applicationContext, hopital_patient_bik::class.java)
                    startActivity(intent)

                    /*
                    data_back.get().addOnSuccessListener(object :OnSuccessListener<DocumentSnapshot>{
                        override fun onSuccess(p0: DocumentSnapshot) {
                            var name=p0.getString("user_name")
                            var phone=p0.getString("phone")
                            var gender=p0.getString("GENDER")
                            var dis=p0.getString("diseases")
                            var blood=p0.getString("TYPE_OF_BLOOD")

                            //var intent:Intent=Intent(applicationContext,Patient_info_send::class.java)
                            var x_name=name
                            var  x_phone=phone
                            var x_gender=gender
                            var x_dis=dis
                            var x_blood=blood
                            val x=location.longitude.toString()
                            val y=location.latitude.toString()

                            //text1.text=name
                           // intent.putExtra("name",x_name)
                           // intent.putExtra("phone",x_phone)
                           // intent.putExtra("gender",x_gender)
                            // intent.putExtra("dis",x_dis)
                            //intent.putExtra("blood",x_blood)
                            ///intent.putExtra("x_x",x)
                           // intent.putExtra("y_y",y)
                            //text1.text=location.longitude.toString()
                            //text2.text=location.latitude.toString()

                            //val xy=text1.text.toString()
                            //var f=text1.text.toString()
                            //intent.putExtra("ss",xy)
                            //intent.putExtra("x_x",x)
                            //intent.putExtra("y_y",y)
                            //my_data()
                            startActivity(intent)
                        }


                    })
                    */
                   // my_data()

                }
                else{
                    AlertDialog.Builder(this).setTitle("الموقع")
                            .setMessage("الرجاء تشغيل الموقع")
                            .setPositiveButton("موافق",object :DialogInterface.OnClickListener{
                                override fun onClick(dialog: DialogInterface?, which: Int) {
                                    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                                }

                            }).setNegativeButton("غير موافق",object :DialogInterface.OnClickListener{
                                override fun onClick(dialog: DialogInterface?, which: Int) {
                                    Toast.makeText(this@Patient_after_home,"عفوا لايمكن الاستمرار",Toast.LENGTH_LONG).show()
                                }

                            }).show()
                }
            }
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),request_code)
        }

    }


    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode){
            request_code->{
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    loction_info.lastLocation.addOnSuccessListener { location ->

                        text1.text=location.longitude.toString()
                       text2.text=location.latitude.toString()


                    }
                }


                else{
                    Toast.makeText(this,"الرجاء المحاولة مرة اخرى",Toast.LENGTH_LONG).show()
                }
            }

        }
    }


}

