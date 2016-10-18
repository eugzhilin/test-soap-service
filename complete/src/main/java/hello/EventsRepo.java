package hello;


import io.spring.guides.gs_producing_web_service.Event;
import org.springframework.stereotype.Component;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class EventsRepo {
	private static final List<Event> countries = new ArrayList<Event>();

	public List<Event> findEvents(XMLGregorianCalendar from, XMLGregorianCalendar to) {

		return Collections.EMPTY_LIST;
	}
}
