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
import techgroup.com.news24.Interfaces.NewsApi;
import techgroup.com.news24.ModelWrapper.ModelList;
import techgroup.com.news24.Models.GeneralNews;
import techgroup.com.news24.NetworkOperations.GetAllModelRetrofitInstance;
import techgroup.com.news24.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GeneralNewsFragment extends Fragment {

    private static final String TAG = "GeneralNewsFragment";
    ViewModel viewModel;
    RecyclerView recyclerView;
    FragmentRecyclerAdapter fragmentRecyclerAdapter;
    private static final String API_KEY = "b37668ef0d1e4ac283ad4c621cc396cf";


    public GeneralNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_general_news, container, false);

        // initialise our ViewModel Object
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        recyclerView = (RecyclerView) view.findViewById(R.id.generalNewsRecyclerView);
        // Calling Our News Data Class
        getNewsData();

        /*** Create handle for the RetrofitInstance interface*/
        viewModel.getGeneralNews().observe(this, new Observer<List<GeneralNews>>() {
            @Override
            public void onChanged(@Nullable List<GeneralNews> news) {
                initialiseRecyclerView((ArrayList<GeneralNews>) news);
            }
        });
        return view;

    }
    // initRecyclerView Adapter
    public void initialiseRecyclerView( final ArrayList<GeneralNews> generalNews) {
        LinearLayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getContext());
        fragmentRecyclerAdapter = new FragmentRecyclerAdapter(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        fragmentRecyclerAdapter.setNewsList(generalNews);
        recyclerView.setAdapter(fragmentRecyclerAdapter);

        fragmentRecyclerAdapter.setOnItemClickListener(new FragmentRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("news_item", generalNews.get(position));
                getActivity().startActivity(intent);
            }
        });
    }
    public  void getNewsData(){
        NewsApi newsApi = GetAllModelRetrofitInstance.retrofitInstance().create(NewsApi.class);
        final Call<ModelList.GeneralNewsList> newsListCall = newsApi.getGeneralNewsData("abc-news",API_KEY);
        /**  Log the URL called*/
        Log.wtf("URL Called", newsListCall.request().url() + "");
        newsListCall.enqueue(new Callback<ModelList.GeneralNewsList>() {
            @Override
            public void onResponse(Call<ModelList.GeneralNewsList> call, Response<ModelList.GeneralNewsList> response) {
                if (response.body() != null){
                    viewModel.InsertGeneral(response.body().getAllGeneralNews());
                }
            }

            @Override
            public void onFailure(Call<ModelList.GeneralNewsList> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                Log.d(TAG, "App Might Crash: ");
            }
        });
    }

}
