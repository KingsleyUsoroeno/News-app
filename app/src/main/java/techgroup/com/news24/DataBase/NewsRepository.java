package techgroup.com.news24.DataBase;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import techgroup.com.news24.Models.GeneralNews;
import techgroup.com.news24.Models.News;
import techgroup.com.news24.Models.SportNews;
import techgroup.com.news24.Models.TechNews;

public class NewsRepository {

    // So Our Repository Has Access to our PictureDao to perform Database Operation
    private NewsDao newsDao;
    // And Access to Our LiveData which can Also be Found in our PictureDao to be able to
    // Observe the data and Save it in Our Database
    private LiveData<List<News>> getAllNews;
    private LiveData<List<GeneralNews>> getAllGeneralNews;
    private LiveData<List<SportNews>> getAllSport;
    private LiveData<List<TechNews>> getAllTech;


    public NewsRepository(Application application) {

        NewsDataBase newsDataBase = NewsDataBase.getInstance(application);
        newsDao = newsDataBase.newsDao();
        getAllNews = newsDao.getAllNews();
        getAllGeneralNews = newsDao.getAllGeneralNews();
        getAllSport = newsDao.getAllSportNews();
        getAllTech = newsDao.getAllTechNews();


    }

    public void InsertNews(ArrayList<News> news) {

        new InsertNewsAsyncTask(newsDao, news).execute();
    }

    public LiveData<List<News>> getAllNews() {
        return getAllNews;
    }

    public LiveData<List<GeneralNews>> getGetAllGeneralNews() {
        return getAllGeneralNews;
    }

    public LiveData<List<SportNews>> getGetAllSport() {
        return getAllSport;
    }
    public LiveData<List<TechNews>> getAllSport() {
        return getAllTech;
    }


    public LiveData<List<TechNews>> getGetAllTech() {
        return getAllTech;
    }
    public void insertGeneral(ArrayList<GeneralNews> generalNews){
        new InsertGeneralNews(newsDao,generalNews).execute(generalNews);

    }
    public void insertSport(ArrayList<SportNews> sportNews){
        new InsertSportNews(newsDao,sportNews).execute();

    }
    public void insertTech(ArrayList<TechNews> techNews){
        new InsertTechNews(newsDao,techNews).execute();

    }

    private static class InsertNewsAsyncTask extends AsyncTask<ArrayList<News>, Void, Void> {

        // So in these InsertPictureAsyncTask we must have Access to our
        // NoteDao as we use our NoteDao to perform Database operations

        private NewsDao newsDao;
        private ArrayList<News> mNews;

        public InsertNewsAsyncTask(NewsDao newsDao, ArrayList<News> news) {

            this.newsDao = newsDao;
            mNews = news;
        }

        @Override
        protected Void doInBackground(ArrayList<News>... contacts) {
            newsDao.insertNews(mNews);
            return null;
        }
    }

    private static class InsertGeneralNews extends AsyncTask<ArrayList<GeneralNews>, Void, Void> {

        // So in these InsertPictureAsyncTask we must have Access to our
        // NoteDao as we use our NoteDao to perform Database operations

        private NewsDao newsDao;
        private ArrayList<GeneralNews> generalNews;

        public InsertGeneralNews(NewsDao newsDao, ArrayList<GeneralNews> news) {

            this.newsDao = newsDao;
            generalNews = news;
        }


        @Override
        protected Void doInBackground(ArrayList<GeneralNews>... arrayLists) {
            newsDao.insertGeneral(generalNews);
            return null;
        }

    }

    private static class InsertSportNews extends AsyncTask<ArrayList<SportNews>, Void, Void> {

        // So in these InsertPictureAsyncTask we must have Access to our
        // NoteDao as we use our NoteDao to perform Database operations

        private NewsDao newsDao;
        private ArrayList<SportNews> sportNews;

        public InsertSportNews(NewsDao newsDao, ArrayList<SportNews> news) {

            this.newsDao = newsDao;
            sportNews = news;
        }


        @Override
        protected Void doInBackground(ArrayList<SportNews>... news) {
            newsDao.insertSport(sportNews);
            return null;
        }
    }

    private static class InsertTechNews extends AsyncTask<ArrayList<TechNews>, Void, Void> {

        // So in these InsertPictureAsyncTask we must have Access to our
        // NoteDao as we use our NoteDao to perform Database operations

        private NewsDao newsDao;
        private ArrayList<TechNews> Tnews;

        public InsertTechNews(NewsDao newsDao, ArrayList<TechNews> techNews) {

            this.newsDao = newsDao;
            Tnews = techNews;
        }


        @Override
        protected Void doInBackground(ArrayList<TechNews>... arrayLists) {
            newsDao.insertTech(Tnews);
            return null;
        }
    }


}
