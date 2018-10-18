package com.example.muradesal.last_time_proj.Patient

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.muradesal.last_time_proj.R
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class phone_auth : AppCompatActivity() {

    var v_id=""
    lateinit var m_auth: FirebaseAuth
    lateinit var edit1: EditText
    lateinit var edit2: EditText
    lateinit var text:TextView
    lateinit var but: Button
    lateinit var but1: Button
    lateinit var m_callls : PhoneAuthProvider.OnVerificationStateChangedCallbacks
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_auth)
        m_auth= FirebaseAuth.getInstance()
        edit1=findViewById(R.id.auth1)
        edit2=findViewById(R.id.auth2)
        but=findViewById(R.id.auth3)
        but1=findViewById(R.id.v_code)
        text=findViewById(R.id.phone_area)


        but1.setOnClickListener {
            verify()
        }
        but.setOnClickListener {

            val auth_code=edit2.text.toString()
            if(auth_code.isEmpty()) {
                Toast.makeText(this, "الرجاء ملىء الصناديق", Toast.LENGTH_LONG).show()
            }

            else{
                auth()
            }
        }

    }



    fun Verify_callback() {
        m_callls= object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                Log.d("","done" + p0)

                singin(p0)


            }

            override fun onVerificationFailed(p0: FirebaseException) {
                Log.w("", "onVerificationFailed", p0)



            }

            override fun onCodeSent(p0: String?, p1: PhoneAuthProvider.ForceResendingToken?) {
                super.onCodeSent(p0, p1)
                v_id=p0.toString()
            }

        }
    }


    fun verify(){


        //





        val phone_area=text.text.toString()
        val phonenumber=edit1.text.toString()

        try {
            if (phonenumber.isEmpty()) {
                Toast.makeText(this, "خطا ماززز", Toast.LENGTH_LONG).show()
            } else {

                Verify_callback()
                intent.putExtra("phone",phonenumber)
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        phone_area+phonenumber, // the tvlaue need to verity
                        60, // the time by seoend
                        TimeUnit.SECONDS,
                        this,
                        m_callls

                )

                Toast.makeText(this, "تم ارسال رمز التحقق", Toast.LENGTH_LONG).show()
                return@verify
            }
        }
        catch (e:Exception){
            println(e.message)
        }

    }
    fun auth(){
        val number_send=edit2.text.toString()
        val credential: PhoneAuthCredential =PhoneAuthProvider.getCredential(v_id,number_send)
        singin(credential)
    }
    fun singin(credential: PhoneAuthCredential){

        m_auth.signInWithCredential(credential).addOnCompleteListener {
            task: Task<AuthResult> ->
            if (task.isSuccessful)
            {
                Toast.makeText(this,"تم التحقق بنجاح", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, Patient_registration::class.java))

            }else{
                Toast.makeText(this,"خطا في ...."+task.exception?.message, Toast.LENGTH_LONG).show()
            }
        }
    }


}