package radion.app.authoshkola.config.security;


import java.sql.Date;
import java.time.Period;

public class Test {
    public static void main(String[] args) {

        Date date1 = Date.valueOf("2024-05-06");
        Date date2 = Date.valueOf("2024-05-12");

        Period period = Period.between(date1.toLocalDate(), date2.toLocalDate());

        if (period.getDays() < 7){
            System.out.println(date1);
        }else {
            System.out.println(date2);
        }

        System.out.println("Разница между датами составляет: ");
        System.out.println("Лет: " + period.getYears());
        System.out.println("Месяцев: " + period.getMonths());
        System.out.println("Дней: " + period.getDays());
    }
}
