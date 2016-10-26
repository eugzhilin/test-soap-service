package its.hello;



import its.ws.test.Device;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class DeviceRepo {
	public Device findDevice(BigInteger id) {
		Device device=new Device();
		device.setCertNo(RandomData.GetRandomString(15));
		device.setCoords(RandomData.getCoords());
		device.setDirection(RandomData.getDirection());
		device.setIddevice(id.toString());
		device.setIdRoad(RandomData.getID());
		device.setOkato(RandomData.GetRandomNumString(11));
		device.setInstallDate(RandomData.GetRandomDateBefore());
		device.setProducer(RandomData.GetRandomString(7));
		device.setValidTill(RandomData.GetRandomDateAfter());
		return device;
	}
}
