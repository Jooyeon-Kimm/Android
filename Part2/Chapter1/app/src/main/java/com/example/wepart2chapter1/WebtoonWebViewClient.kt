package com.example.wepart2chapter1

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

class WebtoonWebViewClient(
    private val progressBar: ProgressBar,
    private val saveData :(String) -> Unit,
) : WebViewClient() {

    // {shouldOverrideUrlLoading}
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
//        if(request != null && request.url.toString().contains("comic.naver.com"))
//            return false // 해당 URL 바깥으로 못 감
//        else return true

        // https://comic.naver.com/webtoon/detail?titleId=826609&no=51&week=tue
        if(request != null && request.url.toString().contains("comic.naver.com/webtoon")) {
            saveData(request.url.toString())
        }

        return super.shouldOverrideUrlLoading(view, request)

    }

    // {onPageFinished}
    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)

        progressBar.visibility = View.GONE
    }

    // {onPageStarted}
    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)

        progressBar.visibility = View.VISIBLE
    }

    // {onReceivedError}
    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        super.onReceivedError(view, request, error)
        // 에러 페이지를 띄워주거나.
    }
}