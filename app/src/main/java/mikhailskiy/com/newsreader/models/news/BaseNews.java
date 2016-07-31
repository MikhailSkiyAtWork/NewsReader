package mikhailskiy.com.newsreader.models.news;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.simpleframework.xml.Element;

import mikhailskiy.com.newsreader.db.NewsReaderDatabase;
import mikhailskiy.com.newsreader.models.Enclosure;

/**
 * Created by Mikhail on 28.07.16.
 */
@Table(database = NewsReaderDatabase.class)
public class BaseNews extends BaseModel {
    @PrimaryKey
    @Column
    @Element(name = "guid", required = false)
    private String guid;

    @Column
    @Element(name = "title", required = false)
    private String title;

    @Column
    @Element(name = "link", required = false)
    private String link;

    @Column
    @Element(name = "pubDate")
    private String pubDate;

    @Column
    @Element(name = "description", required = false)
    private String description;

    @Column(typeConverter = Enclosure.EnclosureConverter.class)
    @Element(name = "enclosure", required = false)
    private Enclosure enclosure;

    public BaseNews() {
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public Enclosure getEnclosure() {
        return enclosure;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }

}
