package com.example.uoftfinalproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.uoftfinalproject.model.Product;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity {

    public  static String WEBDATA ="webData";
    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Log.d("WebActivity","WebActivity Run");
        ButterKnife.bind(this);
        product= getIntent().getParcelableExtra(WEBDATA);
        Log.d("webdata",product.getWeb());
        startWebView(product.getWeb());


    }

    private void startWebView(String url) {
        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);

        progressBar.setVisibility(View.VISIBLE);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(WebViewActivity.this, "Error:" + description, Toast.LENGTH_SHORT).show();

            }
        });
        webView.loadUrl(url);
    }

}
