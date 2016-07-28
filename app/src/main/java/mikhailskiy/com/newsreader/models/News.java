package mikhailskiy.com.newsreader.models;

/**
 * Created by Mikhail on 28.07.16.
 */
public class News {
    private String imageUrl_;
    private String title_;
    private String description_;

    public News(String imageUrl, String title, String description) {
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
}
