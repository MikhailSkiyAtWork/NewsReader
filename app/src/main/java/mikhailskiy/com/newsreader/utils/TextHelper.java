package mikhailskiy.com.newsreader.utils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Mikhail on 30.07.16.
 */
public class TextHelper {
    private static String PREFIX = "www";
    // Include prefix and dot in order to get source name
    private static int START = PREFIX.length() + 1;

    public static String getSourceFromUrl(String url) {
        try {
            String source = new URL(url).getHost();
            if (source.contains(PREFIX)) {
                source = source.substring(START);
            }
            return source;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
