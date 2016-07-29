package mikhailskiy.com.newsreader.models;

import org.simpleframework.xml.Element;

/**
 * Created by Mikhail on 29.07.16.
 */
@Element(name = "image")
public class Image {
    @Element(name = "url")
    private String url_;
    @Element(name = "title")
    private String title_;
    @Element(name = "link")
    private String link_;
    @Element(name = "width")
    private int width_;
    @Element(name = "height")
    private int height_;

    public Image() {
    }
}
