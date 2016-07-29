package mikhailskiy.com.newsreader.models;

import org.simpleframework.xml.Element;

/**
 * Created by Mikhail on 28.07.16.
 */
public class LentaNews extends BaseNews {
    @Element(name = "category")
    private String category_;
    @Element(name = "enclosure")
    private String enclosure_;

    public LentaNews(String category_, String enclosure_) {
        this.category_ = category_;
        this.enclosure_ = enclosure_;
    }

    public String getCategory_() {
        return category_;
    }

    public String getEnclosure_() {
        return enclosure_;
    }
}
