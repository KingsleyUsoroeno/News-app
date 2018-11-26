package techgroup.com.news24.ModelWrapper;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import techgroup.com.news24.Models.GeneralNews;

public class GeneralNewsList {

    @SerializedName("articles")
    private ArrayList<GeneralNews> allGeneralNews;

    public ArrayList<GeneralNews> getAllGeneralNews() {
        return allGeneralNews;
    }
}
