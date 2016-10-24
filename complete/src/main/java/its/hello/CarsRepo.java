package its.hello;



import its.ws.test.Car;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class CarsRepo {
	public Car findCars(String gnz) {
		final Car car=new Car();
		car.setGrz(gnz);
		car.setId(RandomData.GetLongID());
		car.setIdCategory(BigInteger.valueOf(RandomData.GetLongID()));
		final String maker = RandomData.getMaker();
		car.setMark(maker);
		car.setModel(RandomData.getModel(maker));
		car.setDateAdd(RandomData.GetRandomDateBefore());
		return car;
	}
}
