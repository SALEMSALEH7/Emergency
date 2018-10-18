package com.example.muradesal.last_time_proj.HOSIPTAL

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.muradesal.last_time_proj.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class hospital_sector : AppCompatActivity() {

    lateinit var edit:EditText
    lateinit var but:Button
    lateinit var my_store:FirebaseFirestore
    lateinit var my_auth:FirebaseAuth
    lateinit var db:DocumentReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospital_sector)
        edit=findViewById(R.id.descrip_hos)
        but=findViewById(R.id.des_sing)

        but.setOnClickListener {

            save_data()
        }
    }

    fun save_data(){

        my_auth=FirebaseAuth.getInstance()
        my_store= FirebaseFirestore.getInstance()
        db=my_store.collection("hospital").document(my_auth.currentUser!!.uid)
        if(edit.text.isEmpty()){
            Toast.makeText(this,"لا يمكنك الاضافة الحقل فارغ",Toast.LENGTH_LONG).show()
        }else{

            var x_des=edit.text.toString()
            var x_dess="des"
            var note=HashMap<Any,Any>()
            note.put(x_dess,x_des)

          db.update(x_dess,x_des).addOnCompleteListener { task ->

              if(task.isSuccessful){
                  Toast.makeText(this,"تم اضافة القسم" ,Toast.LENGTH_LONG).show()
              }else{
                  Toast.makeText(this,"هنالك خطا في " + task.exception!!.message ,Toast.LENGTH_LONG).show()
              }
          }
        }

    }
}
