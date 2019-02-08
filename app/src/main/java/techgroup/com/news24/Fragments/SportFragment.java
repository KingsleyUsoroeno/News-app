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
import techgroup.com.news24.Adapter.SportFragmentAdapter;
import techgroup.com.news24.Interfaces.NewsApi;
import techgroup.com.news24.ModelWrapper.ModelList;
import techgroup.com.news24.Models.SportNews;
import techgroup.com.news24.NetworkOperations.GetAllModelRetrofitInstance;
import techgroup.com.news24.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SportFragment extends Fragment {

    private static final String TAG = "SportFragment";
    public ViewModel viewModel;
    private static final String API_KEY = "b37668ef0d1e4ac283ad4c621cc396cf";
    SportFragmentAdapter sportFragmentAdapter;
    RecyclerView recyclerView;

    public SportFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // initialise ViewModel
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_sport, container, false);

        recyclerView = view.findViewById(R.id.sportNewsRecyclerView);
        // Call our Network Method
        getSportData();

        viewModel.getSportNews().observe(this, new Observer<List<SportNews>>() {
            @Override
            public void onChanged(@Nullable List<SportNews> sportNews) {
                initialiseRecyclerView((ArrayList<SportNews>) sportNews);
            }
        });

        return view;
    }
    public void getSportData(){
        NewsApi sportApi = GetAllModelRetrofitInstance.retrofitInstance().create(NewsApi.class);
        /*** Create handle for the RetrofitInstance interface*/
        Call<ModelList.SportNewsList> sportData = sportApi.getSportData("football-italia",API_KEY);
        sportData.enqueue(new Callback<ModelList.SportNewsList>() {
            @Override
            public void onResponse(Call<ModelList.SportNewsList> call, Response<ModelList.SportNewsList> response) {
                if (response.body() != null){
                    viewModel.InsertSport(response.body().getAllSportNews());
                }
            }

            @Override
            public void onFailure(Call<ModelList.SportNewsList> call, Throwable t) {
                Log.d(TAG, "Network Error : " + t.getMessage());
            }
        });

    }
    public void initialiseRecyclerView( final ArrayList<SportNews> sportNews) {
        LinearLayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getContext());
        sportFragmentAdapter = new SportFragmentAdapter(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        sportFragmentAdapter.setNewsList(sportNews);
        recyclerView.setAdapter(sportFragmentAdapter);

        sportFragmentAdapter.setOnItemClickListener(new SportFragmentAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("news_item", sportNews.get(position));
                getActivity().startActivity(intent);
            }
        });
    }
}
