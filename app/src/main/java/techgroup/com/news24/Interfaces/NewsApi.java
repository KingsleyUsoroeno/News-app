package techgroup.com.news24.Interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import techgroup.com.news24.ModelWrapper.GeneralNewsList;
import techgroup.com.news24.ModelWrapper.NewsList;
import techgroup.com.news24.ModelWrapper.SportNewsList;
import techgroup.com.news24.ModelWrapper.TechNewsList;

public interface NewsApi {

    @GET("v2/top-headlines")
    Call<GeneralNewsList> getGeneralNewsData(@Query("sources") String sources , @Query("apiKey") String apiKey);

    // GeneralNews Api
    @GET("v2/everything")
    Call<NewsList> getNewsData(@Query("domains") String domains , @Query("apiKey") String apiKey);

    //Sport Data Api
    @GET("v2/top-headlines")
    Call<SportNewsList> getSportData(@Query("sources") String sources , @Query("apiKey") String apiKey);

    // TechNews Data Api
    @GET("/v2/top-headlines/")
    Call<TechNewsList> getTechNewsData(@Query("sources") String sources , @Query("apiKey") String apiKey);

}
