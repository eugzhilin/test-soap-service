package header;

import org.springframework.ws.wsdl.wsdl11.provider.SuffixBasedMessagesProvider;
import org.w3c.dom.Element;

public class HeaderMessagesProvider extends SuffixBasedMessagesProvider {

    /** The default suffix used to detect response elements in the schema. */
    public static final String DEFAULT_HEADER_SUFFIX = "Header";

    private String headerSuffix = DEFAULT_HEADER_SUFFIX;

    public void setHeaderSuffix(String headerSuffix) {
        this.headerSuffix = headerSuffix;
    }

    @Override
    protected boolean isMessageElement(Element element) {
        if (super.isMessageElement(element)) {
            return true;
        }
        else {
            String elementName = getElementName(element);
            return elementName.endsWith(headerSuffix);
        }
    }
}