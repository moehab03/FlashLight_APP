package com.codexcue.flashlight

import android.hardware.camera2.CameraManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import com.codexcue.flashlight.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var cameraManager: CameraManager
    private lateinit var cameraId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initCameraManager()
        binding.switchBtn.setOnCheckedChangeListener { _, isChecked ->
            setTorchMode(isChecked)
        }
    }

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initCameraManager() {
        cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
        cameraId = cameraManager.cameraIdList[0]
    }

    private fun setTorchMode(isChecked: Boolean) {
        if (isChecked) {
            cameraManager.setTorchMode(cameraId, true)
            changeBackground(true)
        } else {
            cameraManager.setTorchMode(cameraId, false)
            changeBackground(false)
        }
    }

    private fun changeBackground(isChecked: Boolean) {
        binding.apply {
            if (isChecked) {
                root.background = getColor(R.color.brown).toDrawable()
                lightIV.setImageResource(R.drawable.light_on)

            } else {
                root.background = getColor(R.color.light_black).toDrawable()
                lightIV.setImageResource(R.drawable.light_off)

            }
        }

    }
}