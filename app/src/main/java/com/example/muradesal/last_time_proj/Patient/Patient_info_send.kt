package com.example.muradesal.last_time_proj.Patient

import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.muradesal.last_time_proj.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.HashMap

class Patient_info_send : AppCompatActivity() {

    lateinit var loction_info: FusedLocationProviderClient
   var my_auth=FirebaseAuth.getInstance()
    var my_store=FirebaseFirestore.getInstance()
    var data_back:DocumentReference=my_store.collection("Patient").document(my_auth.currentUser!!.uid)
    lateinit var text:TextView
    lateinit var text1:TextView
    lateinit var text2:TextView
    lateinit var text3:TextView
    lateinit var text4:TextView
    lateinit var text5:TextView
    lateinit var text6:TextView
    lateinit var text7:TextView
    lateinit var text8:TextView
    lateinit var but:Button
    lateinit var but1:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_info_send)
        text7=findViewById(R.id.me_hopit)
        text=findViewById(R.id.me_name)
        text1=findViewById(R.id.me_phone)
        text2=findViewById(R.id.me_blood)
        text3=findViewById(R.id.me_gender)
        text4=findViewById(R.id.me_dis)
        text5=findViewById(R.id.me_x)
        text6=findViewById(R.id.me_y)
        text8=findViewById(R.id.me_time)
        but=findViewById(R.id.last_step)
        but1=findViewById(R.id.items7)

        val x=intent.getStringExtra("gmm")
        text7.text=" اسم المستشفى :"+ x
        val y=Calendar.getInstance().time.toLocaleString()
        text8.text=y
        get_data()



        but1.setOnClickListener {

           var note=HashMap<Any,Any>()
            val hos_name=text7.text.toString()
            val x=text5.text.toString()
            val y=text6.text.toString()
            val data=text8.text.toString()

            var my_data= FirebaseDatabase.getInstance().getReference("order")
            var data_Id=my_data.push().key
            val xx=""


            var x_other="hos_name"
            val x_other1="pat_long"
            val y_other2="pat_lat"
            val x_data_other="data"
            val xx_name="pat_name"
            val x_phone="pat_phone"
            val x_gender="pat_gender"
            val x_dis="pat_dis"
            val x_blood="pat_blood"

            note.put(x_other,hos_name)
            note.put(x_other1,x)
            note.put(y_other2,y)
            note.put(x_data_other,data)
            note.put(xx_name,xx)
            note.put(x_phone,xx)
            note.put(x_gender,xx)
            note.put(x_dis,xx)
            note.put(x_blood,xx)
            my_store.collection("orders").document(data_Id).set(note).addOnCompleteListener {

                task ->if(task.isSuccessful){

                Toast.makeText(this,"تم ارسال طلب الطوراىء لشخص اخر",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"خطا في ..." + task.exception?.message,Toast.LENGTH_LONG).show()
            }


            }


        }


        but.setOnClickListener {

           // text.text=x_name
            //text1.text=x_phone
            //text2.text=x_gender
            //text3.text=x_dis
            //text4.text=x_blood
            //text5.text=x
            //text6.text=y
            val note = HashMap<Any,Any>()
            val hos_name=text7.text.toString()
            val p_name=text.text.toString()
            val p_phone=text1.text.toString()
            val p_gender=text2.text.toString()
            val p_dis=text3.text.toString()
            val p_blood=text4.text.toString()
            val x=text5.text.toString()
            val y=text6.text.toString()
            val data=text8.text.toString()
            var my_data= FirebaseDatabase.getInstance().getReference("order")
            var data_Id=my_data.push().key

            val x_name="hos_name"
            val xx_name="pat_name"
            val x_phone="pat_phone"
            val x_gender="pat_gender"
            val x_dis="pat_dis"
            val x_blood="pat_blood"
            val x_long="pat_long"
            val y_lat="pat_lat"
            val x_data="data"
            note.put(x_name,hos_name)
            note.put(xx_name,p_name)
            note.put(x_phone,p_phone)
            note.put(x_gender,p_gender)
            note.put(x_dis,p_dis)
            note.put(x_blood,p_blood)
            note.put(x_long,x)
            note.put(y_lat,y)
            note.put(x_data,data)

            my_store.collection("orders").document(data_Id).set(note).addOnCompleteListener {

                task ->if(task.isSuccessful){

                Toast.makeText(this,"تم ارسال الطلب",Toast.LENGTH_LONG).show()
            }
                else{
                Toast.makeText(this,"خطا في ..." + task.exception?.message,Toast.LENGTH_LONG).show()
            }


            }



        }


        /*
        but1.setOnClickListener {

            val x=text.text.toString()
            //val y=text1.text.toString()

            var intent:Intent= Intent(this,hospital_notfi::class.java)
            intent.putExtra("go",x)
            //intent.putExtra("went",y)
            startActivity(intent)

        }

*/
    }
    fun get_data(){


        loction_info= LocationServices.getFusedLocationProviderClient(this)

        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){

            loction_info.lastLocation.addOnSuccessListener { location ->
                if(location!==null){
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
                           text.text="اسم المريض :" + x_name
                            text1.text="رقم المريض :"+x_phone
                            text2.text="جنس المريض : " + x_gender
                            text3.text=x_dis
                            text4.text="فصيلة دم المريض :" +x_blood
                            text5.text= x
                            text6.text= y
                        }


                    })
                    // my_data()

                }

            }
        }

    }


    }

