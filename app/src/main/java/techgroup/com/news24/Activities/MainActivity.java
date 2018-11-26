package techgroup.com.news24.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techgroup.com.news24.Adapter.FragmentAdapter;
import techgroup.com.news24.Interfaces.BusScheduleApi;
import techgroup.com.news24.Interfaces.RetrofitArrayApi;
import techgroup.com.news24.Models.BusSchedule;
import techgroup.com.news24.Models.BusStop;
import techgroup.com.news24.Models.Route;
import techgroup.com.news24.Models.Student;
import techgroup.com.news24.NetworkOperations.GetAllModelRetrofitInstance;
import techgroup.com.news24.R;

public class MainActivity extends AppCompatActivity {

    public Toolbar mToolbar;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Finding our ToolBar by its ID

        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        mToolbar.setTitle(R.string.toolBar_text);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);

        // Find the view pager that will allow the user to swipe between fragments

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        // Create an adapter that knows which fragment should be shown on each page

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),
                MainActivity.this);

        // Set the adapter onto the view pager
        viewPager.setAdapter(fragmentAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tab);
        tabLayout.setupWithViewPager(viewPager);
        //studentResponseData();
        //getBusScheduleData();


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
}

