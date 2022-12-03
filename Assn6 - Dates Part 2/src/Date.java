public class Date {
    private int year;
    private int month;
    private int day;

    public Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /*GETTERS*/
    public int getYear() { return year; }

    public int getMonth() { return month; }

    public int getDayOfMonth() { return day; }

    public boolean isLeapYear() { return isLeapYear(this.year); }

    public boolean isLeapYear(int year) {
        return true;
    }


    public String getMonthName() {
        switch (month) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "Error";
        }
    }

    public int getNumberOfDaysInMonth(int year, int month) {
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (isLeapYear()) {         /*DO i need to have year in this function call?*/
                    return 29;
                } else {
                    return 28;
                }
            default:
                return 0;
        }
    }


    public void addDays(int days) {
        for (int i = 0; i < days; i++) {
            day += 1;

            if (day > getNumberOfDaysInMonth(year, month)) {
                day = 1;
                month += 1;
            }
            if (month == 13) {
                month = 1;
                year += 1;
            }
        }
    }


    public void subtractDays(int days) {
        for (int k = 0; k < days; k++) {
            day -= 1;
            if (day == 0) {

                if (month == 1) {
                    month = 12;
                    day = getNumberOfDaysInMonth(year, (month));
                    year -= 1;
                }
                else {
                    month -= 1;
                    day = getNumberOfDaysInMonth(year, (month));
                }
            }
        }
    }

    public void printShortDate() {
        System.out.printf(" %d/%d/%d", getMonth(), getDayOfMonth(), getYear());
    }


    public void printLongDate() {
        System.out.printf(" %s %d, %d",getMonthName(), getDayOfMonth(), getYear());
    }







}
