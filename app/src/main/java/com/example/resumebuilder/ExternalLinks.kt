package com.example.resumebuilder

import android.os.Bundle
import android.view.*
import android.webkit.WebView
import androidx.fragment.app.Fragment
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.fragment_external_links.*

class ExternalLinks : BaseFragment() {

    lateinit var webViewItem : WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_external_links, container, false)
        webViewItem = view.findViewById<WebView>(R.id.webView)
        webViewItem.webViewClient = WebViewClient()
        webViewItem.settings.javaScriptEnabled = true
        webViewItem.settings.builtInZoomControls = true
        return view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menuGitHub -> webViewItem.loadUrl("https://github.com")
            R.id.menuLinkedIn -> webViewItem.loadUrl("https://linkedin.com")
        }
        return super.onOptionsItemSelected(item)
    }
}