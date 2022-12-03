public class JulianDate extends Date {

    public JulianDate() {
        super(1, 1, 1);
        addDays(719164);
        long millisToAdd = System.currentTimeMillis() + java.util.TimeZone.getDefault().getRawOffset();
        long daysToAdd = millisToAdd / 1000 / 60 / 60 / 24;

        addDays((int) daysToAdd);
    }

    public JulianDate(int year, int month, int day) {
        super(year, month, day);
    }


    public boolean isLeapYear(int year) {
        return (year % 4 == 0);
    }

}
