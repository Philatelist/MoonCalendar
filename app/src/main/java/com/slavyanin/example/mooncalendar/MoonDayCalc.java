package com.slavyanin.example.mooncalendar;

public class MoonDayCalc {

    private int day;
    private int month;
    private int year;
    private double moonday, eq, eq1, eq2;

    public double MoonDayCalc(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;

        int monthH = month;
        int yearH = year;
        if (month < 2) {
            monthH = month + 12;
            yearH = year - 1;
        }

        eq = (yearH / 100);
        eq1 = (eq / 3) + (eq / 4) + 6 - eq;
        eq2 = Math.round(((yearH / eq - (yearH / eq)) * 209) + monthH + eq1 + day) / 30;
        moonday = Math.round((eq2 - (eq2)) * 30 + 1);

        return moonday;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoonDayCalc that = (MoonDayCalc) o;

        if (day != that.day) return false;
        if (month != that.month) return false;
        return year == that.year;

    }

    @Override
    public int hashCode() {
        int result = day;
        result = 31 * result + month;
        result = 31 * result + year;
        return result;
    }

    @Override
    public String toString() {
        return "MoonDayCalc{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }
}
