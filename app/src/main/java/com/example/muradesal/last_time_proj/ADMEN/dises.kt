package com.example.muradesal.last_time_proj.ADMEN

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.muradesal.last_time_proj.R
import com.google.firebase.firestore.FirebaseFirestore

class dises : AppCompatActivity() {

    lateinit var edit1:EditText
    lateinit var but:Button
    lateinit var my_store:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dises)
        my_store= FirebaseFirestore.getInstance()
        edit1=findViewById(R.id.dies)
        but=findViewById(R.id.add_dis_item)

        but.setOnClickListener {

            var x=edit1.text.toString()
            if(x.isEmpty()){
                Toast.makeText(this," يرجىء ملى الحقل",Toast.LENGTH_LONG).show()
            }
            else{
                save_data()
            }
        }
    }

    fun save_data(){

        var x=edit1.text.toString()
        var xx="dis_name"

        var note=HashMap<Any,Any>()

        note.put(xx,x)


        my_store.collection("dis").document().set(note).addOnCompleteListener { task ->
            if(task.isSuccessful){
                Toast.makeText(this,"تم الحفظ",Toast.LENGTH_LONG).show()
            }else{

                Toast.makeText(this,"هنالك خطا في"+task.exception!!.message,Toast.LENGTH_LONG).show()
            }
        }
    }
}
