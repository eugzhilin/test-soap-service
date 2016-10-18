package hello;

import io.spring.guides.gs_producing_web_service.GetEventsRequest;
import io.spring.guides.gs_producing_web_service.GetEventsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class CountryEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

	@Autowired
	private EventsRepo eventsRepo;



	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public GetEventsResponse getEvents(@RequestPayload GetEventsRequest request) {
		GetEventsResponse response = new GetEventsResponse();
		response.getEvent().addAll(eventsRepo.findEvents(request.getDateFrom(),request.getDateTo()));

		return response;
	}
}
