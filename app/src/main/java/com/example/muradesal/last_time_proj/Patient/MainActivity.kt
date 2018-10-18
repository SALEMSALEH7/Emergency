package com.example.muradesal.last_time_proj.Patient

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.muradesal.last_time_proj.ADMEN.adme_page
import com.example.muradesal.last_time_proj.HOSIPTAL.hospital_home_page
import com.example.muradesal.last_time_proj.R
import com.example.muradesal.last_time_proj.STTAF.emp_home_page
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    lateinit var my_auth:FirebaseAuth
    lateinit var edit:EditText
    lateinit var edit2:EditText
    lateinit var but:Button
    lateinit var but1:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        my_auth=FirebaseAuth.getInstance()
        edit=findViewById(R.id.user_name)
        edit2=findViewById(R.id.paswwd_sin)
        but=findViewById(R.id.loin_in)
        but1=findViewById(R.id.register)

        but1.setOnClickListener {
            var intent:Intent= Intent(this,Patient_registration::class.java)
            startActivity(intent)
        }
        but.setOnClickListener {
            val x=edit.text.toString()
            val y=edit2.text.toString()
            if(x.isEmpty()||y.isEmpty()){
                Toast.makeText(this," try agine ",Toast.LENGTH_LONG).show()
            }else
            {
                sing_in(x,y)

            }

        }
    }



    fun sing_in(emile:String,pass:String){



        var my_auth:FirebaseAuth= FirebaseAuth.getInstance()



        var type:String=""
        my_auth.signInWithEmailAndPassword(emile,pass).addOnCompleteListener(this, OnCompleteListener { task ->
            if(task.isSuccessful){


                var my_store:FirebaseFirestore= FirebaseFirestore.getInstance()
                var dp:DocumentReference=my_store.document("ADMEN/${my_auth.currentUser!!.uid}")
                dp.get().addOnSuccessListener(object :OnSuccessListener<DocumentSnapshot>{
                    override fun onSuccess(p0: DocumentSnapshot?) {
                        if(p0!!.exists()){
                            var x=p0.getString("admen_name")
                            if(emile==x){
                                        var intent:Intent= Intent(this@MainActivity, adme_page::class.java)
                                intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        startActivity(intent)
                                        Toast.makeText(this@MainActivity,"done " +emile,Toast.LENGTH_LONG).show()
                                    }
                        }
                    }

                })



                var my_store1:FirebaseFirestore= FirebaseFirestore.getInstance()
                var dp1:DocumentReference=my_store1.document("employees/${my_auth.currentUser!!.uid}")
                dp1.get().addOnSuccessListener(object :OnSuccessListener<DocumentSnapshot>{
                    override fun onSuccess(p0: DocumentSnapshot?) {
                        if(p0!!.exists()){

                            var x=p0.getString("emile_emp")

                            if(emile==x){

                                var intent:Intent= Intent(this@MainActivity, emp_home_page::class.java)
                                intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                                Toast.makeText(this@MainActivity,"done " +emile,Toast.LENGTH_LONG).show()
                            }
                        }
                    }

                })



                var my_store2:FirebaseFirestore= FirebaseFirestore.getInstance()
                var dp2:DocumentReference=my_store2.document("hospital/${my_auth.currentUser!!.uid}")
                dp2.get().addOnSuccessListener(object :OnSuccessListener<DocumentSnapshot>{
                    override fun onSuccess(p0: DocumentSnapshot?) {
                        if(p0!!.exists()){
                            var x=p0.getString("emile")

                            if(emile==x){

                                var intent:Intent= Intent(this@MainActivity, hospital_home_page::class.java)
                                intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                                Toast.makeText(this@MainActivity,"done " +emile,Toast.LENGTH_LONG).show()
                            }
                        }
                    }


                })



                var my_store3:FirebaseFirestore= FirebaseFirestore.getInstance()
                var dp3:DocumentReference=my_store3.document("Patient/${my_auth.currentUser!!.uid}")


                dp3.get().addOnSuccessListener(object :OnSuccessListener<DocumentSnapshot>{
                    override fun onSuccess(p0: DocumentSnapshot?) {
                        if(p0!!.exists()){
                            var x=p0.getString("emile")
                            if(emile==x){

                                var intent:Intent= Intent(this@MainActivity, Patient_after_home::class.java)
                                intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                                startActivity(intent)
                                Toast.makeText(this@MainActivity,"done " +emile,Toast.LENGTH_LONG).show()
                            }
                        }
                    }


                })


            }
            else{
                Toast.makeText(this,"filed " + task.exception?.message,Toast.LENGTH_LONG).show()
            }
        })
    }

    fun try_time(){

    }
}
