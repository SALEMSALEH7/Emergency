package com.example.muradesal.last_time_proj.Patient

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.*
import com.example.muradesal.last_time_proj.R
import com.example.muradesal.last_time_proj.database.Patient
import com.example.muradesal.last_time_proj.database.des_hospital
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import java.util.*
import kotlin.collections.ArrayList

class Patient_registration : AppCompatActivity() {

    var myAuth= FirebaseAuth.getInstance()
    var my_store_DB= FirebaseFirestore.getInstance()
    var db:DocumentReference=my_store_DB.collection("dis").document()


    lateinit var users_Id:String
    lateinit var edit_text1: EditText
    lateinit var edit_text2: EditText
    lateinit var edit_text3: TextView
    lateinit var edit_text4: EditText
    lateinit var edit5_blood: EditText
    lateinit var text_blood: TextView
    lateinit var text_list: TextView
    lateinit var text_gend:TextView
    lateinit var text_121: TextView
    lateinit var radio_group: RadioGroup
    lateinit var radioButton: RadioButton
    lateinit var radioButton1: RadioButton

    lateinit var list_items_des:ArrayList<des_hospital>
    lateinit var but: Button
    lateinit var dis_list:ArrayList<des_hospital>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_registration)

        edit_text1 = findViewById(R.id.user_name_s)
        edit_text2= findViewById(R.id.emile_t)
        edit_text3 = findViewById(R.id.phone_number)
        edit_text4 = findViewById(R.id.passowrd_t)
        text_blood=findViewById(R.id.blood_sing2)
        radio_group = findViewById(R.id.rad_group)
        but = findViewById(R.id.back)
        text_list=findViewById(R.id.sick_list)
        text_gend=findViewById(R.id.text_gender)

        list_items_des=ArrayList()
        dis_list= ArrayList()
        //radioButton=findViewById(R.id.female_radio)
        //radioButton1=findViewById(R.id.men_radio)

        var x= arrayOf("d","d")



        text_list.setOnClickListener {
            // here i try to get it from the database

            //data()




            // build the small half secream of disseases
            val bilder= AlertDialog.Builder(this@Patient_registration)
            // in here we have array of disses
            val disseases_list= arrayOf("لاشيء","السكر","ضغط الدم","الصرع","حرقة المعدة","احتياجات خاصة"," سرطان الدم ","سرطان الثدي","سرطان المعدة","فشل كلوي")
            // know to but something cheack for the system so it can abar on database as string
            // also mean but default value that mathe the array above
            val cheak_array= booleanArrayOf(true,false,false,false,false,false,false,false,false,false)
            // know covert the array into list
            val list_items= Arrays.asList(*disseases_list)
            bilder.setTitle("الامراض المزمنة")
            // something need to search about it
            bilder.setMultiChoiceItems(disseases_list,cheak_array){ dialog: DialogInterface?, which: Int, isChecked: Boolean ->
                //now its will update the choies insted of the one that the system build
                cheak_array[which]=isChecked
                //now get the new values
                //val choies_item=list_items[which]
            }
            bilder.setPositiveButton("موافق"){ dialog: DialogInterface, which:Int ->
                //konw after click this new button OK what do i want to happen
                text_list.text="لديك امراض مزمنة: \n"
                for (i in cheak_array.indices){
                    val cheak_bik=cheak_array[i]
                    if(cheak_bik){
                        text_list.text=text_list.text.toString() + disseases_list[i] + " "

                    }
                }
            }
            // for cansel button
            bilder.setNeutralButton("الغاء"){dialog, which ->

                dialog.dismiss()
            }
            // made this dialog
            val dialog=bilder.create()
            // know show it
            dialog.show()



              // build the small half secream of disseases



        }

        text_blood.setOnClickListener {

            val bulider = AlertDialog.Builder(this@Patient_registration).setTitle("فصيلة الدم")
            val blood_type = arrayOf("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-")
            bulider.setSingleChoiceItems(blood_type, -1) { dialog: DialogInterface?, i: Int ->


                text_blood.text = blood_type[i]
                dialog?.dismiss()

            }

            bulider.setNegativeButton("الغاء") { dialog: DialogInterface?, which: Int ->
                dialog?.dismiss()


            }
            val close=bulider.create()
            close.show()

        }







        but.setOnClickListener {



            val emilee=edit_text2.text.toString()
            val pasw=edit_text4.text.toString().trim()


            if(emilee.isEmpty()){
                Toast.makeText(this,"الرجاء ملىء الصناديق", Toast.LENGTH_LONG).show()
            }
            else{

                register(emilee,pasw)

            }




        }

    }

    private fun register(emile:String,password:String){
        // intent:Intent=intent
        myAuth.createUserWithEmailAndPassword(emile,password)
                .addOnCompleteListener(this, OnCompleteListener {task ->
                    if (task.isSuccessful) {

                        val username=edit_text1.text.toString().trim()
                        val passwordd=edit_text4.text.toString().trim()
                        val emilee=edit_text2.text.toString().trim()
                        val phone=edit_text3.text.toString().trim()
                        var radio=radio_group.checkedRadioButtonId
                        var xx=findViewById<RadioButton>(radio)
                        text_gend.text=xx.text
                        val gend=text_gend.text.toString().trim()
                        val text_items=text_list.text.toString().trim()
                        val blood=text_blood.text.toString()


                        // this how i choiec to save it in the cloud fun
                        // doc child name
                        val user_name1="user_name"
                        val password1="Passwwrd"
                        val emile1="emile"
                        val phone1="phone"
                        val bik="GENDER"
                        val sick="diseases"
                        val blood1="TYPE_OF_BLOOD"
                        val user_id="id"

                        val note = HashMap<Any,Any>()
                        note.put(user_name1,username)
                        note.put(password1,passwordd)
                        note.put(emile1,emilee)
                        note.put(phone1,phone)
                        note.put(bik,gend)
                        note.put(sick,text_items)
                        note.put(blood1,blood)
                        note.put(user_id, myAuth.currentUser!!.uid)
                        if(username.isEmpty()||(password.isEmpty())||(blood.isEmpty())){

                                    Toast.makeText(this,"يجب ملىء الحقول للتسجيل",Toast.LENGTH_LONG).show()
                                }else {



                            my_store_DB.collection("Patient").document(myAuth.currentUser!!.uid).set(note).addOnCompleteListener { task ->
                                if (task.isSuccessful) {

                                    // this only for search i use to save it in the real time database
                                    val r_data = FirebaseDatabase.getInstance().getReference("Patient")
                                    val pati = r_data.push().key
                                    val patine_da = Patient(blood, username, phone)
                                    r_data.child(pati).setValue(patine_da).addOnCompleteListener {

                                        task ->
                                        if (task.isSuccessful) {

                                            Toast.makeText(this, "تم التسجيل", Toast.LENGTH_LONG).show()
                                            var intent:Intent=Intent(this,Patient_after_home::class.java)
                                            startActivity(intent)
                                        }

                                    }


                                } else {

                                    Toast.makeText(this, "الرجاء المحاولة", Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                        Toast.makeText(this,"اهلا " + username, Toast.LENGTH_LONG).show()

                    }
                    else{
                        //need to to do check here
                        Toast.makeText(this,"خطا في... " +task.exception?.message, Toast.LENGTH_LONG).show()

                    }

                })
    }

}

private fun Any.setMultiChoiceItems(list_items: List<ArrayList<des_hospital>>, cheak_array: BooleanArray, function: (DialogInterface?, Int, Boolean) -> Unit) {

}


