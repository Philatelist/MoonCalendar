package com.slavyanin.example.mooncalendar.entity;

/**
 * Created by slavyanin on 15.03.16.
 */
public class MoonDay {

    private int number;
    private int phase;
    private String symbol;
    private String stone;
    private String title;
    private String description;

    public MoonDay(int number, int phase, String symbol, String stone, String title, String description) {
        this.number = number;
        this.phase = phase;
        this.symbol = symbol;
        this.stone = stone;
        this.title = title;
        this.description = description;
    }

    public MoonDay(int i) {
        this.number = i;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPhase() {
        return phase;
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getStone() {
        return stone;
    }

    public void setStone(String stone) {
        this.stone = stone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoonDay moonDay = (MoonDay) o;

        if (number != moonDay.number) return false;
        if (phase != moonDay.phase) return false;
        if (symbol != null ? !symbol.equals(moonDay.symbol) : moonDay.symbol != null) return false;
        if (stone != null ? !stone.equals(moonDay.stone) : moonDay.stone != null) return false;
        if (title != null ? !title.equals(moonDay.title) : moonDay.title != null) return false;
        return !(description != null ? !description.equals(moonDay.description) : moonDay.description != null);

    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + phase;
        result = 31 * result + (symbol != null ? symbol.hashCode() : 0);
        result = 31 * result + (stone != null ? stone.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MoonDay{" +
                "number=" + number +
                ", phase=" + phase +
                ", symbol='" + symbol + '\'' +
                ", stone='" + stone + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
