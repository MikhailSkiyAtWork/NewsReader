package mikhailskiy.com.newsreader.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import mikhailskiy.com.newsreader.models.channels.GazetaChannel;

/**
 * Created by Mikhail on 28.07.16.
 */
@Root
public class RssInfo {
    @Element(name = "channel")
    private GazetaChannel channel_;

    @Attribute(name = "version")
    private String version_;

    public GazetaChannel getChannel() {
        return channel_;
    }
}
