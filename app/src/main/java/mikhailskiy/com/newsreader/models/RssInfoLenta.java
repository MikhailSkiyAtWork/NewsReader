package mikhailskiy.com.newsreader.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import mikhailskiy.com.newsreader.models.channels.LentaChannel;

/**
 * Created by Mikhail on 29.07.16.
 */
@Root
public class RssInfoLenta {
    @Element(name = "channel")
    private LentaChannel channel_;

    @Attribute(name = "version")
    private String version_;

    public LentaChannel getChannel() {
        return channel_;
    }

}
