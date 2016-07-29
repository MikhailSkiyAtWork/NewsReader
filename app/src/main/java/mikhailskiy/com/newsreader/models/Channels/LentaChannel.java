package mikhailskiy.com.newsreader.models.channels;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

import mikhailskiy.com.newsreader.models.Image;
import mikhailskiy.com.newsreader.models.news.LentaNews;

/**
 * Created by Mikhail on 29.07.16.
 */
@Root(strict = false)
public class LentaChannel extends BaseChannel {

    @Element(name = "image")
    private Image image_;

    @ElementList(entry = "item", inline = true)
    private List<LentaNews> lentaNewsList_;

    public List<LentaNews> getLentaNewsList() {
        return lentaNewsList_;
    }
}

