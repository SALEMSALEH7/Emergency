package com.example.muradesal.last_time_proj.STTAF

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.muradesal.last_time_proj.R
import com.example.muradesal.last_time_proj.HOSIPTAL.hospital_note
import com.example.muradesal.last_time_proj.database.Employes
import kotlinx.android.synthetic.main.hospita_emp_info.view.*


class Empp_list:RecyclerView.Adapter<Empp_list.hloderr_items> {

    var contex:Context
    var items_list:ArrayList<Employes>
    constructor(context: Context,items_list:ArrayList<Employes>){
        this.contex=context
        this.items_list=items_list
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): hloderr_items {
        var layoutInflater=LayoutInflater.from(parent?.context)
        var view:View=layoutInflater.inflate(R.layout.hospita_emp_info,parent,false)
        return hloderr_items(view)
    }

    override fun getItemCount(): Int {
        return items_list.size
    }

    override fun onBindViewHolder(holder: hloderr_items?, position: Int) {
       holder?.text1?.setText(items_list.get(position).get_name_h()).toString()
        holder?.text2?.setText(items_list.get(position).get_namee()).toString()
        holder?.but?.setOnClickListener {


            var intent:Intent= Intent(contex.applicationContext, hospital_note::class.java)
            intent.putExtra("emp_name",holder.view_x.h_p_2.text)

            holder.view_x.context.startActivity(intent)
        }

    }


    class hloderr_items:RecyclerView.ViewHolder{

        lateinit var view_x: View
        lateinit var text1: TextView
        lateinit var text2: TextView
        lateinit var but:Button
        constructor(items:View):
                super(items){
            view_x=items
            text1=view_x.findViewById(R.id.h_p1)
            text2=view_x.findViewById(R.id.h_p_2)
            but=view_x.findViewById(R.id.h_p_3)

        }
    }
}