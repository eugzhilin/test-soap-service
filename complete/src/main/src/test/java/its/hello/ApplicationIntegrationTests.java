
package its.hello;
import its.ws.test.GetEventsRequest;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.time.Instant;
import java.util.Date;
import java.util.GregorianCalendar;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,classes = {its.hello.Application.class})
public class ApplicationIntegrationTests {

    private Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

    @LocalServerPort
    private int port = 0;

    @Before
    public void init() throws Exception {
        marshaller.setPackagesToScan(ClassUtils.getPackageName(GetEventsRequest.class));
        marshaller.afterPropertiesSet();
    }
    private static DatatypeFactory datatypeFactory;
    static{
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException("Init Error!", e);
        }
    }
    @Test
    public void testSendAndReceive() {
        WebServiceTemplate ws = new WebServiceTemplate(marshaller);
        GetEventsRequest request = new GetEventsRequest();
        GregorianCalendar cal=new GregorianCalendar();
        cal.setTime(Date.from(Instant.now()));
        final Instant instant = Instant.now().minusSeconds(3600);
        GregorianCalendar calFrom=new GregorianCalendar();
        calFrom.setTime(Date.from(instant));

        request.setDateFrom(datatypeFactory.newXMLGregorianCalendar(calFrom));
        request.setDateTo(datatypeFactory.newXMLGregorianCalendar(cal));
        //request.setName("Spain");
        final Object o = ws.marshalSendAndReceive("http://localhost:"
                + port + "/ws", request);
        Assertions.assertThat(o).isNotNull();
    }

}