package com.permissionx.app

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.permissionx.app.databinding.ActivityMainBinding
import com.permissionx.tim.PermissionX
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        binding.makeCallBtn.setOnClickListener {
            PermissionX.request(this,
                android.Manifest.permission.CALL_PHONE) { allGranted, deniedList ->
                    if (allGranted) {
                        call()
                    } else {
                        Toast.makeText(this, "You denied $deniedList",
                            Toast.LENGTH_SHORT).show()
                    }
            }
        }
        setContentView(binding.root)
    }

    private fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }
}