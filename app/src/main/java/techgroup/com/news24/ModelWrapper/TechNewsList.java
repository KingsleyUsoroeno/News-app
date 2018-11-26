package techgroup.com.news24.ModelWrapper;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import techgroup.com.news24.Models.News;

public class TechNewsList {

    @SerializedName("articles")
    private ArrayList<News> allTechNews;

    public ArrayList<News> getAllTechNews() {
        return allTechNews;
    }
}
