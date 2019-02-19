package techgroup.com.news24.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import techgroup.com.news24.Models.News;
import techgroup.com.news24.R;

public class DetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView tv_authorName;
    TextView tv_newsDescription;
    TextView tv_newsTitle;
    FloatingActionButton fab;
    private static final String TAG = "DetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Init Views
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fab = findViewById(R.id.detailActivity_Button);
        imageView = findViewById(R.id.detailActivity_ImageView);
        tv_authorName = findViewById(R.id.Author);
        tv_newsTitle = findViewById(R.id.detailActivity_NewsTitle);
        tv_newsDescription = findViewById(R.id.detailActivity_NewsDescription);

        final News thisNews = getIntent().getParcelableExtra("news_item");
        toolbar.setTitle(thisNews.getTitle());
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
//        collapsingToolbar.setTitle(thisNews.getAuthor());
        toolbar.setTitle(thisNews.getTitle());
        initDataWithViews(imageView, thisNews, fab, tv_authorName, tv_newsTitle, tv_newsDescription);
    }

    private void initDataWithViews(@NonNull ImageView newsImage, final News news, FloatingActionButton btn_openBrowser, TextView... textViews) {
        // using a Varargs as i don't know how many textViews i would pass into my param
        Glide.with(this)
                .asBitmap()
                .load(news.getUrlToImage())
                .into(newsImage);
        // first TextView i pass into the params and con-currently
        textViews[0].setText(news.getAuthor());
        textViews[1].setText(news.getTitle());
        if (news.getDescription().equals("")){
            textViews[2].setText("News Description not found");
        }else {
            textViews[2].setText(news.getDescription());
        }
        btn_openBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Button Not Responding to Click: ");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(news.getUrl()));
                startActivity(intent);
            }
        });
    }

}
