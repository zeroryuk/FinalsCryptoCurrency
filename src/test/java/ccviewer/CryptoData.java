package ccviewer;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class CryptoData {

    @SerializedName(value = "Response")
    private String response;
    @SerializedName(value = "Type")
    private int type;
    @SerializedName(value = "Aggregated")
    private boolean aggregated;
    @SerializedName(value = "Data")
    private DataPerTime data[];
    @SerializedName(value = "TimeTo")
    private int timeTo;
    @SerializedName(value = "TimeFrom")
    private int timeFrom;
    @SerializedName(value = "FirstValueInArray")
    private boolean firstValueInArray;
    //RateLimit
    @SerializedName(value = "HasWarning")
    boolean hasWarning;

    public CryptoData(String response, int type, boolean aggregated, DataPerTime[] data, int timeTo, int timeFrom, boolean firstValueInArray) {
        this.response = response;
        this.type = type;
        this.aggregated = aggregated;
        this.data = data;
        this.timeTo = timeTo;
        this.timeFrom = timeFrom;
        this.firstValueInArray = firstValueInArray;
    }

    public String GetResponse(){
        return response;
    }
    public int GetType(){
        return type;
    }
    public boolean GetAggregated(){
        return aggregated;
    }
    public DataPerTime[] GetData(){
        return data;
    }
    public int GetTimeTo(){
        return timeTo;
    }
    public int GetTimeFrom(){
        return timeFrom;
    }
    public boolean GetFirstValueInArray(){
        return firstValueInArray;
    }
    public boolean GetHasWarning(){
        return hasWarning;
    }

    @Override
    public String toString() {
        return "CryptoData{" +
                "response='" + response + '\'' +
                ", type=" + type +
                ", aggregated=" + aggregated +
                ", data=" + Arrays.toString(data) +
                ", timeTo=" + timeTo +
                ", timeFrom=" + timeFrom +
                ", firstValueInArray=" + firstValueInArray +
                ", hasWarning=" + hasWarning +
                '}';
    }

}
