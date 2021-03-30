package ps.room.boundservicedemo

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MyMessengerActivity : AppCompatActivity() {
   lateinit var txvResult:TextView
   var mIsBound=false
    var mService:Messenger?=null
   val mConnection=object: ServiceConnection{
       override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
           mService= Messenger(service)
            mIsBound=true
       }

       override fun onServiceDisconnected(name: ComponentName?) {
           mIsBound=false
       }

   }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messenger)
    txvResult=findViewById(R.id.txvResult)
    }

    fun performAddOperation(view: View) {
        val etNumOne=findViewById<EditText>(R.id.etNumOne)
        val etNumTwo=findViewById<EditText>(R.id.etNumTwo)
   val num1=Integer.valueOf(etNumOne.text.toString())

        val num2=Integer.valueOf(etNumTwo.text.toString())
val msgToService=Message.obtain(null,43)
        val bundle=Bundle()
        bundle.putInt("numOne",num1)
        bundle.putInt("numTwo",num2)
        msgToService.data=bundle
        mService!!.send(msgToService)
    }
    fun bindService(view: View) {
        val intent= Intent(this,MyMessengerService::class.java)
        bindService(intent,mConnection, BIND_AUTO_CREATE)
    }
    fun unbindService(view: View) {
        if(mIsBound){
            unbindService(mConnection)
            mIsBound=false
        }
    }
}