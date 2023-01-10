package core.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class TimeUtils
{
    private static LocalDateTime dateTime = LocalDateTime.now();

    public static String getCurrentDate(double seconds)
    {
        dateTime = dateTime.plusSeconds((int) seconds);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy");
        return formatter.format(dateTime);
    }

    public static String[] getTimeStep(double timeStep)
    {
        boolean hours = false;
        if(Math.abs(timeStep) < 1.0 && timeStep != 0)
        {
            timeStep *= 24;
            hours = true;
        }

        int intValue = (int) timeStep;
        String time = timeStep - intValue == 0.0 ? String.valueOf(intValue) : String.valueOf(timeStep);
        String units;
        if(Math.abs(timeStep) == 1) units = hours ? "hour" : "day";
        else units = hours ? "hours" : "days";

        return new String[] {time, units};
    }
}