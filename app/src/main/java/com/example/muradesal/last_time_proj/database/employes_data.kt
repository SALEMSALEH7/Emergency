package com.example.muradesal.last_time_proj.database



class Employes{


    var emile_emp:String=""
    var name_emp:String=""
    var phone_emp:String=""
    var password_emp:String=""
    var hopital_name:String=""


    fun set_namee(name_emp:String){
        this.name_emp=name_emp
    }
    fun get_namee():String{
        return name_emp
    }

    fun set_name_h(hopital_name:String){
        this.hopital_name=hopital_name
    }
    fun get_name_h():String{
        return hopital_name
    }

    /*

    val emile1="emile_emp"
                val name="name_emp"
                val phone="phone_emp"
                val pass="password_emp"
                val hopit="hopital_name"
     */



}

