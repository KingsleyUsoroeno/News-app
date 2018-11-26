package techgroup.com.news24.Interfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import techgroup.com.news24.Models.BusSchedule;

public interface BusScheduleApi {

    @GET("api/bus-schedules")
    Call<List<BusSchedule>> getAllBusSchedule();
}
