package mikhailskiy.com.newsreader.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Mikhail on 29.07.16.
 */
public class DateHelper {
    private final static SimpleDateFormat serverDateFormat_ = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss zzz", Locale.US);
    private final static SimpleDateFormat appDateFormat = new SimpleDateFormat("d MMM HH:mm:ss", Locale.getDefault());

    private static volatile DateHelper instance_;

    private DateHelper() {
        serverDateFormat_.setTimeZone(TimeZone.getTimeZone("UTC"));
        TimeZone defaultTimeZone = TimeZone.getDefault();
        appDateFormat.setTimeZone(defaultTimeZone);
    }

    public static DateHelper getInstance() {
        if (instance_ == null) {
            synchronized (DateHelper.class) {
                if (instance_ == null) {
                    instance_ = new DateHelper();
                }
            }
        }
        return instance_;
    }

    public SimpleDateFormat getServerDateFormat() {
        return serverDateFormat_;
    }

    public static long getMillisFromServerTime(String time) {
        Date date = null;
        long millis = 0;
        try {
            date = DateHelper.getInstance().getServerDateFormat().parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) {
            millis = date.getTime();
        }
        return millis;
    }

    public static String getReadableDate(String time) {
        long postTimeMillis = getMillisFromServerTime(time);
        return appDateFormat.format(postTimeMillis);
    }
}

