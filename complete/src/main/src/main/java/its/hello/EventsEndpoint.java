package its.hello;

import its.ws.test.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityValidationException;

import javax.annotation.Resource;

@Endpoint
public class EventsEndpoint {
	private static final String NAMESPACE_URI = "urn:test-ws-its";

	@Autowired
	private EventsRepo eventsRepo;
	@Autowired
	private CarsRepo carsRepo;
	@Autowired
	private DeviceRepo deviceRepo;

	@Resource
	ApplicationContext context;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEventsRequest")
	@ResponsePayload
	public GetEventsResponse getEvents(@RequestPayload GetEventsRequest request) {
		GetEventsResponse response = new GetEventsResponse();

		response.getEvent().addAll(eventsRepo.findEvents(request.getDateFrom(),request.getDateTo()));

		return response;
	}
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCarsRequest")
	@ResponsePayload
	public GetCarsResponse getCars(@RequestPayload GetCarsRequest request) {
		GetCarsResponse response = new GetCarsResponse();
		response.setCar(carsRepo.findCars(request.getGrz()));
		return response;
	}
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getDeviceRequest")
	@ResponsePayload
	public GetDeviceResponse getDevices(@RequestPayload GetDeviceRequest request) {
		GetDeviceResponse getDeviceResponse=new GetDeviceResponse();
		getDeviceResponse.setDevice(deviceRepo.findDevice(request.getIddevice()));
		return getDeviceResponse;
	}
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAuthenticateRequest")
	@ResponsePayload
	public GetAuthenticateResponse getAuth(@RequestPayload GetAuthenticateRequest request) {
		GetAuthenticateResponse response=new GetAuthenticateResponse();
		if(request.getLogin().equals("admin") && request.getPassword().equals("admin"))
		{
			response.setCode("1234567890");
		}
		else
		{
			throw new Wss4jSecurityValidationException("Access denied");
		}
		return response;
	}
}
