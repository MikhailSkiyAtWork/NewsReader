package mikhailskiy.com.newsreader.models.channels;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

import mikhailskiy.com.newsreader.models.news.BaseNews;

/**
 * Created by Mikhail on 01.08.16.
 */
@Root(strict = false)
public class BaseChannel {
    @Element(name = "title")
    private String title_;
    @Element(name = "description")
    private String description_;
    @Element(name = "language")
    private String language_;
    @ElementList(entry = "item", inline = true)
    private List<BaseNews> itemList_;

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

    public List<BaseNews> getNewsList() {
        return itemList_;
    }
}
