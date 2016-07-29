package mikhailskiy.com.newsreader.models.channels;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Mikhail on 29.07.16.
 */
@Root(strict = false)
public class BaseChannel {
    @Element(name = "title")
    private String title_;
    @Element(name = "description")
    private String description_;
    @Element(name = "language")
    private String language_;

    public String getTitle() {
        return title_;
    }

    public String getDescription() {
        return description_;
    }

    public String getLanguage() {
        return language_;
    }

    public void setTitle(String title) {
        this.title_ = title;
    }

    public void setDescription(String description) {
        this.description_ = description;
    }

    public void setLanguage(String language) {
        this.language_ = language;
    }
}
