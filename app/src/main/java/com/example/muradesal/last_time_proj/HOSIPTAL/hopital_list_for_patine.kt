package com.example.muradesal.last_time_proj.HOSIPTAL

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.muradesal.last_time_proj.Patient.Patient_info_send
import com.example.muradesal.last_time_proj.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.blood_search_list.view.*


class hoital_list: RecyclerView.Adapter<hoital_list.holder_items> {


    lateinit var list:ArrayList<hospiotal>
    lateinit var context: Context
    constructor(list:ArrayList<hospiotal>, context: Context){
        this.list=list
        this.context=context
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): holder_items {

        var layoutInflater=LayoutInflater.from(parent?.context)
        var view:View=layoutInflater.inflate(R.layout.blood_search_list,parent,false)

        return holder_items(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: holder_items?, position: Int) {
        holder?.view_x?.search_blood?.setText( list.get(position).get_name()).toString()
        holder?.view_x?.search_name?.setText(" موقع المستشفى :" + list.get(position).get_loc()).toString()
        holder?.view_x?.setOnClickListener {

            var intent:Intent= Intent(holder.view_x.context, Patient_info_send::class.java)
            intent.putExtra("gmm",holder.view_x.search_blood.text)
                    holder.view_x.context.startActivity(intent)
        }
        /*
        holder?.view_x?.setOnClickListener {

            var x=holder.view_x.search_name.text.toString()

            getdata()



        }
        */

    }
    fun getdataa(){

        var my_auth=FirebaseAuth.getInstance()
        var mydata=FirebaseFirestore.getInstance()
        //var my_store:DocumentReference=mydata.collection("Patient").document(my_auth.currentUser!!.uid)
        var locatoin_info:FusedLocationProviderClient

        locatoin_info=LocationServices.getFusedLocationProviderClient(context)

        if(ActivityCompat.checkSelfPermission(context,android.Manifest.permission.ACCESS_FINE_LOCATION)==
                PackageManager.PERMISSION_GRANTED){

            locatoin_info.lastLocation.addOnSuccessListener { location ->

                if(location!=null){

                  //  my_store.get()
                }
            }
        }




    }

    class holder_items:RecyclerView.ViewHolder{

        lateinit var view_x:View
        lateinit var text1:TextView
        lateinit var text2:TextView
        constructor(itmes:View) :

                super(itmes){
            view_x=itmes
            text1=view_x.findViewById(R.id.search_blood)
            text2=view_x.findViewById(R.id.search_name)
        }

    }
}

/*
class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {


    internal var mView: View
    var ff: TextView
    var dd: TextView
    init{
        mView = itemView
        ff = mView.findViewById(R.id.search_blood)
        dd=mView.findViewById(R.id.search_name)
    }

}
        */

class info(){

}