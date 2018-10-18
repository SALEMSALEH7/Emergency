package com.example.muradesal.last_time_proj.HOSIPTAL

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.muradesal.last_time_proj.R
import com.example.muradesal.last_time_proj.database.review_items
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*

class hospital_notfi : AppCompatActivity() {

    lateinit var rcy:RecyclerView
    var my_store:FirebaseFirestore= FirebaseFirestore.getInstance()
    lateinit var db:DocumentReference
    lateinit var items_list:ArrayList<review_items>
    lateinit var revie_adapter: review_orders
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospital_notfi)
        rcy=findViewById(R.id.r_review)


        items_list= ArrayList()
        db=my_store.collection("review").document()
        revie_adapter= review_orders(this, items_list)
        rcy.layoutManager=LinearLayoutManager(this)
        rcy.adapter=revie_adapter


        data_shown()
    }
    fun gps(){
        /*
           // val gmmIntentUri = Uri.parse("geo:0,0?:q=${text5.text},${text6.text}")
            val gmmIntentUr3i = Uri.parse("google.navigation:q=${x},${y}")
            val mapIntent = Intent(Intent.ACTION_VIEW,gmmIntentUr3i)
            mapIntent.`package` = "com.google.android.apps.maps"
            startActivity(mapIntent)
            */
    }
    fun data_shown(){


        //var my_auth:FirebaseAuth=FirebaseAuth.getInstance()
        my_store.collection("review").addSnapshotListener(object:EventListener<QuerySnapshot>{
            override fun onEvent(p0: QuerySnapshot, p1: FirebaseFirestoreException?) {
                for(x in p0.documentChanges){

                    if(x.type==DocumentChange.Type.ADDED){

                        var name: review_items =x.document.toObject(review_items::class.java)
                        items_list.add(name)
                        revie_adapter.notifyDataSetChanged()
                    }
                }
            }


        })


    }


}




class review_orders:RecyclerView.Adapter<review_orders.review_hos>{


    var contex:Context
    var list_revie:ArrayList<review_items>

    constructor(context: Context,list_revie:ArrayList<review_items>){
        this.contex=context
        this.list_revie=list_revie

    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): review_hos {
        var layoutInflater=LayoutInflater.from(parent?.context)
        var view:View=layoutInflater.inflate(R.layout.review_hospit,parent,false)
        return review_hos(view)
    }

    override fun getItemCount(): Int {

        return list_revie.size
    }

    override fun onBindViewHolder(holder: review_hos?, position: Int) {


        holder?.text1?.setText(list_revie.get(position).get_e_name()).toString()
        holder?.text2?.setText(list_revie.get(position).get_e_phone()).toString()
        holder?.text3?.setText(list_revie.get(position).get_e_review()).toString()
        holder?.text4?.setText(list_revie.get(position).get_emp_name()).toString()
        holder?.text5?.setText(list_revie.get(position).get_data()).toString()

        holder?.but?.setOnClickListener {

            var my_auth:FirebaseAuth=FirebaseAuth.getInstance()
            var my_data:FirebaseFirestore= FirebaseFirestore.getInstance()
            var dp:DocumentReference=my_data.document("review/${my_auth.toString()}")
            dp.delete()
        }
    }

    class review_hos:RecyclerView.ViewHolder{

        lateinit var lats_view:View
        lateinit var text1:TextView
        lateinit var text2:TextView
        lateinit var text3:TextView
        lateinit var text4:TextView
        lateinit var text5:TextView
        lateinit var but: Button
        constructor(items:View)
                :super(items){

            lats_view=items
            text1=lats_view.findViewById(R.id.review_1)
            text2=lats_view.findViewById(R.id.review_2)
            text3=lats_view.findViewById(R.id.review_3)
            text4=lats_view.findViewById(R.id.review4)
            text5=lats_view.findViewById(R.id.review5)
            but=lats_view.findViewById(R.id.review6)
        }
    }
}
