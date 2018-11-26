package techgroup.com.news24.Interfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import techgroup.com.news24.Models.Student;

public interface RetrofitArrayApi {
    /*
     * Retrofit get annotation with our URL
     * And our method that will return us details of student.
     */
    @GET("api/RetrofitAndroidArrayResponse")
    Call<List<Student>> getStudentDetails();

}
