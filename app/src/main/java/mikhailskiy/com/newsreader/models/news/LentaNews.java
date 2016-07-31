package mikhailskiy.com.newsreader.models.news;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.Table;

import org.simpleframework.xml.Element;

import mikhailskiy.com.newsreader.db.NewsReaderDatabase;

/**
 * Created by Mikhail on 28.07.16.
 */
@Table(database = NewsReaderDatabase.class)
public class LentaNews extends BaseNews {
    @Element(name = "category")
    @Column
    private String category;

    public LentaNews() {
        super();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
