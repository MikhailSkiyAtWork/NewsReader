package mikhailskiy.com.newsreader.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;

import java.util.List;

/**
 * Created by Mikhail on 28.07.16.
 */
@Element(name = "channel")
public class Channel {
    @Element(name = "title")
    private String title_;
    @Element(name = "link")
    private String link_;
    @Element(name = "description")
    private String description_;
    @Element(name = "pubDate")
    private String pubDate_;
    @Element(name = "language")
    private String language_;
    @Element(name = "copyright")
    private String copyright_;
    @Element(name = "webMaster")
    private String webMaster_;
    @Element(name = "ttl")
    private int ttl_;

    @ElementList(entry = "item",inline = true)
    private List<BaseNews> itemList_;

    @Element(name = "owner")
    @Namespace(reference="http://rss2lj.net/NS",prefix="rss2lj")
    private String owner_;

    public Channel() {}

    public Channel(List<BaseNews> newsList_) {
        this.itemList_ = newsList_;
    }

    public List<BaseNews> getNewsList() {
        return itemList_;
    }
}
