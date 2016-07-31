package mikhailskiy.com.newsreader.adapters;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import mikhailskiy.com.newsreader.R;
import mikhailskiy.com.newsreader.models.news.BaseNews;
import mikhailskiy.com.newsreader.utils.DateHelper;
import mikhailskiy.com.newsreader.utils.TextHelper;

/**
 * Created by Mikhail on 28.07.16.
 */
public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.ViewHolder> {

    private List<BaseNews> allBaseNews_ = new ArrayList<>();
    private List<BaseNews> gazetaNews_ = new ArrayList<>();
    private List<BaseNews> lentaNews = new ArrayList<>();
    private int originalHeight_ = 0;
    private int detailsTextHeight = 0;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Context context = holder.newsImageView.getContext();
        BaseNews currentItem = allBaseNews_.get(position);

        holder.newsTitleTextView.setText(currentItem.getTitle());
        holder.newsDescriptionTextView.setText(currentItem.getDescription().replace("\n", "").replaceFirst("^\\s*", ""));

        holder.dateTextView.setText(DateHelper.getReadableDate(currentItem.getPubDate()));
        holder.sourceTextView.setText(TextHelper.getSourceFromUrl(currentItem.getGuid()));

        String imageUrl = getImageUrl(currentItem);
        if (!"".equals(imageUrl)) {
            Picasso.with(context).load(imageUrl).placeholder(R.drawable.ic_photo).into(holder.newsImageView);
        } else {
            holder.newsImageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_photo));
        }
    }

    private void onNewsClicked(final View v) {
        ViewHolder viewHolder = (ViewHolder) v.getTag();
        Context context = viewHolder.newsDescriptionTextView.getContext();

        if (originalHeight_ == 0) {
            originalHeight_ = v.getMeasuredHeight();
        }

        detailsTextHeight = getTextViewHeight(viewHolder.newsDescriptionTextView);
        ValueAnimator valueAnimator;
        Animation animation;
        if (v.getHeight() < (originalHeight_ + detailsTextHeight)) {
            valueAnimator = ValueAnimator.ofInt(originalHeight_, originalHeight_ + detailsTextHeight);
            animation = AnimationUtils.loadAnimation(context, R.anim.fade_in_animation);
            viewHolder.newsDescriptionTextView.setVisibility(View.VISIBLE);
        } else {
            valueAnimator = ValueAnimator.ofInt(originalHeight_ + detailsTextHeight, originalHeight_);
            animation = AnimationUtils.loadAnimation(context, R.anim.fade_out_animation);
            viewHolder.newsDescriptionTextView.setVisibility(View.GONE);
        }
        valueAnimator.setDuration(context.getResources().getInteger(R.integer.animation_duration));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                v.getLayoutParams().height = value.intValue();
                v.requestLayout();
            }
        });
        valueAnimator.start();
        viewHolder.newsDescriptionTextView.startAnimation(animation);

        if (viewHolder.newsDescriptionTextView.getVisibility() == View.VISIBLE) {
            viewHolder.setIsRecyclable(false);
        } else {
            viewHolder.setIsRecyclable(true);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.content_layout)
        RelativeLayout contentLayout;

        @Bind(R.id.news_image_view)
        ImageView newsImageView;

        @Bind(R.id.date_text_view)
        TextView dateTextView;

        @Bind(R.id.source_text_view)
        TextView sourceTextView;

        @Bind(R.id.news_title_text_view)
        TextView newsTitleTextView;

        @Bind(R.id.news_description_text_view)
        TextView newsDescriptionTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            contentLayout.setTag(this);
            contentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onNewsClicked(v);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return allBaseNews_.size();
    }

    public void addNews(@NonNull List<BaseNews> freshNews) {
        clearNews();
        allBaseNews_.addAll(freshNews);
        Collections.sort(allBaseNews_, BaseNews.COMPARATOR_BY_DATE_DESC);
        notifyDataSetChanged();
    }

    public void clearNews() {
        int oldCount = allBaseNews_.size();
        allBaseNews_.clear();
        if (oldCount > 0) {
            notifyItemRangeRemoved(0, oldCount);
        }
    }

    private String getImageUrl(BaseNews baseNewsItem) {
        String imageUrl = "";
        if (baseNewsItem.getEnclosure() != null) {
            imageUrl = baseNewsItem.getEnclosure().getUrl();
        }

        return imageUrl;
    }

    /**
     * Returns measured TextView height in order to show news description text
     */
    private int getTextViewHeight(TextView textView) {
        WindowManager windowManager = (WindowManager) textView.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        int deviceWidth;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point size = new Point();
            display.getSize(size);
            deviceWidth = size.x;
        } else {
            deviceWidth = display.getWidth();
        }

        int margins = (int) textView.getContext().getResources().getDimension(R.dimen.activity_horizontal_margin);
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(deviceWidth - 2 * margins, View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        textView.measure(widthMeasureSpec, heightMeasureSpec);
        return textView.getMeasuredHeight();
    }
}
