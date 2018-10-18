package com.example.muradesal.last_time_proj.Patient

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.muradesal.last_time_proj.R
import kotlinx.android.synthetic.main.items1_first.view.*


class items1():RecyclerView.Adapter<Viewholder>(){




    var x= arrayListOf<Int>(R.drawable.f1, R.drawable.f2, R.drawable.f3, R.drawable.f4)
    val y= listOf("","","","البعد عن المصدر الكهربائي الذي سبب الحرق .\n" +
            "تحديد عمق الحرق .\n" +
            "تغطية الحروق بضمادة جافة معقمة .\n" +
            "لا تهدأ الحروق باستخدام الماء .\n" +
            "مراقبة ما إذا كانت توجد علامات تهدد حياة المصاب مثل: عدم انتظام ضربات القلب أو مشاكل التنفس ")
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Viewholder {

        var layoutInflater=LayoutInflater.from(parent?.context)
        var view:View=layoutInflater.inflate(R.layout.items1_first,parent,false)
        return Viewholder(view)
    }

    override fun getItemCount(): Int {
        return x.size
    }

    override fun onBindViewHolder(holder: Viewholder?, position: Int) {

        var name=y.get(position)
        //val x:Int=R.drawable.f1
       holder?.view?.imageView?.setImageResource(x.get(position))
        holder?.view?.text_item1?.text=name

    }


}

class Viewholder(var view:View):RecyclerView.ViewHolder(view){

}