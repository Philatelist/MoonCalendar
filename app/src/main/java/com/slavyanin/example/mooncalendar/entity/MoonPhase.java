package com.slavyanin.example.mooncalendar.entity;

/**
 * Created by slavyanin on 28.03.16.
 */
public class MoonPhase {

    private int imageId;
    private String titlePhase;
    private String shortDescrPhase;
    private String fullDescrPhase;

    public MoonPhase() {

    }

    public MoonPhase(int imageId, String titlePhase, String shortDescrPhase, String fullDescrPhase) {
        this.imageId = imageId;
        this.titlePhase = titlePhase;
        this.shortDescrPhase = shortDescrPhase;
        this.fullDescrPhase = fullDescrPhase;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitlePhase() {
        return titlePhase;
    }

    public void setTitlePhase(String titlePhase) {
        this.titlePhase = titlePhase;
    }

    public String getShortDescrPhase() {
        return shortDescrPhase;
    }

    public void setShortDescrPhase(String shortDescrPhase) {
        this.shortDescrPhase = shortDescrPhase;
    }

    public String getFullDescrPhase() {
        return fullDescrPhase;
    }

    public void setFullDescrPhase(String fullDescrPhase) {
        this.fullDescrPhase = fullDescrPhase;
    }

    @Override
    public String toString() {
        return "MoonPhase{" +
                "imageId=" + imageId +
                ", titlePhase='" + titlePhase + '\'' +
                ", shortDescrPhase='" + shortDescrPhase + '\'' +
                ", fullDescrPhase='" + fullDescrPhase + '\'' +
                '}';
    }
}
