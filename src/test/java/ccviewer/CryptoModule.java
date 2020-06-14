package ccviewer;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class CryptoModule {
    private String minuteAddress = "https://min-api.cryptocompare.com/data/histominute?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD";
    private String hourAddress = "https://min-api.cryptocompare.com/data/histohour?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD";
    private String dayAddress = "https://min-api.cryptocompare.com/data/histoday?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD";

    //Gets CrytoData from a string in a json format
    cryptoData getCryptoData(String json) {
        Gson gson = new Gson();
        cryptoData cryptoData = gson.fromJson(json, ccviewer.cryptoData.class);
        return cryptoData;
    }

    String readApi(String label) {
        String targetAddress;
        if (label.equals("Minutes")) {
            targetAddress = minuteAddress;
        } else if (label.equals("Hours")) {
            targetAddress = hourAddress;
        } else {
            targetAddress = dayAddress;
        }
        return getJsonFromAddress(targetAddress);
    }

    //Returns a string from a URL
    String getJsonFromAddress(String urlAddress) {
        URL address = null;
        String line = "";
        try {
            address = new URL(urlAddress);
            InputStreamReader isr = new InputStreamReader(address.openStream());
            BufferedReader br = new BufferedReader(isr);
            if (br != null) line = br.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }
}
