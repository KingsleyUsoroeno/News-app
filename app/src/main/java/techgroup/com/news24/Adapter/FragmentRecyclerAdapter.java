package techgroup.com.news24.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import techgroup.com.news24.Models.GeneralNews;
import techgroup.com.news24.Models.News;
import techgroup.com.news24.Models.SportNews;
import techgroup.com.news24.Models.TechNews;
import techgroup.com.news24.R;

public class FragmentRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    // Our DataArrays
    private List<GeneralNews> generalNewsList;
    private List<SportNews> sportNewsArray;
    private List<TechNews> techNewsArray;

    private OnItemClickListener mListener;
    private static final int GENERAL_NEWS = 0;
    private static final int SPORT_NEWS = 1;
    private static final int TECH_NEWS = 2;

    public FragmentRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClicked(int position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_recycler_layout, parent, false);
        if (viewType == GENERAL_NEWS) {
            return new GeneralNewsViewHolder(view);
        }
        if (viewType == SPORT_NEWS) {
            return new SportNewsViewHolder(view);
        }
        if (viewType == TECH_NEWS) {
            return new TechNewsViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GeneralNewsViewHolder) {
            GeneralNews generalNews = generalNewsList.get(position);
            ((GeneralNewsViewHolder) holder).textView_title.setText(generalNews.getTitle());
            Glide.with(context)
                    .asBitmap()
                    .load(generalNews.getUrlToImage())
                    .into(((GeneralNewsViewHolder) holder).circleImageView);
        }
        if (holder instanceof SportNewsViewHolder){
            SportNews sportNews = sportNewsArray.get(position);
            ((SportNewsViewHolder) holder).textView_title.setText(sportNews.getTitle());
            Glide.with(context)
                    .asBitmap()
                    .load(sportNews.getUrlToImage())
                    .into(((SportNewsViewHolder) holder).circleImageView);
        }

        if (holder instanceof TechNewsViewHolder){
            TechNews techNews = techNewsArray.get(position);
            ((TechNewsViewHolder) holder).textView_title.setText(techNews.getTitle());
            Glide.with(context)
                    .asBitmap()
                    .load(techNews.getUrlToImage())
                    .into(((TechNewsViewHolder) holder).circleImageView);

        }
    }

    @Override
    public int getItemCount() {
        if (generalNewsList == null || sportNewsArray == null || techNewsArray == null) {
            return 0;
        } else {
            return generalNewsList.size() + sportNewsArray.size() + techNewsArray.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position < generalNewsList.size()){
            return GENERAL_NEWS;
        }
        if (position - generalNewsList.size() < sportNewsArray.size()){
            return SPORT_NEWS;
        }
        return -1;
    }

    public void setNewsList(List<GeneralNews> news, List<SportNews> sportNewsArray, List<TechNews> techNews) {
        this.generalNewsList = news;
        this.sportNewsArray = sportNewsArray;
        this.techNewsArray = techNews;
        notifyDataSetChanged();
    }

    public class GeneralNewsViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView textView_title;


        public GeneralNewsViewHolder(View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.ImageView);
            textView_title = itemView.findViewById(R.id.News_Title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClicked(position);
                        }
                    }

                }
            });
        }
    }

    public class SportNewsViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView textView_title;

        public SportNewsViewHolder(View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.ImageView);
            textView_title = itemView.findViewById(R.id.News_Title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClicked(position);
                        }
                    }

                }
            });
        }
    }

    public class TechNewsViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView textView_title;

        public TechNewsViewHolder(View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.ImageView);
            textView_title = itemView.findViewById(R.id.News_Title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClicked(position);
                        }
                    }

                }
            });
        }
    }


}
