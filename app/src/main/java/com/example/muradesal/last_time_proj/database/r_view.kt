package com.example.muradesal.last_time_proj.database




class review_items{

    var engery_name:String=""
    var engery_phone:String=""
    var engery_dis:String=""
    var engery_blood:String=""
    var review:String=""
    var emp_name1:String=""
    var data:String=""

    fun set_e_name(engery_name:String){
        this.engery_name=engery_name
    }
    fun get_e_name():String{
        return engery_name
    }

    fun set_e_phone(engery_phone:String){
        this.engery_phone=engery_phone
    }
    fun get_e_phone():String{
        return engery_phone
    }

    fun set_e_review(review:String){
        this.review=review
    }
    fun get_e_review():String{
        return review
    }

    fun set_emp_name(emp_name1:String){
        this.emp_name1=emp_name1
    }

    fun get_emp_name():String{
        return emp_name1
    }

    fun set_data(data:String){
        this.data=data
    }

    fun get_data():String{
        return data
    }
}