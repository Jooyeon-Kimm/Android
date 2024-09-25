package com.part1.chapter4

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.part1.chapter4.R.layout.activity_input
import com.part1.chapter4.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 스피너 설정
        binding.bloodTypeSpinner.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.blood_types,
            android.R.layout.simple_list_item_1
        )

        // 캘린더 설정
        binding.birthdateLayer.setOnClickListener {
            val listener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                binding.birthdateTextView.text = "$year-${month.inc()}-$dayOfMonth"
            }

            DatePickerDialog(
                this,
                listener,
                2000,
                1,
                1
            ).show()
        }

        // 체크박스 설정
        binding.warningCheckBox.setOnCheckedChangeListener { _, isChecked ->
            binding.warningEditText.isVisible = isChecked
        }

        // 초기 선택 여부 설정
        binding.warningEditText.isVisible = binding.warningCheckBox.isChecked

        // 저장 버튼 설정
        binding.saveButton.setOnClickListener {
            saveData()
            finish() // 액티비티 종료
        }
    }


    // 저장을 위해 sharedPreference 를 사용하자.
    private fun saveData() {
        with(getSharedPreferences("userInformation", Context.MODE_PRIVATE).edit()) {
            putString(NAME, binding.nameEditText.text.toString())
            putString(BIRTHDATE, binding.birthdateTextView.text.toString())
            putString(BLOOD_TYPE,getBloodType())
            putString(EMERGENCY_CONTACT, binding.emergencyContactEditText.text.toString())
            putString(WARNING, getWarning())
            apply()
        }

        // 저장되었습니다. 알림 띄우기
        // 마찬가지로 show()를 붙여주어야 화면에 나타남
        Toast.makeText(this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show()

    }

    // 혈액형 정보 받아오기
    private fun getBloodType() : String {
        val bloodAlphabet = binding.bloodTypeSpinner.selectedItem.toString()
        val bloodSign = if(binding.bloodTypePlus.isChecked) "+" else "-"
        return "$bloodSign$bloodAlphabet"
    }

    // 주의사항 받아오기
    private fun getWarning() : String {
        return if(binding.warningCheckBox.isChecked) binding.warningEditText.text.toString() else ""
    }


}