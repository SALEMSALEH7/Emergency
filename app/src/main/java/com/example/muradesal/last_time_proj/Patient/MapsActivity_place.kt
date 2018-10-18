package com.example.muradesal.last_time_proj.Patient

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Color
import android.util.Log
import com.example.muradesal.last_time_proj.database.mark_point
import com.example.muradesal.last_time_proj.R
import com.example.muradesal.last_time_proj.database.Places_info
import com.google.android.gms.maps.model.*
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import java.util.*


class MapsActivity_place : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    lateinit var location_info:FusedLocationProviderClient
    var my_store:FirebaseFirestore= FirebaseFirestore.getInstance()
    var data_back: DocumentReference=my_store.collection("Patient").document("Place_Mark")


    var xx:Double=0.0
    var yy:Double=0.0
    var my_plan_b:Location?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps_place)
        my_store= FirebaseFirestore.getInstance()
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        place_mark()
        cheack_per()
        my_doning()

    }

    val acess_location=1234
    fun cheack_per(){

        if(Build.VERSION.SDK_INT>=23) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), acess_location)
                return
            }

        }
        get_users()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {


        when(requestCode){
            acess_location->{
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    get_users()
                }else{
                    Toast.makeText(this,"try agine",Toast.LENGTH_LONG).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    @SuppressLint("MissingPermission")
    fun get_users(){


        val location_in=my_map_point()
        val location_manger=getSystemService(Context.LOCATION_SERVICE) as LocationManager
        location_manger.requestLocationUpdates(LocationManager.GPS_PROVIDER,3,3f,location_in)

        val thread=my_thread()
        thread.start()
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            val success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.dark))

            if (!success) {

                Log.e("b", "Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Log.e("a", "Can't find style. Error: ", e)
        }








        // Add a marker in Sydney and move the camera

        /*

  var x:Location?=null
        x!!.latitude=0.0
        x!!.longitude=0.0
        val sydney = LatLng(14.539834, 49.117206)
        mMap.addMarker(MarkerOptions()
                .position(sydney)
                .title("مكاني"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,14f))
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        */

    }


    var place=ArrayList<Places_info>()

    fun place_mark(){


        // THIS ONLY FOR START OTHER ELEMENT HAS BEEN ADD THROW Firebase DATABASE
        place.add(Places_info("البرج", "", 14.550729, 49.126036))
        place.add(Places_info("الريان", "", 14.531060, 49.124116))
        place.add(Places_info("حضرموت", "", 14.549267, 49.125714))


        my_store.collection("Place_Mark").addSnapshotListener(object: EventListener<QuerySnapshot> {
            override fun onEvent(p0: QuerySnapshot, p1: FirebaseFirestoreException?) {
                for(x in p0.documentChanges){

                    if(x.type== DocumentChange.Type.ADDED){

                        var name: mark_point =x.document.toObject(mark_point::class.java)

                        val x_lat = java.lang.Double.parseDouble(name.place_lat)
                        val x_long = java.lang.Double.parseDouble(name.place_long)

                        place.add(Places_info(name.place_name, name.place_des, x_lat, x_long))
                    }
                }
            }


        })




    }

    fun my_doning() {


        location_info = LocationServices.getFusedLocationProviderClient(this)

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            location_info.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    var x = location.latitude
                    var y = location.longitude




                    place_mark()
                    cheack_per()

                    my_location!!.latitude = x
                    my_location!!.longitude = y

                }
            }

        }
    }

    fun cheak_gps(){


    }

    var my_location:Location?=null

   inner class my_map_point:LocationListener{

       constructor(){
           my_location= Location("مكاني")
           //my_location!!.latitude=0.0
           //my_location!!.longitude=0.0
       }
       override fun onLocationChanged(p0: Location?) {


          // my_location=p0
       }

       override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

       }

       override fun onProviderEnabled(p0: String?) {

       }

       override fun onProviderDisabled(p0: String?) {

       }

   }



    var old_location:Location?=null
    inner class my_thread:Thread{

        constructor():super(){
            old_location= Location("مكاني القديم")
            old_location!!.longitude=0.0
            old_location!!.latitude=0.0
        }


        // AIzaSyDokm1UiTy1rEdz4K9OS8Dc8LjFS-ssz4c
        override fun run() {

            while(true){
                try {


                    if(old_location!!.distanceTo(my_location)==0f){

                        continue
                    }
                    old_location=my_location

                    runOnUiThread {




                        mMap.clear()
                var sydney = LatLng(my_location!!.latitude,my_location!!.longitude)
                        mMap.addMarker(MarkerOptions()
                                .position(sydney)
                                .title("مكاني")
                                .snippet("my ay the map"))
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,14f))





                        for (i in 0..place.size-1){
                            var new_location=place[i]
                            if(new_location.location_exist==false){

                                val sydney_lo = LatLng(new_location.location!!.latitude,new_location.location!!.longitude)
                                mMap.addMarker(MarkerOptions()
                                        .position(sydney_lo)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.images333))
                                        .title(new_location.name))
                                mMap.addPolyline(PolylineOptions()
                                        .add(sydney,sydney_lo)
                                        .color(Color.RED).width(25F)
                                        .geodesic(true)
                                        .clickable(true))

                            }
                            }

                        /*
                        data_back.get().addOnSuccessListener(object :OnSuccessListener<DocumentSnapshot>{
                            override fun onSuccess(p0: DocumentSnapshot) {

                                //val x_name="place_name"
                                //val x_des="place_des"
                                //val x_lat="place_lat"
                                //val x_long="place_long"
                                var name=p0.getString("place_name")
                                var des=p0.getString("place_des")
                                var lat=p0.getString("place_lat")
                                var long=p0.getString("place_long")

                                val mark_new=LatLng(lat.toDouble(),long.toDouble())
                                mMap.addMarker(MarkerOptions()
                                        .position(mark_new)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.images333))
                                        .title(name)
                                        .snippet(des))


                            }

                        })

*/


                        }

                    Thread.sleep(1000)



                }
                catch (e:Exception){

                }
            }
        }
    }


}
