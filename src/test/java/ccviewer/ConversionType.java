package ccviewer;

public class ConversionType {

    private String type;
    private String conversionSymbol;

    public ConversionType(String type, String conversionSymbol) {
        this.type = type;
        this.conversionSymbol = conversionSymbol;
    }

    public String GetType(){
        return type;
    }

    public String GetConversionSymbol(){
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
