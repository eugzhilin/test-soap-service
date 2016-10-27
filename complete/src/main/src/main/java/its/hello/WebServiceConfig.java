package its.hello;

import header.HeaderMessagesProvider;
import header.HeaderSoap11Provider;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.WsdlDefinition;
import org.springframework.ws.wsdl.wsdl11.ProviderBasedWsdl4jDefinition;
import org.springframework.ws.wsdl.wsdl11.provider.InliningXsdSchemaTypesProvider;
import org.springframework.ws.wsdl.wsdl11.provider.SuffixBasedPortTypesProvider;
import org.springframework.ws.wsdl.wsdl11.provider.TypesProvider;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.List;
import java.util.Properties;

@EnableWs
@Configuration
@ComponentScan("its.hello")
public class WebServiceConfig extends WsConfigurerAdapter {
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/ws/*");
	}


	@Bean(name = "events")
	public WsdlDefinition defaultDefinition(XsdSchema eventsSchema) {
		ProviderBasedWsdl4jDefinition def=new ProviderBasedWsdl4jDefinition();
		HeaderSoap11Provider soap=new HeaderSoap11Provider();
		soap.setServiceName("EventsService");
		soap.setLocationUri("/ws");
		SuffixBasedPortTypesProvider portTypesProvider=new SuffixBasedPortTypesProvider();
		portTypesProvider.setPortTypeName("Events");
		def.setTargetNamespace("urn:test-ws-its");
		def.setMessagesProvider(new HeaderMessagesProvider());
		def.setTypesProvider(typesProvider(eventsSchema));
		def.setPortTypesProvider(portTypesProvider);
		def.setBindingsProvider(soap);
		def.setServicesProvider(soap);
		return def;
	}

	@Bean
	TypesProvider typesProvider(XsdSchema eventsSchema){
		final InliningXsdSchemaTypesProvider inliningXsdSchemaTypesProvider = new InliningXsdSchemaTypesProvider();
		inliningXsdSchemaTypesProvider.setSchema(eventsSchema);
		return inliningXsdSchemaTypesProvider;

	}

	@Bean
	public XsdSchema eventsSchema() {
		return new SimpleXsdSchema(new ClassPathResource("events.xsd"));
	}

	@Bean
	public ApikeyValidationCallback apiCallbackHandler(){
		ApikeyValidationCallback callbackHandler = new ApikeyValidationCallback();
		Properties users = new Properties();
		users.setProperty("admin", "secret");
		users.setProperty("APIKEY", "1234567890");
		callbackHandler.setUsers(users);
		return callbackHandler;
	}

	@Bean
	public HeaderInterceptor headerInterceptor(){
		final HeaderInterceptor interceptor= new HeaderInterceptor();
		interceptor.setSkipValidate("getAuthenticateRequest");
		interceptor.setValidationCallbackHandler(apiCallbackHandler());
		return interceptor;
	}

	@Override
	public void addInterceptors(List interceptors) {

		interceptors.add(headerInterceptor());
	}

}
