package ccviewer;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class CryptoModule
{
    //Gets CrytoData from a string in a json format
    CryptoData GetCryptoData(String json){
        Gson gson = new Gson();
        CryptoData cryptoData = gson.fromJson(json,CryptoData.class);
        return cryptoData;
    }

    //Returns a string from a URL
    String GetJsonFromAddress(String urlAddress){
        URL address = null;
        String line = "";
        try {
            address = new URL(urlAddress);
            InputStreamReader isr = new InputStreamReader(address.openStream());
            BufferedReader br = new BufferedReader(isr);
            if(br!=null) line = br.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return line;
    }
}
