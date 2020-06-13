package ccviewer;

import com.google.gson.annotations.SerializedName;

public class DataPerTime {

    private int time;
    private float close;
    private float high;
    private float low;
    private float open;
    @SerializedName(value = "volumefrom")
    private float volumeFrom;
    @SerializedName(value = "volumeto")
    private float volumeTo;

    public DataPerTime(int time, float close, float high, float low, float open, float volumeFrom, float volumeTo) {
        this.time = time;
        this.close = close;
        this.high = high;
        this.low = low;
        this.open = open;
        this.volumeFrom = volumeFrom;
        this.volumeTo = volumeTo;
    }

    public  int GetTime(){
        return time;
    }

    public float GetClose(){
        return close;
    }

    public float GetHigh(){
        return high;
    }

    public float GetLow(){
        return low;
    }

    public float GetOpen(){
        return open;
    }

    public float GetVolumeFrom(){
        return volumeFrom;
    }

    public float GetVolumeTo(){
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

