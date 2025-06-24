package com.part1.chapter4

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.part1.chapter4.databinding.ActivityInputBinding
import com.part1.chapter4.databinding.ActivityMainBinding
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Intent
        // 의도: MainActivity 에서 편집 버튼을 누르면
        // InputActivity 로 이동한다.
        binding.goInputActivityButton.setOnClickListener {
            // Intent 가 시작하는 곳의 context 를 알려주어야 함 ( MainActivity 가 가진 context)
            // InputActivity 를 call 하겠다는 의도
            val intent = Intent(this, InputActivity::class.java)


            // 다른 Activity 실행
            startActivity(intent)
        }

        binding.deleteButton.setOnClickListener {
            deleteData()
        }

        binding.emergencyContactLayer.setOnClickListener {
                with(Intent(Intent.ACTION_VIEW)) {
                    val phoneNumber = binding.emergencyContactValueTextView.text.toString()
                        .replace("-","")
                    data = Uri.parse("tel:$phoneNumber")
                    startActivity(this)
                }
        }

    }

    override fun onResume() {
        super.onResume()
        getDataUiUpdate() // onCreate() 가 아닌, onResume() 에서 호출돼야 함.

        // onCreate() 는 액티비티가 최초실행될 때
        // 액티비티 종류된 후 다른 액티비티가 실행될 때는
        // onCreate() 가 아닌 onResume() 이 호출되므로

    }
    // 데이터를 가져와서 UI 업데이트를 함께 했다.
    // 두 가지 기능을 나누어서 하는 것이 좋다.
    // 함수 하나에는 하나의 기능이 있는 것이 좋다.
    private fun getDataUiUpdate() {
        with(getSharedPreferences(USER_INFORMATION, Context.MODE_PRIVATE)) {
            binding.nameValueTextView.text = getString(NAME, "미정")
            binding.birthdateValueTextView.text = getString(BIRTHDATE, "미정")
            binding.bloodtypeValueTextView.text = getString(BLOOD_TYPE, "미정")
            binding.emergencyContactValueTextView.text = getString(EMERGENCY_CONTACT, "미정")
            val warning = getString(WARNING, "")

            binding.warningTextView.isVisible = warning.isNullOrEmpty().not()
            binding.warningValueTextView.isVisible = warning.isNullOrEmpty().not()

            if(!warning.isNullOrEmpty()){
                binding.warningValueTextView.text = warning
            }

        }
    }

    private fun deleteData() {
        with(getSharedPreferences(USER_INFORMATION, MODE_PRIVATE).edit()) {
            clear() // 파일에 있는 모든 데이터 삭제
            apply() // apply() 코드가 없으면, 마지막에 실행되지 않음
            getDataUiUpdate()
        }

        Toast.makeText(this, "초기화를 완료했습니다.", Toast.LENGTH_SHORT)
    }
}


