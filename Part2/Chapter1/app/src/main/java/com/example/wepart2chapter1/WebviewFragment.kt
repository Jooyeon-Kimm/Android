package com.example.wepart2chapter1

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.provider.Settings.System.putString
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.content.edit
import com.example.wepart2chapter1.databinding.FragmentWebviewBinding
import com.google.android.material.animation.AnimatableView.Listener


class WebviewFragment(private val position: Int, private val webViewUrl: String) : Fragment() {

    // 불변의 것은 static으로 사용하여라
    // companion object는 JAVA에서 사용하던 STATIC 임
    // const는 JAVA의 final과 같음
    companion object {
        const val SHARED_PREFERENCE = "WEB_HISTORY"
    }

    var listener : OnTabLayoutNameChanged ?= null
    private var _binding : FragmentWebviewBinding?= null
    private val binding get() = _binding!!

    // {onCreate}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    // {onCreateView}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebviewBinding.inflate(inflater, container, false)
        return binding.root
    }


    // {onViewCreated}
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val webView = binding.webView
        webView.webViewClient = WebtoonWebViewClient(binding.progressBar) { url ->
            activity?.getSharedPreferences("WEB_HISTORY", Context.MODE_PRIVATE)?.edit {
                putString("tab$position", url)
            }
        }
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(webViewUrl)

        binding.changeTabNameButton.setOnClickListener {
            val dialog = AlertDialog.Builder(context)
            val editText = EditText(context)
            dialog.setView(editText)
            // 확인, 저장
            dialog.setPositiveButton("저장") { _, _ ->
                activity?.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)?.edit {
                    putString("tab${position}_name", editText.text.toString())
                    listener?.nameChanged(position, editText.text.toString())
                }

            }
            // 취소
            dialog.setNeutralButton("취소") { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            dialog.show()
        }

        binding.backToLastButton.setOnClickListener {
            val sharedPreferences = activity?.getSharedPreferences("WEB_HISTORY", Context.MODE_PRIVATE)
            val url = sharedPreferences?.getString("tab$position", "")
            if (url.isNullOrEmpty()){
                Toast.makeText(context, "마지막 저장 시점이 없습니다.", Toast.LENGTH_SHORT).show()
            }else{
                binding.webView.loadUrl(url)
            }
        }

    }

    fun canGoBack() : Boolean {
        return binding.webView.canGoBack()
    }

    fun goBack() {
        binding.webView.goBack()
    }



}

interface OnTabLayoutNameChanged {
    fun nameChanged(position: Int, name: String)
}