package techgroup.com.news24.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import techgroup.com.news24.Models.GeneralNews;
import techgroup.com.news24.Models.News;
import techgroup.com.news24.Models.SportNews;
import techgroup.com.news24.Models.TechNews;

@Database(entities = {News.class, GeneralNews.class,SportNews.class, TechNews.class},
    version = 2,exportSchema = false)


public abstract class NewsDataBase extends RoomDatabase {

    public abstract NewsDao newsDao();

    private static NewsDataBase INSTANCE;

    // So here we Create an Instance of Our DataBase or like Most developers
    // a Singleton

    public static synchronized NewsDataBase getInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    NewsDataBase.class,
                    "news_database")
                    .fallbackToDestructiveMigration()
                    // So we use the Callback to populate our Database before we
                    // Start adding Stuff into the DataBase Ourselves
                    // But we Choosing not to Now
                    .build();
        }
        return INSTANCE;
    }
}
