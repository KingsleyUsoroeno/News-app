package techgroup.com.news24.NetworkOperations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetAllModelRetrofitInstance {
    private static Retrofit retrofit;

    // General  Base url for all the retrofit instances
    private static final String BASE_URL = "https://newsapi.org/";
    private static final String STUDENT_BASE_URL = "https://androidtutorialpoint.com/";
    private static final String OMNIBUS_BASE_URL = "http://omnibus.kodehauz.xyz/";
    /**
     * Create an instance of Retrofit object
     */
    public static Retrofit retrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getStudentResponse(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (retrofit == null){
             retrofit = new Retrofit.Builder()
                    .baseUrl(STUDENT_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getBusScheduleResponse(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(OMNIBUS_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }



}
