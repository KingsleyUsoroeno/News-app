package techgroup.com.news24.Activities;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import techgroup.com.news24.DataBase.NewsRepository;
import techgroup.com.news24.Models.GeneralNews;
import techgroup.com.news24.Models.News;
import techgroup.com.news24.Models.SportNews;
import techgroup.com.news24.Models.TechNews;

public class ViewModel extends AndroidViewModel {

    // Our ViewModel class should have a Reference to our
    // NoteRepository and we should never pass a Context
    // or have a Reference to any Activity in Our ViewModel as Our ViewModel is
    // Meant to Outlive our Activity lifeCycle to survive Configuration Changes
    // Our ViewModel should have a Reference to the LiveData in our NoteDao as it
    // uses the LiveData to constantly notifyDataSetChanged on our Layouts;

    private NewsRepository newsRepository;
    private LiveData<List<News>> allNews;
    private LiveData<List<GeneralNews>> GeneralNews;
    private LiveData<List<SportNews>> sportNews;


    public ViewModel(@NonNull Application application) {
        super(application);

        newsRepository = new NewsRepository(application);
        allNews = newsRepository.getAllNews();
        GeneralNews = newsRepository.getGetAllGeneralNews();
        sportNews = newsRepository.getGetAllSport();

        // All we all Know that our Activities should only have Access to
        // Our ViewHolder right, so below we create a Method that instantiates
        // and that we can use to send data to our Activities;
        // so these Method Calls the NoteRepository method that does the insertInBackground
        // methods
    }

    public void InsertNews(ArrayList<News> news) {
        newsRepository.InsertNews(news);
    }

    public LiveData<List<News>> getAllNews() {
        return allNews;
    }

    public LiveData<List<GeneralNews>> getGeneralNews(){
        return GeneralNews;
    }

    public LiveData<List<SportNews>> getSportNews() {
        return sportNews;
    }

    public void InsertGeneral(ArrayList<GeneralNews> generalNews) {
        newsRepository.insertGeneral(generalNews);
    }

    public void InsertSport(ArrayList<SportNews> sportNews) {
        newsRepository.insertSport(sportNews);
    }
    public void InsertTech(ArrayList<TechNews> techNews) {
        newsRepository.insertTech(techNews);
    }

}
