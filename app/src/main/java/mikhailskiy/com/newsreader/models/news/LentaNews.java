package mikhailskiy.com.newsreader.models.news;

import org.simpleframework.xml.Element;

import mikhailskiy.com.newsreader.models.Enclosure;

/**
 * Created by Mikhail on 28.07.16.
 */
public class LentaNews extends BaseNews {
    @Element(name = "enclosure", required = false)
    private Enclosure enclosure_;
    @Element(name = "category")
    private String category_;

    public LentaNews() {
        super();
    }

    public Enclosure getEnclosure() {
        return enclosure_;
    }

    public String getCategory() {
        return category_;
    }
}
