package mikhailskiy.com.newsreader.adapters;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import mikhailskiy.com.newsreader.R;
import mikhailskiy.com.newsreader.models.News;

/**
 * Created by Mikhail on 28.07.16.
 */
public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder> {

    private List<News> allNews_ = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        News currenItem = allNews_.get(position);
        holder.newsTitleTextView.setText(currenItem.getTitle());
        holder.newsDescriptionTextView.setText(currenItem.getDescription());

        String imageUrl = currenItem.getImageUrl();
        Picasso.with(holder.newsImageView.getContext()).load(imageUrl).into(holder.newsImageView);
        if (!"".equals(imageUrl)) {
            Picasso.with(holder.newsImageView.getContext()).load(imageUrl).placeholder(R.drawable.ic_photo).into(holder.newsImageView);
        } else {
            holder.newsImageView.setImageDrawable(ContextCompat.getDrawable(holder.newsImageView.getContext(), R.drawable.ic_photo));
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.news_image_view)
        ImageView newsImageView;

        @Bind(R.id.news_title_text_view)
        TextView newsTitleTextView;

        @Bind(R.id.news_description_text_view)
        TextView newsDescriptionTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Add click listener here in order to show description
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return allNews_.size();
    }

    public void addNews(@NonNull List<News> freshNews) {
        int oldSize = allNews_.size();
        allNews_.addAll(freshNews);
        notifyItemRangeInserted(oldSize, freshNews.size());
    }
}