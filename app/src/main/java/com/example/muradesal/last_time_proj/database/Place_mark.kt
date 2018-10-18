package com.example.muradesal.last_time_proj.database


class mark_point{


    var place_name:String=""
    var place_des:String=""
    var place_lat:String=""
    var place_long:String=""

    constructor(){

    }
    fun set_name_place(place_name:String){
        this.place_name=place_name
    }
    fun get_name_place():String{
        return place_name
    }

    fun set_place_des(place_des:String){

        this.place_des=place_des
    }
    fun get_place_des():String{
        return place_des
    }
    fun set_place_lat(place_lat:String){
        this.place_lat=place_lat
    }

    fun get_place_lat():String{
        return place_lat
    }

    fun set_place_long(place_long:String){
        this.place_long=place_long
    }

    fun get_place_long():String{
        return place_long
    }
}