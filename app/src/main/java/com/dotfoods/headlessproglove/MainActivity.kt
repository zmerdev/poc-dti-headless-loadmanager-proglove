package com.dotfoods.headlessproglove
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val serviceIntent = Intent(this, ProGloveService::class.java)
        startService(serviceIntent) // This will start the service

        // This activity will be minimal and can even finish immediately
        finish() // Close the activity right after it's created
    }
}
