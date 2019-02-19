package techgroup.com.news24.Fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import techgroup.com.news24.Activities.ViewModel;
import techgroup.com.news24.Adapter.FragmentRecyclerAdapter;
import techgroup.com.news24.Adapter.TechNewsAdapter;
import techgroup.com.news24.Interfaces.NewsApi;
import techgroup.com.news24.ModelWrapper.ModelList;
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
    public static final String API_KEY = "b37668ef0d1e4ac283ad4c621cc396cf";
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
        recyclerView = view.findViewById(R.id.techNewsNewsRecyclerView);
        fragmentRecyclerAdapter = new FragmentRecyclerAdapter(getActivity());
        linearLayoutManager = new LinearLayoutManager(getActivity());

        // Init ViewModel
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        viewModel.getTechNewsLiveData().observe(this, new Observer<List<TechNews>>() {
            @Override
            public void onChanged(@Nullable List<TechNews> techNews) {
                TechNewsAdapter recyclerAdapter = new TechNewsAdapter(getContext());
                recyclerAdapter.setNewsList(techNews);
                recyclerView.setAdapter(recyclerAdapter);
            }
        });

        return view;
    }


}
