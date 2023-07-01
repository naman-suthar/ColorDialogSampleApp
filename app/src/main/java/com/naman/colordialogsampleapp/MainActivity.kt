package com.naman.colordialogsampleapp

import android.os.Bundle
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import app.ijp.colorpickerdialog.ColorDialog
import app.ijp.colorpickerdialog.OnColorChangedListener


import com.naman.colordialogsampleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.btnOpenDialog?.setOnClickListener {
            val colorDialog =
                ColorDialog.newInstance(
                    0,
                    colorHistory = null,
                    object : OnColorChangedListener {
                        override fun colorChanged(color: Int) {
                            binding?.btnOpenDialog?.setBackgroundColor(color)
                        }
                    })
            colorDialog.show(supportFragmentManager, "single")
        }

    }
}