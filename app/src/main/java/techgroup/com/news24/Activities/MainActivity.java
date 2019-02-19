package techgroup.com.news24.Activities;

import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techgroup.com.news24.Adapter.FragmentAdapter;
import techgroup.com.news24.Fragments.GeneralNewsFragment;
import techgroup.com.news24.Fragments.SportFragment;
import techgroup.com.news24.Fragments.TechNewsFragment;
import techgroup.com.news24.Interfaces.BusScheduleApi;
import techgroup.com.news24.Interfaces.NewsApi;
import techgroup.com.news24.Interfaces.RetrofitArrayApi;
import techgroup.com.news24.ModelWrapper.ModelList;
import techgroup.com.news24.Models.BusSchedule;
import techgroup.com.news24.Models.BusStop;
import techgroup.com.news24.Models.GeneralNews;
import techgroup.com.news24.Models.Route;
import techgroup.com.news24.Models.SportNews;
import techgroup.com.news24.Models.Student;
import techgroup.com.news24.Models.TechNews;
import techgroup.com.news24.NetworkOperations.GetAllModelRetrofitInstance;
import techgroup.com.news24.R;

public class MainActivity extends AppCompatActivity {

    public Toolbar mToolbar;
    private static final String TAG = "MainActivity";
    ViewModel databaseViewModel;
    // Create Constants
    public static List<GeneralNews> generalNewsArray;
    public static List<SportNews> sportNewsArray;
    public static List<TechNews> techNewsArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Finding our ToolBar by its ID

        mToolbar = findViewById(R.id.toolBar);
        mToolbar.setTitle(R.string.toolBar_text);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = findViewById(R.id.viewPager);

        // Create an adapter that knows which fragment should be shown on each page
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),
                MainActivity.this);

        // Set the adapter onto the view pager
        viewPager.setAdapter(fragmentAdapter);

        TabLayout tabLayout = findViewById(R.id.sliding_tab);
        tabLayout.setupWithViewPager(viewPager);
        fetchAndInsertGeneralNewsData();
        fetchAndInsertSportNewsData();
        fetchAndInsertTechNewsData();
        databaseViewModel = ViewModelProviders.of(this).get(ViewModel.class);
    }

    public void studentResponseData() {
        RetrofitArrayApi arrayApi = GetAllModelRetrofitInstance.getStudentResponse().create(RetrofitArrayApi.class);
        Call<List<Student>> call = arrayApi.getStudentDetails();

        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                try {
                    List<Student> studentData = response.body();
                    for (int i = 0; i < studentData.size(); i++) {
                        Student student = studentData.get(i);

                        Log.d(TAG, "studentId is  : " + student.getStudentId());
                        Log.d(TAG, "studentName is  : " + student.getStudentName());
                        Log.d(TAG, "studentMarks is : " + student.getStudentMarks() + "\n");
                    }

                } catch (Exception e) {
                    Log.d(TAG, "Exception due to  : " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.d(TAG, "Response Failed Due to : " + t.getMessage());
            }
        });
    }

    public void getBusScheduleData() {
        BusScheduleApi busScheduleApi = GetAllModelRetrofitInstance.getBusScheduleResponse().create(BusScheduleApi.class);
        Call<List<BusSchedule>> scheduledData = busScheduleApi.getAllBusSchedule();
        scheduledData.enqueue(new Callback<List<BusSchedule>>() {
            @Override
            public void onResponse(Call<List<BusSchedule>> call, Response<List<BusSchedule>> response) {

                try {
                    if (!response.isSuccessful()) {
                        Log.d(TAG, "Response Failed due to : " + response.code());
                    } else {
                        List<BusSchedule> scheduleBuses = response.body();
                        for (int i = 0; i < scheduleBuses.size(); i++) {
                            BusSchedule schedule = scheduleBuses.get(i);
                            Log.d(TAG, "Bus Schedule id is  : " + schedule.getId());
                            Log.d(TAG, "Bus Schedule Departure is   : " + schedule.getDeparture_date_time());
                            Log.d(TAG, "Bus Schedule arrival is   : " + schedule.getArrival_date_time());
                            Log.d(TAG, "Bus Schedule cost is  : " + schedule.getCost());
                            Log.d(TAG, " route id is   : " + schedule.getRoute_id());
                            Log.d(TAG, "Bus Schedule bus stop array is  : " + schedule.getBusStop().size());

                            List<BusStop> busStopsList = schedule.getBusStop();

                            if (schedule.getBusStop().size() == 5 || schedule.getBusStop().size() == 2 || schedule.getBusStop().size() == 4) {
                                for (int j = 0; j < busStopsList.size(); j++) {
                                    BusStop stop = busStopsList.get(j);
                                    Log.d(TAG, "BusStop cost is --> : " + stop.getCost());
                                }
                            }else {
                                Toast.makeText(MainActivity.this, "Array out of Bonds", Toast.LENGTH_SHORT).show();
                            }
                            Route route = schedule.getRoutes();
                            Log.d(TAG, "Route Object response is : " + route.getName());

                        }
                    }
                } catch (Exception e) {
                    Log.d(TAG, "Exception while parsing Json due to : " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<BusSchedule>> call, Throwable t) {
                Log.d(TAG, "onFailure Response: " + t.getMessage());
            }
        });

    }

    public void fetchAndInsertGeneralNewsData(){
        NewsApi newsApi = GetAllModelRetrofitInstance.retrofitInstance().create(NewsApi.class);
        final Call<ModelList.GeneralNewsList> newsListCall = newsApi.getGeneralNewsData("abc-news", GeneralNewsFragment.API_KEY);
        /**  Log the URL called*/
        Log.wtf("URL Called", newsListCall.request().url() + "");
        newsListCall.enqueue(new Callback<ModelList.GeneralNewsList>() {
            @Override
            public void onResponse(Call<ModelList.GeneralNewsList> call, Response<ModelList.GeneralNewsList> response) {
                if (response.isSuccessful()){
                    if (response.body() != null) {
                        databaseViewModel.InsertGeneral(response.body().getAllGeneralNews());
                        generalNewsArray = response.body().getAllGeneralNews();
                        Log.d(TAG, "onResponse: " + generalNewsArray);
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelList.GeneralNewsList> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                Log.d(TAG, "App Might Crash: ");
            }
        });
    }

    public void fetchAndInsertSportNewsData(){
        NewsApi sportApi = GetAllModelRetrofitInstance.retrofitInstance().create(NewsApi.class);
        /*** Create handle for the RetrofitInstance interface*/
        Call<ModelList.SportNewsList> sportData = sportApi.getSportData("espn", TechNewsFragment.API_KEY);
        Log.d(TAG, "Sport Url called is : " + sportData.request().url() + "");
        sportData.enqueue(new Callback<ModelList.SportNewsList>() {
            @Override
            public void onResponse(Call<ModelList.SportNewsList> call, Response<ModelList.SportNewsList> response) {
                if (response.body() != null){
                    databaseViewModel.InsertSport(response.body().getAllSportNews());
                    sportNewsArray = response.body().getAllSportNews();
                }
            }

            @Override
            public void onFailure(Call<ModelList.SportNewsList> call, Throwable t) {
                Log.d(TAG, "Network Error : " + t.getMessage());
            }
        });
    }

    private void fetchAndInsertTechNewsData(){
        NewsApi api = GetAllModelRetrofitInstance.retrofitInstance()
                .create(NewsApi.class);
        Call<ModelList.TechNewsList> call = api.getTechNewsData("techcrunch", SportFragment.API_KEY);

        call.enqueue(new Callback<ModelList.TechNewsList>() {
            @Override
            public void onResponse(Call<ModelList.TechNewsList> call, Response<ModelList.TechNewsList> response) {
                if (response.body() != null){
                    databaseViewModel.InsertTech(response.body().getAllTechNews());
                    techNewsArray = response.body().getAllTechNews();
                }
            }

            @Override
            public void onFailure(Call<ModelList.TechNewsList> call, Throwable t) {
                Log.d(TAG, "onFailure: " +  t.getMessage());
                Log.d(TAG, "App Crashes: ");
            }
        });
    }
}

