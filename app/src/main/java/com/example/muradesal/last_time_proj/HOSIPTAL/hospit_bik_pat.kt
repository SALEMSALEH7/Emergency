package com.example.muradesal.last_time_proj.HOSIPTAL

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.muradesal.last_time_proj.R
import com.example.muradesal.last_time_proj.database.Employes
import com.example.muradesal.last_time_proj.STTAF.Empp_list
import com.google.firebase.firestore.*



class hospit_bik_pat : AppCompatActivity() {


    lateinit var recy: RecyclerView
    lateinit var list_items:ArrayList<Employes> //list of data
    lateinit var data_adapter: Empp_list // value for recyclerView
   var my_store:FirebaseFirestore= FirebaseFirestore.getInstance()
    lateinit var db: DocumentReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospit_bik_pat)
        recy=findViewById(R.id.re_pp)

        list_items=ArrayList()






        //my_store= FirebaseFirestore.getInstance()
        db=my_store.collection("employees").document()
        data_adapter= Empp_list(applicationContext, list_items)
        recy.layoutManager= LinearLayoutManager(this)
        recy.setHasFixedSize(true)
        recy.adapter=data_adapter

        get_data()
    }

    fun get_data(){

        my_store.collection("employees").addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(p0: QuerySnapshot, p1: FirebaseFirestoreException?) {
                for(x in p0.documentChanges){

                    if(x.type== DocumentChange.Type.ADDED){

                        var name: Employes =x.document.toObject(Employes::class.java)
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
