public class GregorianDate extends Date {

    public GregorianDate() {  /*Default constructor*/
        super(1970, 1, 1);
        long millisToAdd = System.currentTimeMillis() + java.util.TimeZone.getDefault().getRawOffset();
        long daysToAdd = millisToAdd / 1000 / 60 / 60 / 24;

        addDays((int)daysToAdd);

    }
    public GregorianDate(int year, int month, int day) {
        super(year, month, day);
    }


    public boolean isLeapYear(int year) {
        /*return(year % 4 == 0 || (year % 100 == 0 && year % 100 != 0));*/
        return((year % 4 == 0 && year % 100 != 0) || (year % 100 == 0 && year % 400 == 0));
    }

}

