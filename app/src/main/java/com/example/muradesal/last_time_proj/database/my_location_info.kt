package com.example.muradesal.last_time_proj.database

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class Places_info{

    var name:String?=null
    var location:Location?=null
    var info:String?=null
    var location_exist:Boolean=false

    constructor(){

    }
    constructor(name:String,info:String,lat:Double,loneg:Double){

        this.name=name
        this.location= Location(name)
        this.location!!.latitude=lat
        this.location!!.longitude=loneg
        this.info=info


    }


}

/*
var my_location:Location?=null

 class my_location_map: LocationListener {

    constructor(){

        my_location=Location("me")
        my_location!!.latitude=0.0
        my_location!!.longitude=0.0
    }
    override fun onLocationChanged(p0: Location?) {

    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

    }

    override fun onProviderEnabled(p0: String?) {

    }

    override fun onProviderDisabled(p0: String?) {

    }

}


        */