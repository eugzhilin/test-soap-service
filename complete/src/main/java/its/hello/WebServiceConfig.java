package its.hello;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
	@Bean
	public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean(servlet, "/ws/*");
	}

	@Bean(name = "events")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema eventsSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("getEventsRequest");
		wsdl11Definition.setPortTypeName("getCarsRequest");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("urn:test-ws-its");
		wsdl11Definition.setSchema(eventsSchema);
		return wsdl11Definition;
	}



	@Bean
	public XsdSchema eventsSchema() {
		return new SimpleXsdSchema(new ClassPathResource("events.xsd"));
	}

}
