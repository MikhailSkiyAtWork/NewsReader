package mikhailskiy.com.newsreader.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by Mikhail on 29.07.16.
 */
@Root(strict = false)
public class Enclosure {
    @Attribute(name = "url")
    private String url_;

    public String getUrl() {
        return url_;
    }
}
