package techgroup.com.news24.Interfaces;

import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import techgroup.com.news24.ModelWrapper.ModelList;

public interface NewsApi {

    @GET("v2/top-headlines")
    Call<ModelList.GeneralNewsList> getGeneralNewsData(@Query("sources") String sources , @Query("apiKey") String apiKey);

    // GeneralNews Api
    @GET("v2/everything")
    Call<ModelList.NewsList> getNewsData(@Query("domains") String domains , @Query("apiKey") String apiKey);

    //Sport Data Api
    @GET("v2/top-headlines")
    Call<ModelList.SportNewsList> getSportData(@Query("sources")@NonNull String sources, @Query("apiKey")@NonNull String apiKey);

    // TechNews Data Api
    @GET("/v2/top-headlines/")
    Call<ModelList.TechNewsList> getTechNewsData(@Query("sources") String sources , @Query("apiKey") String apiKey);

}
