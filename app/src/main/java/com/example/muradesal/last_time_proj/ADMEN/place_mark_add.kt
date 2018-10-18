package com.example.muradesal.last_time_proj.ADMEN

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.muradesal.last_time_proj.R
import com.google.firebase.firestore.FirebaseFirestore

class place_mark_add : AppCompatActivity() {


    lateinit var edit:EditText
    lateinit var edit2:EditText
    lateinit var edit3:EditText
    lateinit var edit4:EditText
    lateinit var but:Button
   lateinit var my_store:FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_mark_add)

        my_store= FirebaseFirestore.getInstance()
        edit=findViewById(R.id.place_name)
        edit2=findViewById(R.id.place_des)
        edit3=findViewById(R.id.place_lat)
        edit4=findViewById(R.id.place_long)
        but=findViewById(R.id.place_save_button)
        but.setOnClickListener {

           save_data()
        }
        /*
        edit3.setOnClickListener {

            val gmmIntentUr3i = Uri.parse("google.navigation:q=14.550729,49.126036")
            val mapIntent = Intent(Intent.ACTION_VIEW,gmmIntentUr3i)
            mapIntent.`package` = "com.google.android.apps.maps"
            startActivity(mapIntent)

        }*/


    }

    fun save_data(){



        var name=edit.text.toString()
        var des=edit2.text.toString()
        var lat=edit3.text.toString()
        var long=edit4.text.toString()
        if(name.isEmpty()||(des.isEmpty())){
                    Toast.makeText(this,"هنالك حقل فارغ ",Toast.LENGTH_LONG).show()
                }else{


            val x_name="place_name"
            val x_des="place_des"
            val x_lat="place_lat"
            val x_long="place_long"

            var note=HashMap<Any,Any>()
            note.put(x_name,name)
            note.put(x_des,des)
            note.put(x_lat,lat)
            note.put(x_long,long)
            my_store.collection("Place_Mark").document().set(note).addOnCompleteListener {
                task ->if(task.isSuccessful){
                Toast.makeText(this,"تم الحفظ",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this," "+ task.exception?.message,Toast.LENGTH_LONG).show()
            }
            }
        }
    }
}
