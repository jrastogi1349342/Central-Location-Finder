package org.finder.route;

public class RouteSummary {
    private int duration; //Unit: seconds
    private int length; //Unit: meters

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "RouteSummary{" +
                "duration=" + duration +
                ", length=" + length +
                '}';
    }
}
