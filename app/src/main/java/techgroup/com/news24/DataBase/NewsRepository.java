package techgroup.com.news24.DataBase;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

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
    private LiveData<List<GeneralNews>> generalNewsLiveData;
    private LiveData<List<SportNews>> sportNewsLiveData;
    private LiveData<List<TechNews>> techNewsLiveData;


    public NewsRepository(Application application) {
        NewsDataBase newsDataBase = NewsDataBase.getInstance(application);
        newsDao = newsDataBase.newsDao();
        getAllNews = newsDao.getAllNews();
        generalNewsLiveData = newsDao.getAllGeneralNews();
        sportNewsLiveData = newsDao.getAllSportNews();
        techNewsLiveData = newsDao.getAllTechNews();
    }

    public void InsertNews(List<News> news) {

        new InsertNewsAsyncTask(newsDao, news).execute();
    }

    public LiveData<List<News>> getAllNews() {
        return getAllNews;
    }

    public LiveData<List<GeneralNews>> getGetAllGeneralNews() {
        return generalNewsLiveData;
    }

    public LiveData<List<SportNews>> getGetAllSport() {
        return sportNewsLiveData;
    }

    public LiveData<List<TechNews>> getAllSport() {
        return techNewsLiveData;
    }


    public LiveData<List<TechNews>> getGetAllTech() {
        return techNewsLiveData;
    }

    public void insertGeneral(List<GeneralNews> generalNews) {
        new InsertGeneralNews(newsDao, generalNews).execute(generalNews);

    }

    public void insertSport(List<SportNews> sportNews) {
        new InsertSportNews(newsDao, sportNews).execute();

    }

    public void insertTech(List<TechNews> techNews) {
        new InsertTechNews(newsDao, techNews).execute();

    }

    private static class InsertNewsAsyncTask extends AsyncTask<List<News>, Void, Void> {

        // So in these InsertPictureAsyncTask we must have Access to our
        // NoteDao as we use our NoteDao to perform Database operations
        private NewsDao newsDao;
        private List<News> mNews;

        public InsertNewsAsyncTask(NewsDao newsDao, List<News> news) {
            this.newsDao = newsDao;
            mNews = news;
        }

        @Override
        protected Void doInBackground(List<News>... contacts) {
            newsDao.insertNews(mNews);
            return null;
        }
    }

    private static class InsertGeneralNews extends AsyncTask<List<GeneralNews>, Void, Void> {

        // So in these InsertPictureAsyncTask we must have Access to our
        // NoteDao as we use our NoteDao to perform Database operations
        private NewsDao newsDao;
        private List<GeneralNews> generalNews;

        public InsertGeneralNews(NewsDao newsDao, List<GeneralNews> news) {
            this.newsDao = newsDao;
            generalNews = news;
        }


        @Override
        protected Void doInBackground(List<GeneralNews>... arrayLists) {
            newsDao.insertGeneral(generalNews);
            return null;
        }

    }

    private static class InsertSportNews extends AsyncTask<List<SportNews>, Void, Void> {

        // So in these InsertPictureAsyncTask we must have Access to our
        // NoteDao as we use our NoteDao to perform Database operations

        private NewsDao newsDao;
        private List<SportNews> sportNews;

        public InsertSportNews(NewsDao newsDao, List<SportNews> news) {

            this.newsDao = newsDao;
            sportNews = news;
        }


        @Override
        protected Void doInBackground(List<SportNews>... news) {
            newsDao.insertSport(sportNews);
            return null;
        }
    }

    private static class InsertTechNews extends AsyncTask<List<TechNews>, Void, Void> {

        // So in these InsertPictureAsyncTask we must have Access to our
        // NoteDao as we use our NoteDao to perform Database operations

        private NewsDao newsDao;
        private List<TechNews> techNews;

        public InsertTechNews(NewsDao newsDao, List<TechNews> tech) {

            this.newsDao = newsDao;
            techNews = tech;
        }


        @Override
        protected Void doInBackground(List<TechNews>... arrayLists) {
            newsDao.insertTech(techNews);
            return null;
        }
    }


}
