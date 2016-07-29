package mikhailskiy.com.newsreader.models.news;

import org.simpleframework.xml.Element;

/**
 * Created by Mikhail on 28.07.16.
 */
public class LentaNews extends BaseNews {
    @Element(name = "category")
    private String category_;

    public LentaNews() {
        super();
    }


    public String getCategory() {
        return category_;
    }
}
