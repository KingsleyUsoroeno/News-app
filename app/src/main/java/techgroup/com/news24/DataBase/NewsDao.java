package techgroup.com.news24.DataBase;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

import techgroup.com.news24.Models.GeneralNews;
import techgroup.com.news24.Models.News;
import techgroup.com.news24.Models.SportNews;
import techgroup.com.news24.Models.TechNews;

@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertNews(List<News> news);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertGeneral(List<GeneralNews> generalNews);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertSport(List<SportNews> sportNews);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTech(List<TechNews> techNews);

    @Query("SELECT * FROM news ")
    LiveData<List<News>> getAllNews();

    @Query("SELECT * FROM general_news")
    LiveData<List<GeneralNews>> getAllGeneralNews();

    @Query("SELECT * FROM sport_news")
    LiveData<List<SportNews>> getAllSportNews();

    @Query("SELECT * FROM tech_news")
    LiveData<List<TechNews>> getAllTechNews();
}
