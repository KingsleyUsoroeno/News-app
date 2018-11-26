package techgroup.com.news24.ModelWrapper;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import techgroup.com.news24.Models.News;
import techgroup.com.news24.Models.SportNews;

public class SportNewsList {

    @SerializedName("articles")
    private ArrayList<SportNews> allSportNews;

    public ArrayList<SportNews> getAllSportNews() {
        return allSportNews;
    }
}
