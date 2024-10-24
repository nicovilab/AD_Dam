import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("\tHola mundo");
        System.out.println("==================");
        System.out.println("Mi nombre es Nicolás Vila Besada");
        String pattern = "E, dd MMM yyyy HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        System.out.println(date);

    }
}