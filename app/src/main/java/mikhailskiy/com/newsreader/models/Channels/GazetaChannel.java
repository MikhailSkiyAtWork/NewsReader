package mikhailskiy.com.newsreader.models.channels;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.List;

import mikhailskiy.com.newsreader.models.news.GazetaNews;

/**
 * Created by Mikhail on 29.07.16.
 */
@Root(strict = false)
public class GazetaChannel extends BaseChannel {
    @Element(name = "pubDate")
    private String pubDate_;
    @Element(name = "copyright")
    private String copyright_;
    @Element(name = "webMaster")
    private String webMaster_;
    @Element(name = "ttl")
    private int ttl_;

    @ElementList(entry = "item", inline = true)
    private List<GazetaNews> itemList_;

    @Element(name = "owner")
    @Namespace(reference = "http://rss2lj.net/NS", prefix = "rss2lj")
    private String owner_;

    public List<GazetaNews> getNewsList() {
        return itemList_;
    }
}
