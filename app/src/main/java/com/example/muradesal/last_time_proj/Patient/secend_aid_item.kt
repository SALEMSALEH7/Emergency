package com.example.muradesal.last_time_proj.Patient

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.muradesal.last_time_proj.R
import kotlinx.android.synthetic.main.item2_adi.view.*


class items2:RecyclerView.Adapter<View_holder>(){

    var x= arrayListOf<Int>(R.drawable.s1, R.drawable.s2, R.drawable.s3, R.drawable.s4)
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): View_holder {



        var layoutInflater=LayoutInflater.from(parent?.context)
        var view:View=layoutInflater.inflate(R.layout.item2_adi,parent,false)
        return View_holder(view)
    }

    override fun getItemCount(): Int {
        return x.size
    }

    override fun onBindViewHolder(holder: View_holder?, position: Int) {

       holder?.view?.imageView2?.setImageResource(x.get(position))
    }


}

class View_holder(var view:View):RecyclerView.ViewHolder(view){


}