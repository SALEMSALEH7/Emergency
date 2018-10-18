package com.example.muradesal.last_time_proj.HOSIPTAL

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.muradesal.last_time_proj.R
import com.example.muradesal.last_time_proj.database.orders
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*


class hospital_note:AppCompatActivity(){

    lateinit var recy: RecyclerView
    lateinit var list_items:ArrayList<orders> //list of data
    lateinit var data_adapter: hoi_list

    var my_store: FirebaseFirestore = FirebaseFirestore.getInstance()
    lateinit var db: DocumentReference
    override fun onCreate(savedInstanceState: Bundle? ){
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_hopital_patient_bik)

        recy=findViewById(R.id.r_hopital_bik)

        //recy.adapter=hoital_list(this)

        list_items=ArrayList()






        //my_store= FirebaseFirestore.getInstance()
        db=my_store.collection("orders").document()
        data_adapter= hoi_list(list_items, applicationContext)
        recy.layoutManager= LinearLayoutManager(this)
        recy.setHasFixedSize(true)
        recy.adapter=data_adapter
        //recy.adapter=user_items

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


class hoi_list: RecyclerView.Adapter<hoi_list.holder_itemss> {


    lateinit var list:ArrayList<orders>
    lateinit var context: Context
    var mintent:Intent= Intent()

    constructor(list:ArrayList<orders>, context: Context){
        this.list=list
        this.context=context
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): holder_itemss {

        var layoutInflater= LayoutInflater.from(parent?.context)
        var view: View =layoutInflater.inflate(R.layout.hosp_orders,parent,false)

        return holder_itemss(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: holder_itemss?, position: Int) {

        holder?.text1?.setText(list.get(position).get_namee()).toString()
        holder?.text2?.setText(list.get(position).get_hname()).toString()
        holder?.text3?.setText(list.get(position).get_dis()).toString()
        holder?.text4?.setText(list.get(position).get_blood()).toString()
        holder?.text5?.setText(list.get(position).get_gender()).toString()
        holder?.text6?.setText(list.get(position).get_data()).toString()
        holder?.text7?.setText(  list.get(position).get_lat()).toString()
        holder?.text8?.setText( list.get(position).get_long()).toString()
       // holder?.view_x?.context?.startActivity(intent)
        holder?.but?.setOnClickListener {

            var x=holder.text7.text.toString()
            var y=holder.text8.text.toString()
            val gmmIntentUr3i = Uri.parse("google.navigation:q=${holder.text7.text},${holder.text8.text}")
            val mapIntent =Intent(Intent.ACTION_VIEW,gmmIntentUr3i)
            mapIntent.`package` = "com.google.android.apps.maps"
                    holder.view_x.context.startActivity(mapIntent)
        }
        holder?.but1?.setOnClickListener {


            var my_auth:FirebaseAuth=FirebaseAuth.getInstance()
            var my_data:FirebaseFirestore= FirebaseFirestore.getInstance()
            var dp:DocumentReference=my_data.document("orders/${my_auth.toString()}")
            dp.delete()


        }


    }



    class holder_itemss:RecyclerView.ViewHolder{

        lateinit var view_x: View
        //var intent: Intent=Intent()
        //var x=intent.getStringExtra("emp_name").toString()
        lateinit var text1: TextView
        lateinit var text2: TextView
        lateinit var text3:TextView
        lateinit var text4:TextView
        lateinit var text5:TextView
        lateinit var text6:TextView
        lateinit var text7:TextView
        lateinit var text8:TextView
        lateinit var but:Button
        lateinit var but1:Button
       // var x_name=intent.getStringExtra("emp_name").toString()
        constructor(itmes: View) :

                super(itmes){
            view_x=itmes
            text1=view_x.findViewById(R.id.order1)
            text2=view_x.findViewById(R.id.order2)
            text3=view_x.findViewById(R.id.order3)
            text4=view_x.findViewById(R.id.order4)
            text5=view_x.findViewById(R.id.order5)
            text6=view_x.findViewById(R.id.order6)
            text7=view_x.findViewById(R.id.order7)
            text8=view_x.findViewById(R.id.order8)
            but=view_x.findViewById(R.id.but_order)
            but1=view_x.findViewById(R.id.but_order2)
        //   intent= Intent()

           //val y=intent.getStringExtra("emp_name")
           //view_x.order1.text=x_name
        }


    }
}



