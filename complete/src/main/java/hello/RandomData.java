package hello;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.*;
import java.util.Date;

/**
 * Created by eugin on 10/20/16.
 */
public class RandomData {
    private static DatatypeFactory datatypeFactory;
    static{
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException("Init Error!", e);
        }
    }
    private static  final String letters="АВЕКМНОРСТУХ";

    private static final Random random = new Random();

    private static final Map<String, List<String>> makerModel;
    static
    {
        makerModel = new HashMap<String, List<String>>();
        makerModel.put("Ford", Arrays.asList("Bronco","Corsair","Maverick","Escort","Mustang"));
        makerModel.put("Nissan",Arrays.asList("Dat Type 31","Datsun Type 13","Prairie","Murano"));
        makerModel.put("Volkswagen", Arrays.asList("Polo","Golf IV","Passat","Jetta","Toureg"));
    }
    public static String getGRZ() {
        char[] text = new char[11];
        text[0] = letters.charAt(random.nextInt(letters.length()));
        for (int i=1;i<3;i++) {
            text[i]=(char)(random.nextInt(9)+'0');
        }
        text[4] = letters.charAt(random.nextInt(letters.length()));
        text[5] = letters.charAt(random.nextInt(letters.length()));
        for (int i=6;i<9;i++) {
            text[i]=(char)(random.nextInt(9)+'0');
        }
        text[9]='R';
        text[10]='U';
        return new String(text);
    }
    public static String getCoords()
    {
        double minLat = -90.00;
        double maxLat = 90.00;
        double latitude = minLat + (double)(Math.random() * ((maxLat - minLat) + 1));
        double minLon = 0.00;
        double maxLon = 180.00;
        double longitude = minLon + (double)(Math.random() * ((maxLon - minLon) + 1));
        DecimalFormat df = new DecimalFormat("#.#####");
        return df.format(latitude) + "," + df.format(longitude);
    }
    public static String getID()
    {
        return (new UUID(random.nextInt(),random.nextInt())).toString();
    }

    public static String getMaker()
    {
        int idx=random.nextInt(3);
        return makerModel.keySet().toArray()[idx].toString();
    }
    public static  String getModel(String maker)
    {
        if(makerModel.containsKey(maker)) {
            int idx = random.nextInt(makerModel.get(maker).size());
            return makerModel.get(maker).get(idx);
        }
        else
            return "Unknown";

    }
    public static  Integer getSpeed()
    {
        return random.nextInt(200);
    }


    public static XMLGregorianCalendar getDate(XMLGregorianCalendar from, XMLGregorianCalendar to) throws DatatypeConfigurationException {
        final Duration duration = DatatypeFactory.newInstance().newDuration(to.toGregorianCalendar().getTimeInMillis() - from.toGregorianCalendar().getTimeInMillis());

        final int offset = random.nextInt(duration.getSeconds());
        final XMLGregorianCalendar clone =(XMLGregorianCalendar) from.clone();
        clone.add(DatatypeFactory.newInstance().newDuration(offset*1000));
        return clone;

    }


//        private final char[] buf;
//
//        public RandomString(int length) {
//            if (length < 1)
//                throw new IllegalArgumentException("length < 1: " + length);
//            buf = new char[length];
//        }
//
//        public String nextString() {
//            for (int idx = 0; idx < buf.length; ++idx)
//                buf[idx] = symbols[random.nextInt(symbols.length)];
//            return new String(buf);
//        }
//    }
}
