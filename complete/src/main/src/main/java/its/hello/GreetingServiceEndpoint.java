package its.hello;

import its.ws.test.GetEventsRequest;
import its.ws.test.GetEventsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.jws.WebService;

/**
 * Created by eprst on 10/26/16.
 */
@WebService(serviceName="EventsService")
public class GreetingServiceEndpoint extends SpringBeanAutowiringSupport {
    @Autowired
    private EventsRepo eventsRepo;
    public GetEventsResponse events(GetEventsRequest request) {
        GetEventsResponse response=new GetEventsResponse();
        response.getEvent().addAll(eventsRepo.findEvents(request.getDateFrom(),request.getDateTo()));
        return response;
    }
}
