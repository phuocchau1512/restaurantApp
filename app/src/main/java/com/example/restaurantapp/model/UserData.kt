package com.example.restaurantapp.model

data class UserData(val name:String,val email:String, val pass:String){
    constructor(): this("","","")
}
