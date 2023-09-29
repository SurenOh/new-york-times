package com.myapp.newyorktimes.ui.buy

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.myapp.newyorktimes.databinding.FragmentBuyBinding
import com.myapp.newyorktimes.ui.BaseFragment
import com.myapp.newyorktimes.ui.buy.client.MyWebViewClient

class BuyFragment : BaseFragment<FragmentBuyBinding>() {
    private val args: BuyFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupView() = with(binding) {
        backButton.setOnClickListener { popBackStack() }
        try {
            webView.webViewClient = MyWebViewClient()
            webView.settings.javaScriptEnabled = true
            webView.loadUrl(args.link)
        } catch (e: Exception) {
            e.stackTrace
        }
    }

    override fun inflateBinding() = FragmentBuyBinding.inflate(layoutInflater)
}