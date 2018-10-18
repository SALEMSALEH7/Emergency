package com.example.muradesal.last_time_proj.HOSIPTAL

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.muradesal.last_time_proj.R
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import kotlin.collections.ArrayList


class hopital_patient_bik : AppCompatActivity() {


    val fff="AHMED"
    lateinit var recy:RecyclerView
     lateinit var list_items:ArrayList<hospiotal> //list of data
    lateinit var data_adapter: hoital_list // value for recyclerView
   var my_store:FirebaseFirestore= FirebaseFirestore.getInstance()
    lateinit var db:DocumentReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hopital_patient_bik)

        recy=findViewById(R.id.r_hopital_bik)

        //recy.adapter=hoital_list(this)

        list_items=ArrayList()






        //my_store= FirebaseFirestore.getInstance()
        db=my_store.collection("hospital").document()
        data_adapter= hoital_list(list_items, applicationContext)
        recy.layoutManager=LinearLayoutManager(this)
        recy.setHasFixedSize(true)
        recy.adapter=data_adapter
        //recy.adapter=user_items


      //val list_items:List<hospiotal>


        //list_items.

        get_data()


    }

    fun get_data(){

        my_store.collection("hospital").addSnapshotListener(object :EventListener<QuerySnapshot>{
            override fun onEvent(p0: QuerySnapshot, p1: FirebaseFirestoreException?) {
                for(x in p0.documentChanges){


                    if(x.type==DocumentChange.Type.ADDED){

                        var name: hospiotal =x.document.toObject(hospiotal::class.java)
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
