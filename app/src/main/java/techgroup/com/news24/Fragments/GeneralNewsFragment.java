package techgroup.com.news24.Fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import techgroup.com.news24.Activities.DetailActivity;
import techgroup.com.news24.Activities.MainActivity;
import techgroup.com.news24.Activities.ViewModel;
import techgroup.com.news24.Adapter.FragmentRecyclerAdapter;
import techgroup.com.news24.Models.GeneralNews;
import techgroup.com.news24.Models.SportNews;
import techgroup.com.news24.Models.TechNews;
import techgroup.com.news24.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GeneralNewsFragment extends Fragment {

    private static final String TAG = "GeneralNewsFragment";
    ViewModel viewModel;
    RecyclerView recyclerView;
    FragmentRecyclerAdapter fragmentRecyclerAdapter;
    public static final String API_KEY = "b37668ef0d1e4ac283ad4c621cc396cf";

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
        recyclerView = view.findViewById(R.id.generalNewsRecyclerView);

        /*** Create handle for the RetrofitInstance interface*/
        viewModel.getGeneralNews().observe(this, new Observer<List<GeneralNews>>() {
            @Override
            public void onChanged(@Nullable List<GeneralNews> news) {

            }
        });
        initialiseRecyclerView(MainActivity.generalNewsArray,MainActivity.sportNewsArray,MainActivity.techNewsArray);
        return view;

    }
    // initRecyclerView Adapter
    public void initialiseRecyclerView(final List<GeneralNews> generalNews, final List<SportNews> sportNews, final List<TechNews> techNews) {
        LinearLayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getContext());
        fragmentRecyclerAdapter = new FragmentRecyclerAdapter(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        fragmentRecyclerAdapter.setNewsList(generalNews,sportNews,techNews);
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
}
