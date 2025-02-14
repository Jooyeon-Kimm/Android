package com.example.chapter9

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chapter9.MediaPlayerService
import com.example.chapter9.databinding.ActivityMainBinding
import com.part1.chapter9.MEDIA_PLAYER_PAUSE
import com.part1.chapter9.MEDIA_PLAYER_PLAY
import com.part1.chapter9.MEDIA_PLAYER_STOP


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playButton.setOnClickListener { mediaPlayerPlay() }
        binding.pauseButton.setOnClickListener { mediaPlayerPause() }
        binding.stopButton.setOnClickListener { mediaPlayerStop() }
    }

    private fun mediaPlayerPlay() {
        // Service 시작하는 방법
        val intent = Intent(this, MediaPlayerService::class.java)
            .apply { action = MEDIA_PLAYER_PLAY }
        startService(intent)

//        if (mediaPlayer == null) {
//            mediaPlayer = MediaPlayer.create(this, R.raw.free).apply {
//                this.isLooping = true
//            }
//        }
//        mediaPlayer?.start()
    }

    private fun mediaPlayerPause() {
        val intent = Intent(this, MediaPlayerService::class.java)
            .apply { action = MEDIA_PLAYER_PAUSE }
        startService(intent)
        // mediaPlayer?.pause()
    }

    private fun mediaPlayerStop() {
        val intent = Intent(this, MediaPlayerService::class.java)
            .apply { action = MEDIA_PLAYER_STOP }
        startService(intent)
//        mediaPlayer?.stop()
//        mediaPlayer?.release()
//        mediaPlayer = null
    }

    // 액티비 종료 시, 메모리 해제
    override fun onDestroy() {
        stopService(Intent(this, MediaPlayerService::class.java))
        super .onDestroy()
    }
}
