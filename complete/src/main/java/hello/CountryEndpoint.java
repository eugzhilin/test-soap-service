package hello;


import its.ws.test.GetEventsRequest;
import its.ws.test.GetEventsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class CountryEndpoint {
	private static final String NAMESPACE_URI = "urn:test-ws-its";

	@Autowired
	private EventsRepo eventsRepo;



	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEventsRequest")
	@ResponsePayload
	public GetEventsResponse getEvents(@RequestPayload GetEventsRequest request) {
		GetEventsResponse response = new GetEventsResponse();
		response.getEvent().addAll(eventsRepo.findEvents(request.getDateFrom(),request.getDateTo()));

		return response;
	}
}
