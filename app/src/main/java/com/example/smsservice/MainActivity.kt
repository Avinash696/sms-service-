package com.example.smsservice

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.smsservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var _binding: ActivityMainBinding
    private var TAG = "rawat"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _binding.btSend.setOnClickListener {
            Log.d(TAG, "onCreate: clicked ")
            //permission check
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.SEND_SMS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                sendMessage()
            } else {
                //if has no permission
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.SEND_SMS),
                    1)
            }
        }
    }

    private fun sendMessage() {
        val sNum = _binding.etNumber.text.trim().toString()
        val sMessage = _binding.etMessage.text.trim().toString()
        if (sNum != "" && sMessage != "") {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage("91$sNum", null, sMessage, null, null)
           val intent = Intent(Intent.ACTION_VIEW)
            startActivity(intent)
            Toast.makeText(this, "Success Send", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Enter All fields ", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(p0: View?) {
        _binding.btSend.setOnClickListener(this)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] ==
            PackageManager.PERMISSION_GRANTED
        ) {

        } else {
            Toast.makeText(this, "Permission Denied !", Toast.LENGTH_SHORT).show()
        }
    }
}