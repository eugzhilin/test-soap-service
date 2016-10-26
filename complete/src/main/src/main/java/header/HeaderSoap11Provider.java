package header;
    import org.springframework.ws.wsdl.wsdl11.provider.Soap11Provider;

import javax.wsdl.*;
import javax.wsdl.extensions.soap.SOAPHeader;
import javax.xml.namespace.QName;
import java.util.Iterator;

    public class HeaderSoap11Provider extends Soap11Provider {

        public static final String DEFAULT_REQUEST_SUFFIX = "Request";

        public static final String DEFAULT_HEADER_SUFFIX = "Header";

        private String requestSuffix = DEFAULT_REQUEST_SUFFIX;

        private String headerSuffix = DEFAULT_HEADER_SUFFIX;

        public void setHeaderSuffix(String headerSuffix) {
            this.headerSuffix = headerSuffix;
        }

        public void setRequestSuffix(String requestSuffix) {
            this.requestSuffix = requestSuffix;
        }

        @Override
        protected void populateBindingInput(Definition definition, BindingInput bindingInput, Input input)
                throws WSDLException {
            super.populateBindingInput(definition, bindingInput, input);
            String bindingInputName = bindingInput.getName();
            if (bindingInputName.endsWith(requestSuffix)) {
                String operationName = bindingInputName.substring(0, bindingInputName.length() - requestSuffix.length());
                String headerName = operationName + headerSuffix;
                for (Iterator iterator = definition.getMessages().keySet().iterator(); iterator.hasNext(); ) {
                    QName messageName = (QName) iterator.next();
                    if (messageName.getLocalPart().equals(headerName)) {
                        SOAPHeader soapHeader = (SOAPHeader) definition.getExtensionRegistry()
                                .createExtension(BindingInput.class, new QName(SOAP_11_NAMESPACE_URI, "header"));
                        Message headerMessage = definition.getMessage(messageName);
                        populateSoapHeader(headerMessage, soapHeader);
                        bindingInput.addExtensibilityElement(soapHeader);
                    }
                }

            }

        }

        private void populateSoapHeader(Message message, SOAPHeader header) {
            header.setUse("literal");
            header.setMessage(message.getQName());
            String partName = (String) message.getParts().keySet().iterator().next();
            header.setPart(partName);

        }
    }


