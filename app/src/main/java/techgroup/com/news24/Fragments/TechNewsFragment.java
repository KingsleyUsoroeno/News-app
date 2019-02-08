package techgroup.com.news24.Fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techgroup.com.news24.Activities.DetailActivity;
import techgroup.com.news24.Activities.ViewModel;
import techgroup.com.news24.Adapter.FragmentRecyclerAdapter;
import techgroup.com.news24.Adapter.SportRecyclerAdapter;
import techgroup.com.news24.Interfaces.NewsApi;
import techgroup.com.news24.ModelWrapper.ModelList;
import techgroup.com.news24.Models.News;
import techgroup.com.news24.Models.TechNews;
import techgroup.com.news24.NetworkOperations.GetAllModelRetrofitInstance;
import techgroup.com.news24.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TechNewsFragment extends Fragment {
    RecyclerView recyclerView;
    FragmentRecyclerAdapter fragmentRecyclerAdapter;
    LinearLayoutManager linearLayoutManager;
    ViewModel viewModel;
    private static final String API_KEY = "8f8eb83da62f43db94b5db27b4dc5312";
    private static final String TAG = "TechNewsFragment";


    public TechNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tech_news, container , false);

        // Init Our RecyclerView
        recyclerView = (RecyclerView) view.findViewById(R.id.techNewsNewsRecyclerView);
        fragmentRecyclerAdapter = new FragmentRecyclerAdapter(getActivity());
        linearLayoutManager = new LinearLayoutManager(getActivity());
        getNetworkData();

        // Init ViewModel
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        viewModel.getTechNewsLiveData().observe(this, new Observer<List<TechNews>>() {
            @Override
            public void onChanged(@Nullable List<TechNews> techNews) {
                SportRecyclerAdapter recyclerAdapter = new SportRecyclerAdapter(getContext());
                recyclerAdapter.setNewsList(techNews);
                recyclerView.setAdapter(recyclerAdapter);
            }
        });

        return view;
    }

    private void getNetworkData(){
        NewsApi api = GetAllModelRetrofitInstance.retrofitInstance()
                .create(NewsApi.class);
        Call<ModelList.TechNewsList> call = api.getTechNewsData("techcrunch",API_KEY);

        call.enqueue(new Callback<ModelList.TechNewsList>() {
            @Override
            public void onResponse(Call<ModelList.TechNewsList> call, Response<ModelList.TechNewsList> response) {
               if (response.body() != null){
                   viewModel.InsertTech(response.body().getAllTechNews());
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
