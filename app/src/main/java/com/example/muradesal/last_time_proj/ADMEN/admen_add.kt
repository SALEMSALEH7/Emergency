package com.example.muradesal.last_time_proj.ADMEN

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.muradesal.last_time_proj.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class admen_add : AppCompatActivity() {

    lateinit var edit1:EditText
    lateinit var edit2:EditText
    lateinit var edit3:EditText
    lateinit var text1:TextView
    lateinit var but:Button
    lateinit var my_auth:FirebaseAuth
    lateinit var my_store:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admen_add)
        edit1=findViewById(R.id.admen_emile)
        edit2=findViewById(R.id.admen_name)
        edit3=findViewById(R.id.admen_pass)
        text1=findViewById(R.id.admen_text)
        but=findViewById(R.id.admen_save)
        my_auth=FirebaseAuth.getInstance()
        my_store= FirebaseFirestore.getInstance()
        but.setOnClickListener {

            var x=edit1.text.toString()
            var y=edit3.text.toString()

            if(x.isEmpty()||y.isEmpty()){
                Toast.makeText(this,"empity file", Toast.LENGTH_LONG).show()
            }else{
                save_data(x,y)
            }


        }
    }

    fun save_data(emile:String,pass:String){
        my_auth.createUserWithEmailAndPassword(emile,pass).addOnCompleteListener { task ->
            if(task.isSuccessful){

                val y_name=edit1.text.toString()
                val y_emlie=edit2.text.toString()
                val y_pass=edit3.text.toString()
                //val y_type=text1.text.toString()

                val x_name="admen_name"
                val x_passs="admen_pass"
                //val x_type="type"
                val x_emile="emile"

                var note=HashMap<Any,Any>()
                note.put(x_name,y_name)
                note.put(x_passs,y_pass)
                note.put(x_emile,y_emlie)
                //note.put(x_type,y_type)
                my_store.collection("ADMEN").document(my_auth.currentUser!!.uid).set(note)
                Toast.makeText(this,"تم حفظ البيانات بنجاح", Toast.LENGTH_LONG).show()

            }else{

                Toast.makeText(this,"file"+task.exception!!.message, Toast.LENGTH_LONG).show()
            }
        }


    }
}
