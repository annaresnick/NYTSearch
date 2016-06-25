package com.example.aresnick.nytimessearch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.aresnick.nytimessearch.Article;
import com.example.aresnick.nytimessearch.R;

public class ArticleActivity extends AppCompatActivity {
    private Intent shareIntent;
    //@BindView(R.id.wvArticle) WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
        RecyclerView rvArticle = (RecyclerView) findViewById(R.id.rvArticles);
        setContentView(R.layout.activity_article);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Article article = (Article) getIntent().getSerializableExtra("article");

        WebView webView = (WebView) findViewById(R.id.wvArticle);
        assert webView != null;
        webView.setWebViewClient(new WebViewClient() {
                                    @Override
                                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                        view.loadUrl(url);
                                        return true;
                                    }
                                 }
        );
        webView.loadUrl(article.getWebUrl());

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ButterKnife.bind(this);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_article, menu);

        MenuItem item = menu.findItem(R.id.menu_item_share);
        ShareActionProvider miShare = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");

        // get reference to WebView
        WebView wvArticle = (WebView) findViewById(R.id.wvArticle);
        // pass in the URL currently being used by the WebView
        shareIntent.putExtra(Intent.EXTRA_TEXT, wvArticle.getUrl());
        miShare.setShareIntent(shareIntent);


        return super.onCreateOptionsMenu(menu);
    }

}
