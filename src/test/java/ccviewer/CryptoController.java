package ccviewer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class CryptoController extends Application implements EventHandler<ActionEvent> {

    private String Label = "Hours";
    private String minuteAddress = "https://min-api.cryptocompare.com/data/histominute?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD";
    private String hourAddress = "https://min-api.cryptocompare.com/data/histohour?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD";
    private String dayAddress = "https://min-api.cryptocompare.com/data/histoday?aggregate=1&e=CCCAGG&extraParams=CryptoCompare&fsym=BTC&limit=10&tryConversion=false&tsym=USD";

    private Stage mainStage;
    private String jsonResult;

    public static void main(String[] args) {
        launch(args);
    }

    void UpdateStage(){
        CryptoModule cryptoModule = new CryptoModule();
        CryptoView cryptoView = new CryptoView();
        String targetAddress;

        if(Label.equals("Minutes")){
            targetAddress = minuteAddress;
        }
        else if(Label.equals("Hours")){
            targetAddress = hourAddress;
        }
        else {
            targetAddress = dayAddress;
        }
        jsonResult = cryptoModule.GetJsonFromAddress(targetAddress);

        mainStage.setScene(cryptoView.SetScene(this,mainStage, cryptoModule.GetCryptoData(jsonResult),Label));
    }

    @Override public void start(Stage stage) {
        mainStage = stage;
        UpdateStage();
        stage.show();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(actionEvent.getSource().toString().contains("Days") && !Label.equals("Days")){
            Label = "Days";
        }
        else if(actionEvent.getSource().toString().contains("Hours")&& !Label.equals("Hours")){
            Label = "Hours";
        }
        else if(actionEvent.getSource().toString().contains("Minutes")&& !Label.equals("Minutes")){
            Label = "Minutes";
        }
        UpdateStage();
    }
}
