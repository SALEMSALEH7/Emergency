package com.example.muradesal.last_time_proj.HOSIPTAL

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.muradesal.last_time_proj.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class hospital_emp_rigst : AppCompatActivity() {

    lateinit var edit:EditText
    lateinit var edit1:EditText
    lateinit var edit2:EditText
    lateinit var edit3:EditText
    lateinit var text1:TextView
    lateinit var but:Button
    var my_auth=FirebaseAuth.getInstance()
    lateinit var my_store:FirebaseFirestore
    var my_store1=FirebaseFirestore.getInstance()
    var db:DocumentReference=my_store1.collection("hospital").document(my_auth.currentUser!!.uid)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospital_emp_rigst)
        edit=findViewById(R.id.emp_user)
        edit1=findViewById(R.id.emp_phone)
        edit2=findViewById(R.id.emp_name)
        edit3=findViewById(R.id.emp_password)
        text1=findViewById(R.id.emp_hopital)
        my_store=FirebaseFirestore.getInstance()
        but=findViewById(R.id.em_save)

       // my_store= FirebaseFirestore.getInstance()

       db=my_store.collection("hospital").document(my_auth.currentUser!!.uid)
        db.get().addOnSuccessListener(object : OnSuccessListener<DocumentSnapshot> {
            override fun onSuccess(p0: DocumentSnapshot) {
                if(p0.exists()){

                    var name=p0.getString("name")
                    text1.text=name


                }else{
                    Toast.makeText(this@hospital_emp_rigst,"not found",Toast.LENGTH_LONG).show()
                }
            }


        })

        but.setOnClickListener {

            val x=edit.text.toString()
            val y=edit3.text.toString()

            if(x.isEmpty()||y.isEmpty()){
                Toast.makeText(this,"filed empty",Toast.LENGTH_LONG).show()
            }else{

                data_save(x,y)
            }

        }
    }
    fun data_save(emile:String,password:String){

        my_auth.createUserWithEmailAndPassword(emile,password).addOnCompleteListener(this, OnCompleteListener {
            task ->

            if(task.isSuccessful){

                val emile1="emile_emp"
                val name="name_emp"
                val phone="phone_emp"
                val pass="password_emp"
                val hopit="hopital_name"

                val emile2=edit.text.toString()
                val name1=edit2.text.toString()
                val phone1=edit1.text.toString()
                val passs=edit3.text.toString()
                val hoit=text1.text.toString()

                val note=HashMap<Any,Any>()
                note.put(emile1,emile2)
                note.put(name,name1)
                note.put(phone,phone1)
                note.put(pass,passs)
                note.put(hopit,hoit)

                my_store.collection("employees").document(my_auth.currentUser!!.uid).set(note)

                Toast.makeText(this@hospital_emp_rigst,"تم تسجيل الموظف " + name1,Toast.LENGTH_LONG).show()

            }
            else{
                Toast.makeText(this@hospital_emp_rigst," worng " +task.exception!!.message,Toast.LENGTH_LONG).show()
            }



        })
    }
}
