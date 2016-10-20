package hello;



import its.ws.test.Event;
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

		List<Event> lst=new ArrayList<Event>();
		for(int i=0;i<10;i++) {
			Event ev = new Event();
			ev.setCoords(RandomData.getCoords());
			ev.setGrz(RandomData.getGRZ());
			ev.setIddevice(RandomData.getID());
			ev.setMaker(RandomData.getMaker());
			ev.setModel(RandomData.getModel(ev.getMaker()));
			ev.setSpeed(RandomData.getSpeed().toString());
			ev.setIdevent(RandomData.getID());
			ev.setMedia("");
			ev.setTsType("1");
			try {
				ev.setDateEvent(RandomData.getDate(from, to));
			} catch (Throwable ex) {

			}
			lst.add(ev);
		}

		return lst;
	}
}
