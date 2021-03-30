package ps.room.boundservicedemo

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class BoundServiceDemo : AppCompatActivity() {
    lateinit var editText1: EditText
    lateinit var editText2: EditText
    lateinit var textView1: TextView
    lateinit var add: Button
    lateinit var substract: Button
    lateinit var multiply: Button
    lateinit var divide: Button
    var isBound = false
    lateinit var myBoundService: MyBoundService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText1 = findViewById(R.id.etNumOne)
        editText2 = findViewById(R.id.etNumTwo)
        textView1 = findViewById(R.id.txvResult)
        add = findViewById(R.id.btnAdd)
        substract = findViewById(R.id.btnSub)
        multiply = findViewById(R.id.btnMul)
        divide = findViewById(R.id.btnDiv)
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, MyBoundService::class.java)
        bindService(intent, mConnection, BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        if(isBound){
            unbindService(mConnection)
            isBound=false
        }
    }
    fun onClickEvent(view: View) {
        val numOne = Integer.valueOf(editText1.text.toString())
        val numTwo = Integer.valueOf(editText2.text.toString())
        var resultStr: String? = null
        if (isBound) {
            when (view.id) {
                R.id.btnAdd -> {
                    resultStr = myBoundService.add(numOne, numTwo).toString()
                }
                R.id.btnSub -> {
                    resultStr = myBoundService.substract(numOne, numTwo).toString()
                }
                R.id.btnMul -> {
                    resultStr = myBoundService.multiply(numOne, numTwo).toString()
                }
                R.id.btnDiv -> {
                    resultStr = myBoundService.divide(numOne, numTwo).toString()
                }
            }
            textView1.text = resultStr
        }
    }

    val mConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, iBinder: IBinder?) {
            val myLocalBinder = iBinder as MyBoundService.MyLocalBinder
            myBoundService = myLocalBinder.getService()
            isBound = true

        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false

        }
    }

    fun moveToMessengerActivity(view: View) {
        val intent=Intent(this,MyMessengerActivity::class.java)
        startActivity(intent)
    }
}