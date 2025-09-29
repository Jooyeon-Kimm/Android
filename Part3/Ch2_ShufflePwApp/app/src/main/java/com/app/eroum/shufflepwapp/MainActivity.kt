package com.app.eroum.shufflepwapp

import kotlin.jvm.java

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.app.eroum.shufflepwapp.databinding.ActivityMainBinding
import com.app.eroum.shufflepwapp.util.AppSignatureHelper

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.view = this
        AppSignatureHelper(this).apply {
            Log.d("hash", "hash : ${appSignature}")
        }
    }

    fun openShuffle() {
        startActivity(Intent(this, PinActivity::class.java))
    }

    fun openVerifySms() {
        startActivity(Intent(this, IdentityInputActivity::class.java))
    }
}