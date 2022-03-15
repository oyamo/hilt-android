package com.oyamo.hilt_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.oyamo.hilt_android.data.entity.LastTime
import com.oyamo.hilt_android.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var lastTime: LastTime

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       getLastTime()
    }


    private fun  updateLastTime(time: String) {
        binding.tvLastTime.text = time
    }

    private fun getLastTime() {
        lifecycleScope.launchWhenStarted {
            var lastTimeValue = lastTime.getLastTimeLoggedIn()
            if(lastTimeValue == -1L) {
                lastTimeValue = System.nanoTime()
            }
            updateLastTime(lastTimeValue.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleScope.launch {
            lastTime.recordLastTime()
        }
    }
}