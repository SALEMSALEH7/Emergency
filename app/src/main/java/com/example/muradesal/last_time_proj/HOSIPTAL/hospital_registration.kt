package com.example.muradesal.last_time_proj.HOSIPTAL

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.muradesal.last_time_proj.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.HashMap

class hospital_registration : AppCompatActivity() {

    lateinit var my_aut:FirebaseAuth
    lateinit var my_store:FirebaseFirestore
    lateinit var edit1:EditText
    lateinit var edit2:EditText
    lateinit var edit3:EditText
    lateinit var edit4:EditText
    lateinit var but:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospital_registration)
        my_aut=FirebaseAuth.getInstance()
        my_store= FirebaseFirestore.getInstance()

        edit1=findViewById(R.id.emile_hos)
        edit2=findViewById(R.id.name_hos)
        edit3=findViewById(R.id.location_hospi)
        edit4=findViewById(R.id.pass_hospital)
        but=findViewById(R.id.adding_it)

        but.setOnClickListener {

            val x=edit1.text.toString()
            val y=edit4.text.toString()
            if(x.isEmpty()||y.isEmpty()){
                Toast.makeText(this," emity filed",Toast.LENGTH_LONG).show()
            }else{
                sava_data(x,y)
            }
        }
    }

    fun sava_data(emile:String,pass:String){

        my_aut.createUserWithEmailAndPassword(emile,pass).addOnCompleteListener(this, OnCompleteListener { task ->

            if(task.isSuccessful){

                val emile1=edit1.text.toString()
                val password=edit4.text.toString()
                val loc=edit3.text.toString()
                val name1=edit2.text.toString()

                val emile11="emile"
                val passs="password"
                val loca="loc"
                val name11="name"
                val id="id"
                val des="des"

                val note = HashMap<Any,Any>()
                note.put(emile11,emile1)
                note.put(passs,password)
                note.put(loca,loc)
                note.put(name11,name1)
                note.put(des,"")
                note.put(id,my_aut.currentUser!!.uid)
                my_store.collection("hospital").document(my_aut.currentUser!!.uid).set(note)

                Toast.makeText(this,"تم تسجيل مستشفى جديد",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"not fit" + task.exception!!.message,Toast.LENGTH_LONG).show()
            }
        })

    }
}
