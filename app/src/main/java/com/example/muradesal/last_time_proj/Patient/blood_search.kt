package com.example.muradesal.last_time_proj.Patient

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.muradesal.last_time_proj.R
import com.example.muradesal.last_time_proj.database.Patient
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class blood_search : AppCompatActivity() {

    lateinit var text1:TextView
    lateinit var edit: TextView
    //lateinit var my_list: RecyclerView
    lateinit var img_but: ImageButton


    lateinit var my_list:RecyclerView
    lateinit var data_search:DatabaseReference
    //var my_store=FirebaseFirestore.getInstance()
    //var db:CollectionReference=my_store.collection("Patient")
    //var xx:DocumentReference=my_store.document("Patient")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blood_search)
        edit = findViewById(R.id.search_time)
        img_but = findViewById(R.id.search_btn)
        my_list = findViewById(R.id.my_list_search)
        text1 = findViewById(R.id.heading_label)



        my_list.layoutManager = LinearLayoutManager(this)
        data_search = FirebaseDatabase.getInstance().getReference("Patient")

        edit.setOnClickListener {

            val bulider = AlertDialog.Builder(this@blood_search).setTitle("فصيلة الدم")
            val blood_type = arrayOf("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-")
            bulider.setSingleChoiceItems(blood_type, -1) { dialog: DialogInterface?, i: Int ->


                edit.text = blood_type[i]
                dialog?.dismiss()

            }

            val close = bulider.create()
            close.show()

            img_but.setOnClickListener {


                var x = edit.text.toString()


                search(x)
            }

        }
    }



    private fun search(search:String) {

        Toast.makeText(this@blood_search,"جاري البحث",Toast.LENGTH_LONG).show()

        val firebase_search=data_search.orderByChild("type_OF_BLOOD").startAt(search).endAt(search + "\uf8ff")
        val firebaseRecyclerAdapter=object :FirebaseRecyclerAdapter<Patient, UsersViewHolder>(

                Patient::class.java,
                R.layout.blood_layout,
                UsersViewHolder::class.java,
                firebase_search
        ){
            override fun populateViewHolder(viewHolder: UsersViewHolder?, model: Patient?, position: Int) {
                viewHolder!!.setDetails(model!!.get_name(),model.get_phone())
            }



        }

        my_list.adapter = firebaseRecyclerAdapter
    }
}

class UsersViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
    internal var mView:View
    init{
        mView = itemView
    }
    fun setDetails(user_name1:String,phone:String) {
        val user_name = mView.findViewById(R.id.blood_list_type) as TextView
        val user_status = mView.findViewById(R.id.blood_list_name) as TextView

        user_name.setText(user_name1)
        user_status.text=" رقم المريض :"+ phone

    }
}




