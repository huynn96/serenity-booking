package booking.abilities;

import net.serenitybdd.screenplay.Ability;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class MakeABooking implements Ability {
    private String destination;
    private Date startTrip;
    private Date endTrip;
    private int adult;
    private int children;
    private int rooms;

    public MakeABooking(String destination, Date startTrip, Date endTrip, int adult, int children, int rooms) {
        this.destination = destination;
        this.startTrip = startTrip;
        this.endTrip = endTrip;
        this.adult = adult;
        this.children = children;
        this.rooms = rooms;
    }

    public static MakeABooking LoadTestData(String actorName) {
        try {
            Reader dataReader = new InputStreamReader(new FileInputStream("src/test/resources/TestData/user." + actorName.toLowerCase() + ".properties"), StandardCharsets.UTF_8);
            Properties dataProperties = new Properties();
            dataProperties.load(dataReader);
            Date now = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(now);
            c.add(Calendar.DATE, 7);
            Date start = c.getTime();
            c.add(Calendar.DATE, 3);
            Date end = c.getTime();
            return new MakeABooking(
                    dataProperties.getProperty("destination", ""),
                    start,
                    end,
                    Integer.parseInt(dataProperties.getProperty("adult", "0")),
                    Integer.parseInt(dataProperties.getProperty("children", "0")),
                    Integer.parseInt(dataProperties.getProperty("rooms", "0"))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDestination() {
        return destination;
    }

    public Date getStartTrip() {
        return startTrip;
    }

    public Date getEndTrip() {
        return endTrip;
    }

    public int getAdult() {
        return adult;
    }

    public int getChildren() {
        return children;
    }

    public int getRooms() {
        return rooms;
    }
}
