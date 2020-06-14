package ccviewer;

import com.google.gson.annotations.SerializedName;

public class dataPerTime {

    private int time;
    private float close;
    private float high;
    private float low;
    private float open;
    @SerializedName(value = "volumefrom")
    private float volumeFrom;
    @SerializedName(value = "volumeto")
    private float volumeTo;

    public dataPerTime(int time, float close, float high, float low, float open, float volumeFrom, float volumeTo) {
        this.time = time;
        this.close = close;
        this.high = high;
        this.low = low;
        this.open = open;
        this.volumeFrom = volumeFrom;
        this.volumeTo = volumeTo;
    }

    public int getTime() {
        return time;
    }

    public float getClose() {
        return close;
    }

    public float getHigh() {
        return high;
    }

    public float getLow() {
        return low;
    }

    public float getOpen() {
        return open;
    }

    public float getVolumeFrom() {
        return volumeFrom;
    }

    public float getVolumeTo() {
        return volumeTo;
    }

    @Override
    public String toString() {
        return "DataPerTime{" +
                "time=" + time +
                ", close=" + close +
                ", high=" + high +
                ", low=" + low +
                ", open=" + open +
                ", volumeFrom=" + volumeFrom +
                ", volumeTo=" + volumeTo +
                '}';
    }
}

