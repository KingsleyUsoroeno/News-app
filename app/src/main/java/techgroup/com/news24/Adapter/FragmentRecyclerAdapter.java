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
import techgroup.com.news24.R;

public class FragmentRecyclerAdapter extends RecyclerView.Adapter<FragmentRecyclerAdapter.GeneralNewsViewHolder> {

    private Context context;
    private List<GeneralNews> newsArrayList;
    private OnItemClickListener mListener;

    public FragmentRecyclerAdapter(Context context) {
        this.context = context;
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }
    public interface OnItemClickListener{
        void onItemClicked(int position);
    }

    @NonNull
    @Override
    public GeneralNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_recycler_layout,parent,false);
        return new GeneralNewsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull GeneralNewsViewHolder holder, int position) {

        News newsModel = newsArrayList.get(holder.getAdapterPosition());

        Glide.with(context)
                .asBitmap()
                .load(newsModel.getUrlToImage())
                .into(holder.circleImageView);

         holder.textView_title.setText(newsModel.getTitle());

    }

    @Override
    public int getItemCount() {
        if (newsArrayList == null) {
            return 0;
        } else {
            return newsArrayList.size();
        }

    }

    public void setNewsList(List<GeneralNews> news) {
        this.newsArrayList = news;
        notifyDataSetChanged();
    }

    public class GeneralNewsViewHolder extends RecyclerView.ViewHolder {

        CircleImageView circleImageView;
        TextView textView_title;


        public GeneralNewsViewHolder(View itemView) {
            super(itemView);

            circleImageView = (CircleImageView) itemView.findViewById(R.id.ImageView);
           // circleImageView.setScaleType(ImageView.ScaleType.FIT_XY);
             textView_title = (TextView) itemView.findViewById(R.id.News_Title);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClicked(position);
                        }
                    }

                }
            });
        }
    }
}
