package mikhailskiy.com.newsreader.models.news;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

import org.simpleframework.xml.Element;

import mikhailskiy.com.newsreader.db.NewsReaderDatabase;


/**
 * Created by Mikhail on 28.07.16.
 */

@Table(database = NewsReaderDatabase.class)
public class GazetaNews extends BaseNews {
    @Element(name = "author")
    @Column
    private String author;

    public GazetaNews() {
        super();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
