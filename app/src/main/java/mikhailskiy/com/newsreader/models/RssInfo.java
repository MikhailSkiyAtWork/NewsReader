package mikhailskiy.com.newsreader.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import mikhailskiy.com.newsreader.models.channels.BaseChannel;

/**
 * Created by Mikhail on 28.07.16.
 */
@Root
public class RssInfo {
    @Element(name = "channel")
    private BaseChannel channel_;

    @Attribute(name = "version")
    private String version_;

    public BaseChannel getChannel() {
        return channel_;
    }
}
