package log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger
{
    private static long time;

    public static void getLog(String title)
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        time = System.nanoTime();

        StringBuilder sb = new StringBuilder();

        sb.append("[").append(dtf.format(now)).append("] ");
        sb.append(title);

        System.out.println(sb);
    }

    public static long getTimeAfterLastLog()
    {
        return System.nanoTime() - time;
    }
}