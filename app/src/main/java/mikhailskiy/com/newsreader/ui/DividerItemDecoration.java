package mikhailskiy.com.newsreader.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Mikhail on 28.07.16.
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    private final Drawable dividerDrawable_;
    private boolean ignoreParentPaddings_ = false;

    /**
     * Constructor with custom dividers
     */
    public DividerItemDecoration(Drawable dividerDrawable) {
        this.dividerDrawable_ = dividerDrawable;
    }

    /**
     * Constructor with default dividers
     */
    public DividerItemDecoration(Context context) {
        TypedArray typedArray = context.obtainStyledAttributes(ATTRS);
        dividerDrawable_ = typedArray.getDrawable(0);
        typedArray.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = ignoreParentPaddings_ ? 0 : parent.getPaddingLeft();
        int right = ignoreParentPaddings_ ? parent.getWidth() : parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + layoutParams.bottomMargin;
            int bottom = top + dividerDrawable_.getIntrinsicHeight();

            dividerDrawable_.setBounds(left, top, right, bottom);
            dividerDrawable_.draw(c);
        }
    }


    /**
     * Sets whether or not we should ignore parent's left and right padding. Default is false
     */
    public DividerItemDecoration setIgnoreParentPaddings(boolean ignoreParentPaddings) {
        this.ignoreParentPaddings_ = ignoreParentPaddings;
        return this;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) == 0) {
            return;
        }
        outRect.top = dividerDrawable_.getIntrinsicHeight();
    }
}