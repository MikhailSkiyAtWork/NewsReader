package mikhailskiy.com.newsreader;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import mikhailskiy.com.newsreader.activities.MainActivity;

public class ApplicationTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private static final String MAIN_ACTIVITY = "MainActivity";
    private Solo solo;

    public ApplicationTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

    public void testMainActivity() throws Exception {
        solo.unlockScreen();
        solo.assertCurrentActivity("Expected MainActivity activity", MAIN_ACTIVITY);
    }
}
