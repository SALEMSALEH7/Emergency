package com.example.muradesal.last_time_proj.HOSIPTAL

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.muradesal.last_time_proj.R
import com.google.firebase.firestore.*

class hospital_list_info : AppCompatActivity() {


    lateinit var resc:RecyclerView
    lateinit var items_list:ArrayList<hospiotal>
    lateinit var data_adapter: hospital_teach
    var my_store: FirebaseFirestore = FirebaseFirestore.getInstance()
    lateinit var db: DocumentReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hospital_list_info)

        resc=findViewById(R.id.ho_list)

        items_list= ArrayList()




        db=my_store.collection("hospital").document()
        data_adapter= hospital_teach(items_list, applicationContext)
        resc.layoutManager= LinearLayoutManager(this)
        resc.setHasFixedSize(true)
        resc.adapter=data_adapter


        data()
    }

    fun data(){


        my_store.collection("hospital").addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(p0: QuerySnapshot, p1: FirebaseFirestoreException?) {
                for(x in p0.documentChanges){


                    if(x.type== DocumentChange.Type.ADDED){

                        var name: hospiotal =x.document.toObject(hospiotal::class.java)
                        items_list.add(name)
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
