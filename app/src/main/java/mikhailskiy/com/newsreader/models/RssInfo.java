package mikhailskiy.com.newsreader.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Mikhail on 28.07.16.
 */
@Root
public class RssInfo {
    @Element(name="channel")
    private Channel channel_;

    @Attribute(name = "version")
    private String version_;

    public Channel getChannel() {
        return channel_;
    }
}
