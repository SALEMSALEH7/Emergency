package com.example.muradesal.last_time_proj.database




class orders{


    var hos_name:String=""
    var pat_blood:String=""
    var pat_dis:String=""
    var pat_gender:String=""
    var pat_name:String=""
    var pat_phone:String=""
    var pat_lat:String=""
    var pat_long:String=""
    var data:String=""

    constructor(){

    }

    fun set_hname(hos_name:String){
        this.hos_name=hos_name
    }
    fun get_hname():String{
        return hos_name
    }
    fun set_name(pat_name:String){
        this.pat_name=pat_name
    }
    fun get_namee():String{
        return pat_name
    }
    fun set_dis(pat_dis:String){
        this.pat_dis=pat_dis
    }
    fun get_dis():String{
        return pat_dis
    }
    fun set_blood(pat_blood:String){
        this.pat_blood=pat_blood
    }
    fun get_blood():String{
        return pat_blood
    }

    fun set_lat(pat_lat:String){
        this.pat_lat=pat_lat
    }
    fun get_lat():String{
        return pat_lat
    }
    fun set_long(pat_long:String){
        this.pat_long=pat_long
    }
    fun get_long():String{
        return  pat_long
    }

    fun set_gender(pat_gender:String){
        this.pat_gender=pat_gender
    }

    fun get_gender():String{
        return pat_gender
    }

    fun set_phonee(pat_phone:String){
        this.pat_phone=pat_phone
    }

    fun get_phonee():String{
        return pat_phone
    }

    fun set_data(data:String){
        this.data=data
    }

    fun get_data():String{
        return data
    }
}