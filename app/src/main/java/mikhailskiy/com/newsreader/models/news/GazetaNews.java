package mikhailskiy.com.newsreader.models.news;

import org.simpleframework.xml.Element;

/**
 * Created by Mikhail on 28.07.16.
 */
public class GazetaNews extends BaseNews {
    @Element(name = "author")
    private String author_;

    public GazetaNews() {
        super();
    }

    public String getAuthor() {
        return author_;
    }
}
