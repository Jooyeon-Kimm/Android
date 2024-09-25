package com.part1.chapter6

import android.media.AudioManager
import android.media.ToneGenerator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import com.part1.chapter6.databinding.ActivityMainBinding
import com.part1.chapter6.databinding.DialogCountdownSettingBinding
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var countdownSecond = 10
    private var currentDeciSecond = 0 // 0.1 초마다 1씩 증가하는 변수
    private var currentCountDownDeciSecond = countdownSecond * 10
    private var timer : Timer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.countdownTextView.setOnClickListener {
            showCountdownSettingDialog()
        }

        binding.startButton.setOnClickListener {
            start()
            binding.startButton.isVisible = false
            binding.stopButton.isVisible = false
            binding.pauseButton.isVisible = true
            binding.lapButton.isVisible = true
        }

        binding.stopButton.setOnClickListener {
            showAlertDialog()
        }

        binding.pauseButton.setOnClickListener {
            pause()
            binding.startButton.isVisible = true
            binding.stopButton.isVisible = true
            binding.pauseButton.isVisible = false
            binding.lapButton.isVisible = false
        }

        binding.lapButton.setOnClickListener {
            lap()
        }

        initCountDownViews()
    }
    private fun initCountDownViews() {
        binding.countdownTextView.text = String.format("%02d", countdownSecond)
        binding.countDownProgressBar.progress = 100
    }

    private fun start() {
        // Thread { }
        timer = timer(initialDelay = 0, period = 100) {
            if ( currentCountDownDeciSecond == 0) {
                currentDeciSecond += 1 // 0.1 초씩 증가
                // Log.d("currentDeciSecond", currentDeciSecond.toString())

                val minute = currentDeciSecond.div(10) / 60
                val second = currentDeciSecond.div(10) % 60
                val deciSecond = currentDeciSecond % 10

                // Activity 내부의 메소드이므로, runOnUiThread 를 바로 호출 가능
                runOnUiThread {
                    // UI 에 숫자 업데이트
                    binding.timeTextView.text=
                        String.format("%02d:%02d", minute, second)
                    binding.tickTextView.text = deciSecond.toString()

                    binding.countdownGroup.isVisible = false
                }

            } else { // countdown 내용 구현 ( 0 이 아닐 때 )
                currentCountDownDeciSecond -= 1 // 빼기
                val second = currentCountDownDeciSecond / 10

                // progress bar 초기화
                val progress = (currentCountDownDeciSecond / (countdownSecond * 10f )) * 100
                // integer / integer 계산 결과가 1보다 작으면, 0 으로 출력되므로
                // 두 숫자 중 하나를 소숫점을 표현할 수 있는 실수로 변경해야했다. ( 10f )

                binding.root.post {
                    binding.countDownProgressBar.progress = progress.toInt()
                    // binding 의 progress 는 정수 타입이고
                    // 우변의 progress 변수는 실수 타입이므로, 정수 타입으로 변경

                    // UI 업데이트
                    binding.countdownTextView.text = String.format("%02d", second)

                }
            }

            // 3초 전 BEEP
            if ( currentDeciSecond == 0 && currentCountDownDeciSecond < 31
                && currentCountDownDeciSecond % 10 == 0) {
                val toneType = if ( currentCountDownDeciSecond == 0) ToneGenerator.TONE_CDMA_HIGH_L else ToneGenerator.TONE_CDMA_ANSWER
                ToneGenerator(AudioManager.STREAM_ALARM, ToneGenerator.MAX_VOLUME)
                    .startTone( toneType, 100 ) // 100 ms = 0.1 s
            }




        }
    }

    private fun stop() {
        // 처음상태와 동일하게
        binding.startButton.isVisible = true
        binding.stopButton.isVisible = true
        binding.pauseButton.isVisible = false
        binding.lapButton.isVisible = false

        currentDeciSecond = 0
        binding.timeTextView.text ="00:00"
        binding.tickTextView.text = "0"
        // pause 단계에서 timer 는 없앴기 때문에, 이 부분 처리는 필요 없음

        binding.countdownGroup.isVisible = true
        initCountDownViews()

        binding.lapContainerLinearLayout.removeAllViews()
    }

    private fun pause() {
        timer?.cancel()
        timer = null
    }

    // 실행 중에 파란색 체크 버튼을 누르면
    // 해당 시간을 UI 에 업데이트
    // scroll view 내에 linear layout 하나가 있다.

    // linear layout 에 text view 를 추가하자.
    private fun lap() {
        // lab 할 수 없는 경우
        if (currentDeciSecond == 0 ) return

        // text 를 추가할 layout 받아오는 container
        val container = binding.lapContainerLinearLayout

        val lapTextView = TextView(this).apply {
            textSize = 20f // text size 는 float 으로
            gravity = Gravity.CENTER

            val minute = currentDeciSecond.div(10) / 60
            val second = currentDeciSecond.div(10) % 60
            val deciSecond = currentDeciSecond % 10

            // 몇 번째 lap 인지, 현재 시각
            // 1) 01:03 0
            text = container.childCount.inc().toString() + ") "+ String.format("%02d:%02d %01d", minute, second, deciSecond)

            setPadding(30) // left, top, right, bottom
            // 숫자가 하나면 모든 padding 이 30
        }.let { labTextView ->
            container.addView(labTextView, 0)
        } // container.addView(lapTextView, 0)
    }



    private fun showCountdownSettingDialog() {
        AlertDialog.Builder(this).apply {
            val dialogBinding = DialogCountdownSettingBinding.inflate(layoutInflater)
            with(dialogBinding.countdownSecondPicker) {
                maxValue = 20
                minValue = 0
                value = countdownSecond
            }
            setTitle("카운트다운 설정")
            setView(dialogBinding.root) // setContentView 가 아님

            setPositiveButton("확인") { _, _ ->
                countdownSecond = dialogBinding.countdownSecondPicker.value // 데이터 업데이트
                currentCountDownDeciSecond = countdownSecond * 10
                binding.countdownTextView.text = String.format("%02d", countdownSecond) // UI 업데이트
            }
            setNegativeButton("취소", null)
        }.show()
    }

    // stopButton Dialog
    private fun showAlertDialog() {
        AlertDialog.Builder(this).apply {
            // setTitle()
            setMessage("종료하시겠습니까?")
            setPositiveButton("네") { _, _ -> // dialog, id
                stop()
            }
            setNegativeButton("아니오", null)
        }.show() // 화면에 보여주기
    }
}