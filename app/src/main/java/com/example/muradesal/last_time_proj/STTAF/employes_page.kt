package com.example.muradesal.last_time_proj.STTAF

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.muradesal.last_time_proj.R
import com.example.muradesal.last_time_proj.database.orders
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import kotlinx.android.synthetic.main.emp_data_orders.view.*
import java.util.*

class employes_page : AppCompatActivity() {

    lateinit var recy: RecyclerView
    lateinit var list_items:ArrayList<orders> //list of data
    lateinit var data_adapter: emp_request // value for recyclerView
    var my_store: FirebaseFirestore = FirebaseFirestore.getInstance()
    lateinit var db: DocumentReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employes_page)

        recy=findViewById(R.id.r_emp)
        list_items=ArrayList()
        db=my_store.collection("orders").document()
        data_adapter= emp_request(list_items, applicationContext)
        recy.layoutManager= LinearLayoutManager(this)
        recy.setHasFixedSize(true)
        recy.adapter=data_adapter

        get_data()
    }

    fun get_data(){

        my_store.collection("orders").addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(p0: QuerySnapshot, p1: FirebaseFirestoreException?) {
                for(x in p0.documentChanges){


                    if(x.type== DocumentChange.Type.ADDED){


                        var name: orders =x.document.toObject(orders::class.java)
                        list_items.add(name)
                        data_adapter.notifyDataSetChanged()

                    }
                    /* if(x.type==DocumentChange.Type.ADDED){
                        // var name:hospiotal=x.document.toObject(hospiotal::class.java)
                         //list_items.add(name)
                         //user_items.notifyDataSetChanged()
                         var y:String
                         y=x.
                         Log.d(fff,"SALEMEN" + y)

                     }
                     */
                }
            }


        })
    }
}

class emp_request: RecyclerView.Adapter<emp_request.holder_info> {


    lateinit var list:ArrayList<orders>
    lateinit var context: Context
    constructor(list:ArrayList<orders>, context: Context){
        this.list=list
        this.context=context
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): holder_info {

        var layoutInflater= LayoutInflater.from(parent?.context)
        var view: View =layoutInflater.inflate(R.layout.emp_data_orders,parent,false)

        return holder_info(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: holder_info?, position: Int) {

        var myAuth= FirebaseAuth.getInstance()
        var my_store_DB= FirebaseFirestore.getInstance()
        var dp:DocumentReference=my_store_DB.collection("employees").document(myAuth.currentUser!!.uid)
        dp.get().addOnSuccessListener (object :OnSuccessListener<DocumentSnapshot>{
            override fun onSuccess(p0: DocumentSnapshot?) {

                if(p0!!.exists()){
                    var x=p0.getString("name_emp")
                    holder?.text4?.text=" اسم الموظف :" + x
                }
                else{
                    Toast.makeText(holder?.view_x?.context,"fail",Toast.LENGTH_LONG).show()
                }
            }

        })

        val y= Calendar.getInstance().time.toLocaleString()
        holder?.text9?.setText(y).toString()
        holder?.text1?.setText(list.get(position).get_hname()).toString()
        holder?.text2?.setText(list.get(position).get_phonee()).toString()
        holder?.text3?.setText(list.get(position).get_dis()).toString()
        holder?.text7?.setText(list.get(position).get_lat()).toString()
        holder?.text8?.setText(list.get(position).get_long()).toString()
        holder?.text10?.setText(list.get(position).get_namee()).toString()

        holder?.but?.setOnClickListener {

            var x=holder.text7.text.toString()
            var y=holder.text8.text.toString()
            val gmmIntentUr3i = Uri.parse("google.navigation:q=${holder.text7.text},${holder.text8.text}")
            val mapIntent = Intent(Intent.ACTION_VIEW,gmmIntentUr3i)
            mapIntent.`package` = "com.google.android.apps.maps"
            holder.view_x.context.startActivity(mapIntent)
        }
        holder?.but1?.setOnClickListener {

/*
            var myAuth= FirebaseAuth.getInstance()
            var my_store_DB= FirebaseFirestore.getInstance()
            var dp:DocumentReference=my_store_DB.collection("employees").document(myAuth.currentUser!!.uid)
            dp.get().addOnSuccessListener (object :OnSuccessListener<DocumentSnapshot>{
                override fun onSuccess(p0: DocumentSnapshot?) {

                    if(p0!!.exists()){
                        var x=p0.getString("name_emp")
                       holder.text4.text=" اسم الموظف :" + x
                    }
                    else{
                        Toast.makeText(holder.view_x.context,"fail",Toast.LENGTH_LONG).show()
                    }
                }

            })
            */
            val note=HashMap<Any,Any>()
            val x_name="engery_name"
            val x_phone="engery_phone"
            val x_dis="engery_dis"
            val x_review="review"
            val x_lat="engery_lat"
            val x_long="engery_long"
            val e_name="emp_name1"
            val x_data="data"

            val x_1=holder.view_x.order11.text.toString()
            val x_2=holder.view_x.order22.text.toString()
            val x_3=holder.view_x.order33.text.toString()
            val x_4=holder.view_x.order77.text.toString()
            val x_5=holder.view_x.order88.text.toString()
            val x_6=holder.edit.text.toString()
            var x_7=holder.text4.text.toString()
            val x_8=holder.view_x.order99.text.toString()
            var my_data=FirebaseDatabase.getInstance().getReference("review")
            var data_Id=my_data.push().key
            note.put(x_name,x_1)
            note.put(x_phone,x_2)
            note.put(x_dis,x_3)
            note.put(x_lat,x_4)
            note.put(x_long,x_5)
            note.put(x_review,x_6)
            note.put(e_name,x_7)
            note.put(x_data,x_8)
            my_store_DB.collection("review").document(data_Id).set(note).addOnCompleteListener {
                task ->  if(task.isSuccessful){
                Toast.makeText(context.applicationContext,"تم ارسال التقرير الى المستشفى" + x_1,Toast.LENGTH_LONG).show()
            }else{

                Toast.makeText(context.applicationContext,"هنالك مشكلة في  "+ task.exception!!.message,Toast.LENGTH_LONG).show()
            }


            }



        }

    }



    class holder_info:RecyclerView.ViewHolder{

        lateinit var view_x: View
        lateinit var text1: TextView
        lateinit var text2: TextView
        lateinit var text3: TextView
        lateinit var text4: TextView
        lateinit var text7: TextView
        lateinit var text8: TextView
        lateinit var text9:TextView
        lateinit var text10:TextView
        lateinit var edit:EditText
        lateinit var but: Button
        lateinit var but1: Button
        constructor(itmes: View) :

                super(itmes){
            view_x=itmes
            text1=view_x.findViewById(R.id.order11)
            text2=view_x.findViewById(R.id.order22)
            text3=view_x.findViewById(R.id.order33)
            text4=view_x.findViewById(R.id.order44)
            text7=view_x.findViewById(R.id.order77)
            text8=view_x.findViewById(R.id.order88)
            text9=view_x.findViewById(R.id.order99)
            text10=view_x.findViewById(R.id.order13)
            edit=view_x.findViewById(R.id.edit_emp)
            but=view_x.findViewById(R.id.but_order11)
            but1=view_x.findViewById(R.id.but_order22)
        }

    }
}
