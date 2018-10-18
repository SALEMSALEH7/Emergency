package com.example.muradesal.last_time_proj.database




class Patient(){

    var user_name:String=""
    var Passwwrd:String=""
    var emile:String=""
    var phone:String=""
    var GENDER:String=""
    var diseases:String=""
    var TYPE_OF_BLOOD:String=""
    var id:String=""




    constructor(TYPE_OF_BLOOD:String,user_name:String,phone:String):this(){

        this.TYPE_OF_BLOOD=TYPE_OF_BLOOD
        this.user_name=user_name
        this.phone=phone
    }

    fun set_name(user_name: String){
        this.user_name=user_name
    }
    fun get_name():String{
        return user_name
    }

    fun set_blood(TYPE_OF_BLOOD: String){
        this.TYPE_OF_BLOOD=TYPE_OF_BLOOD
    }
    fun get_blood():String{
        return TYPE_OF_BLOOD
    }
    fun set_phone(phone: String){
        this.phone=phone
    }
    fun get_phone():String{
        return phone
    }




}

class des_hospital{

    var dis_name:String=""

    fun set_des(dis_name:String){
        this.dis_name=dis_name
    }
    fun get_des():String{
        return dis_name
    }
}