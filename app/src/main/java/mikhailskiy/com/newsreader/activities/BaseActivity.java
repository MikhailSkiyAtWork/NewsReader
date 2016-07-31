package mikhailskiy.com.newsreader.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

import mikhailskiy.com.newsreader.R;

/**
 * Created by Mikhail on 31.07.16.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private ProgressDialog progressDialog_;
    protected boolean isRequestInProgress_;

    public void showProgressBar() {
        if (progressDialog_ == null) {
            progressDialog_ = new ProgressDialog(this, R.style.TransparentProgressBar);
            progressDialog_.setCancelable(true);
            progressDialog_.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        }
        progressDialog_.show();
    }

    public void hideProgressBar() {
        if ((progressDialog_ != null) && (progressDialog_.isShowing())) {
            progressDialog_.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isRequestInProgress_) {
            hideProgressBar();
        }
    }
}
