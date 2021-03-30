package ps.room.boundservicedemo

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log
import android.widget.Toast

class MyMessengerService : Service() {
   inner class IncomingHandler : Handler(Looper.getMainLooper()!!) {
        override fun handleMessage(msg: Message) {

            when (msg.what) {
                43 -> {
                    val bundle = msg.data
                    val numOne = bundle.getInt("numOne", 0)
                    val numTwo = bundle.getInt("numTwo", 0)
                    val result= addNumbers(numOne,numTwo)
                    Log.d("MyMessenger","$numOne $numTwo")
                    Toast.makeText(applicationContext,"Result=$result",Toast.LENGTH_LONG).show()
                }
                else -> super.handleMessage(msg)
            }
        }
    }


    override fun onBind(intent: Intent?): IBinder? {
        val mMessenger = Messenger(IncomingHandler())
        return mMessenger.binder
    }

    fun addNumbers(a: Int, b: Int): Int {
        return a + b
    }
}