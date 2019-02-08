package techgroup.com.news24.ModelWrapper;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import techgroup.com.news24.Models.GeneralNews;
import techgroup.com.news24.Models.News;
import techgroup.com.news24.Models.SportNews;
import techgroup.com.news24.Models.TechNews;

public class ModelList {

    public static class GeneralNewsList {

        @SerializedName("articles")
        private List<GeneralNews> allGeneralNews;

        public List<GeneralNews> getAllGeneralNews() {
            return allGeneralNews;
        }
    }

    public static class NewsList {

        @SerializedName("articles")
        private ArrayList<News> allGeneralNews;

        public ArrayList<News> getAllGeneralNews() {
            return allGeneralNews;
        }
    }

    public static class SportNewsList {

        @SerializedName("articles")
        private List<SportNews> allSportNews;

        public List<SportNews> getAllSportNews() {
            return allSportNews;
        }
    }

    public static class TechNewsList {

        @SerializedName("articles")
        private List<TechNews> allTechNews;

        public List<TechNews> getAllTechNews() {
            return allTechNews;
        }
    }
}
