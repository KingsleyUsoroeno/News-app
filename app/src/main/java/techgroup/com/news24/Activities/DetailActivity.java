package techgroup.com.news24.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import techgroup.com.news24.Models.News;
import techgroup.com.news24.R;

public class DetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView author;
    TextView newsDescription;
    TextView newsTitle;
    FloatingActionButton button_openFullStory;
    private static final String TAG = "DetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent detailIntent = getIntent();
//        final String intentName = detailIntent.getStringExtra("news_item");
        final News thisNews = detailIntent.getParcelableExtra("news_item");

        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
//        collapsingToolbar.setTitle(thisNews.getAuthor());
        toolbar.setTitle(thisNews.getTitle());


        button_openFullStory = findViewById(R.id.detailActivity_Button);


        imageView = findViewById(R.id.detailActivity_ImageView);
        author = findViewById(R.id.Author);
        newsTitle = findViewById(R.id.detailActivity_NewsTitle);
        newsDescription = findViewById(R.id.detailActivity_NewsDescription);

        Glide.with(this)
                .asBitmap()
                .load(thisNews.getUrlToImage())
                .into(imageView);

        author.setText(thisNews.getAuthor());
        newsTitle.setText(thisNews.getTitle());
        newsDescription.setText(thisNews.getDescription());

        button_openFullStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "Button Not Responding to Click: ");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(thisNews.getUrl()));
                startActivity(intent);
            }
        });

    }

}
