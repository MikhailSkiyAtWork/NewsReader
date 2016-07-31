package mikhailskiy.com.newsreader.models;

import com.raizlabs.android.dbflow.converter.TypeConverter;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by Mikhail on 29.07.16.
 */
@Root(strict = false)
public class Enclosure {
    @Attribute(name = "url")
    private String url_;

    public String getUrl() {
        return url_;
    }

    public Enclosure() {
    }

    public Enclosure(String url) {
        this.url_ = url;
    }

    public static class EnclosureConverter extends TypeConverter<String, Enclosure> {

        @Override
        public String getDBValue(Enclosure model) {
            return model == null ? null : model.getUrl();
        }

        @Override
        public Enclosure getModelValue(String data) {
            return new Enclosure(data);
        }
    }
}
