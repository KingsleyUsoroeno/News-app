package techgroup.com.news24.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import techgroup.com.news24.Adapter.FragmentRecyclerAdapter;
import techgroup.com.news24.Models.News;
import techgroup.com.news24.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TechNewsFragment extends Fragment {
    RecyclerView recyclerView;
    FragmentRecyclerAdapter fragmentRecyclerAdapter;
    LinearLayoutManager linearLayoutManager;
    ArrayList<News> techNews;
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

        techNews = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.techNewsNewsRecyclerView);
        fragmentRecyclerAdapter = new FragmentRecyclerAdapter(getActivity());
        linearLayoutManager = new LinearLayoutManager(getActivity());

//        TechNewsJsonResponseInterface jsonResponseInterface = GetAllModelRetrofitInstance.techNewsJsonResponse()
//                .create(TechNewsJsonResponseInterface.class);
//
//        Call<TechNewsList> call = jsonResponseInterface.getTechNewsData("techcrunch",API_KEY);
//
//        call.enqueue(new Callback<TechNewsList>() {
//            @Override
//            public void onResponse(Call<TechNewsList> call, Response<TechNewsList> response) {
//                techNews = response.body().getAllTechNews();
//                recyclerView.setHasFixedSize(true);
//                recyclerView.setAdapter(fragmentRecyclerAdapter);
//                recyclerView.setLayoutManager(linearLayoutManager);
//                //fragmentRecyclerAdapter.setNewsList(techNews);
//
//                fragmentRecyclerAdapter.setOnItemClickListener(new FragmentRecyclerAdapter.OnItemClickListener() {
//                    @Override
//                    public void onItemClicked(int position) {
//                        Intent intent = new Intent(getActivity(), DetailActivity.class);
//                        intent.putExtra("news_item", techNews.get(position));
//                        startActivity(intent);
//                    }
//                });
//
//            }
//
//            @Override
//            public void onFailure(Call<TechNewsList> call, Throwable t) {
//                Log.d(TAG, "onFailure: " +  t.getMessage());
//                Log.d(TAG, "App Crashes: ");
//            }
//        });

        return view;
    }

}
