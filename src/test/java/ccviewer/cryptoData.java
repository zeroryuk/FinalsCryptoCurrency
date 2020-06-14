package ccviewer;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class cryptoData {

    @SerializedName(value = "Response")
    private String response;
    @SerializedName(value = "Type")
    private int type;
    @SerializedName(value = "Aggregated")
    private boolean aggregated;
    @SerializedName(value = "Data")
    private dataPerTime data[];
    @SerializedName(value = "TimeTo")
    private int timeTo;
    @SerializedName(value = "TimeFrom")
    private int timeFrom;
    @SerializedName(value = "FirstValueInArray")
    private boolean firstValueInArray;
    //RateLimit
    @SerializedName(value = "HasWarning")
    boolean hasWarning;

    public cryptoData(String response, int type, boolean aggregated, dataPerTime[] data, int timeTo, int timeFrom, boolean firstValueInArray) {
        this.response = response;
        this.type = type;
        this.aggregated = aggregated;
        this.data = data;
        this.timeTo = timeTo;
        this.timeFrom = timeFrom;
        this.firstValueInArray = firstValueInArray;
    }

    public String getResponse() {
        return response;
    }

    public int getType() {
        return type;
    }

    public boolean getAggregated() {
        return aggregated;
    }

    public dataPerTime[] getData() {
        return data;
    }

    public int getTimeTo() {
        return timeTo;
    }

    public int getTimeFrom() {
        return timeFrom;
    }

    public boolean getFirstValueInArray() {
        return firstValueInArray;
    }

    public boolean getHasWarning() {
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
