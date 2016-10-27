package its.hello;

import org.apache.wss4j.common.ext.WSSecurityException;
import org.apache.wss4j.common.util.XMLUtils;
import org.apache.wss4j.dom.SOAP11Constants;
import org.apache.wss4j.dom.SOAP12Constants;
import org.apache.wss4j.dom.SOAPConstants;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.security.AbstractWsSecurityInterceptor;
import org.springframework.ws.soap.security.WsSecuritySecurementException;
import org.springframework.ws.soap.security.WsSecurityValidationException;
import org.springframework.ws.soap.security.callback.CallbackHandlerChain;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityValidationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by eprst on 10/26/16.
 */
public class HeaderInterceptor  extends AbstractWsSecurityInterceptor implements InitializingBean {

    private CallbackHandler validationCallbackHandler;

    public List<String> getSkipValidate() {
        return skipValidate;
    }

    public void setSkipValidate(String... skipValidate) {
        this.skipValidate = Arrays.asList(skipValidate);
    }

    private List<String> skipValidate;



    public static String getSOAPNamespace(Element startElement) {
        return getSOAPConstants(startElement).getEnvelopeURI();
    }

    public static Element getSOAPHeader(Document doc) {
        String soapNamespace = getSOAPNamespace(doc.getDocumentElement());
        return XMLUtils.getDirectChildElement(doc.getDocumentElement(), "Header", soapNamespace);
    }

    public static SOAPConstants getSOAPConstants(Element startElement) {
        Document doc = startElement.getOwnerDocument();
        String ns = doc.getDocumentElement().getNamespaceURI();
        return ("http://www.w3.org/2003/05/soap-envelope".equals(ns) ? new SOAP12Constants() : new SOAP11Constants());
    }


    public void setValidationCallbackHandler(CallbackHandler callbackHandler) {
        this.validationCallbackHandler = callbackHandler;
    }

    public void setValidationCallbackHandlers(CallbackHandler[] callbackHandler) {
        this.validationCallbackHandler = new CallbackHandlerChain(callbackHandler);
    }

    public static Element getSecurityHeader(Element soapHeader, boolean soap12) throws WSSecurityException {
        Element foundSecurityHeader = null;
        for (Node currentChild = soapHeader.getFirstChild(); currentChild != null; currentChild = currentChild.getNextSibling()) {
            if(currentChild.getFirstChild().getNextSibling().getLocalName().equals("APIKEY"))
            {
                foundSecurityHeader=(Element) currentChild.getFirstChild().getNextSibling();
                return foundSecurityHeader;
            }
        }

        return foundSecurityHeader;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    private boolean skipCheck(Document doc,String soapNamespace)
    {
        try
        {
            final String target=XMLUtils.getDirectChildElement(doc.getDocumentElement(), "Body", soapNamespace).getFirstChild().getNextSibling().getLocalName();
            return this.skipValidate.contains(target);
        }
        catch (Throwable ex)
        {
            return false;
        }
    }

    @Override
    protected void validateMessage(SoapMessage soapMessage, org.springframework.ws.context.MessageContext messageContext) throws WsSecurityValidationException {

        final Document doc = soapMessage.getDocument();
        String soapNamespace = getSOAPNamespace(doc.getDocumentElement());
        if(!this.skipCheck(doc,soapNamespace)) {
            Element header = XMLUtils.getDirectChildElement(doc.getDocumentElement(), "Header", soapNamespace);

            try {
                final Element securityHeader = getSecurityHeader(header, "http://www.w3.org/2003/05/soap-envelope".equals(soapNamespace));
                if (securityHeader == null || securityHeader.getFirstChild() == null) {
                    throw new Wss4jSecurityValidationException("Access denied");
                }
                ApikeyCallback cb = new ApikeyCallback();
                cb.setTestKey(securityHeader.getFirstChild().getNodeValue());
                validationCallbackHandler.handle(new Callback[]{cb});
                if (!cb.getOk()) {
                    throw new Wss4jSecurityValidationException("Access denied");
                }

            } catch (WSSecurityException var8) {
                throw new Wss4jSecurityValidationException(var8.getMessage(), var8);
            } catch (UnsupportedCallbackException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void secureMessage(SoapMessage soapMessage, org.springframework.ws.context.MessageContext messageContext) throws WsSecuritySecurementException {

    }

    @Override
    protected void cleanUp() {

    }


}