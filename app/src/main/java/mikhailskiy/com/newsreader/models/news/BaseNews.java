package mikhailskiy.com.newsreader.models.news;

import org.simpleframework.xml.Element;

import mikhailskiy.com.newsreader.models.Enclosure;

/**
 * Created by Mikhail on 28.07.16.
 */
public class BaseNews {
    // TODO delete this after Api integration
    protected String imageUrl_;
    @Element(name = "title", required = false)
    private String title_;
    @Element(name = "link", required = false)
    private String link_;
    @Element(name = "pubDate")
    private String pubDate_;
    @Element(name = "description", required = false)
    private String description_;
    @Element(name = "guid", required = false)
    private String guid_;
    @Element(name = "enclosure", required = false)
    private Enclosure enclosure_;

    public BaseNews() {
    }

    public BaseNews(String imageUrl, String title, String description) {
        this.imageUrl_ = imageUrl;
        this.title_ = title;
        this.description_ = description;
    }

    public String getImageUrl() {
        return imageUrl_;
    }

    public String getTitle() {
        return title_;
    }

    public String getDescription() {
        return description_;
    }

    public String getPubDate() {
        return pubDate_;
    }

    public Enclosure getEnclosure() {
        return enclosure_;
    }
}
