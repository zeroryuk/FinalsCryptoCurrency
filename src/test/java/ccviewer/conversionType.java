package ccviewer;

public class conversionType {

    private String type;
    private String conversionSymbol;

    public conversionType(String type, String conversionSymbol) {
        this.type = type;
        this.conversionSymbol = conversionSymbol;
    }

    public String getType() {
        return type;
    }

    public String getConversionSymbol() {
        return conversionSymbol;
    }

    @Override
    public String toString() {
        return "ConversionType{" +
                "type='" + type + '\'' +
                ", conversionSymbol='" + conversionSymbol + '\'' +
                '}';
    }
}
