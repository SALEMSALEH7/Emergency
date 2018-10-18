package com.example.muradesal.last_time_proj.HOSIPTAL

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.muradesal.last_time_proj.R
import kotlinx.android.synthetic.main.list_shown_hopital_info.view.*


class hospital_teach: RecyclerView.Adapter<home>{


    var list_items_shown:ArrayList<hospiotal>

     var contex:Context
    lateinit var c:Context
    constructor(list_items:ArrayList<hospiotal>, contex:Context){
        this.list_items_shown=list_items
        this.contex=contex
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): home {

        var layoutInflater=LayoutInflater.from(parent?.context)
        var view:View=layoutInflater.inflate(R.layout.list_shown_hopital_info,parent,false)
        return home(view)
    }

    override fun getItemCount(): Int {

        return list_items_shown.size
    }

    override fun onBindViewHolder(holder: home?, position: Int) {

        holder?.x?.t1t1?.setText(list_items_shown.get(position).get_name())
        holder?.y?.t2t1?.setText(list_items_shown.get(position).get_des())




      //  holder.x.t1t1.setText(list_items_shown.get(position))
    }

    class holder_time:RecyclerView.ViewHolder{

        lateinit var view_x: View
        lateinit var text1:TextView
        lateinit var text2:TextView
        constructor(itmes:View) :
                super(itmes){
            view_x=itmes
            text1=view_x.findViewById(R.id.t1t1)
            text2=view_x.findViewById(R.id.t2t1)

        }
    }
}

class home(view:View):RecyclerView.ViewHolder(view){

    lateinit var x:TextView
    lateinit var y:TextView

    init {

        x=view.findViewById(R.id.t1t1)
        y=view.findViewById(R.id.t2t1)
    }
}



