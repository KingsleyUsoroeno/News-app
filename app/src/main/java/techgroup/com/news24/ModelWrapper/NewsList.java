package techgroup.com.news24.ModelWrapper;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import techgroup.com.news24.Models.GeneralNews;
import techgroup.com.news24.Models.News;

public class NewsList {

    @SerializedName("articles")
    private ArrayList<News> allGeneralNews;

    public ArrayList<News> getAllGeneralNews() {
        return allGeneralNews;
    }
}
