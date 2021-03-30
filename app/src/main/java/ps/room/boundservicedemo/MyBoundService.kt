package ps.room.boundservicedemo

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.IBinder

class MyBoundService: Service() {
    inner class MyLocalBinder: Binder() {
        fun getService():MyBoundService=this@MyBoundService


    }
    val myLocalBinder=MyLocalBinder()
    override fun onBind(intent: Intent?): IBinder? {
         return myLocalBinder
    }



    fun add(a:Int,b:Int):Int{
        return a+b
    }
    fun substract(a:Int,b:Int):Int{
        return a-b
    }
    fun multiply(a:Int,b:Int):Int{
        return a*b
    }
    fun divide(a:Int,b:Int):Int{
        return a/b
    }
}