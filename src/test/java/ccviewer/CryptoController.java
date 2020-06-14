package ccviewer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

import java.util.concurrent.CompletableFuture;

public class CryptoController extends Application implements EventHandler<ActionEvent> {

    private String label = "Hours";
    private Stage mainStage;
    private String jsonResult;
    CryptoModule cryptoModule = new CryptoModule();
    CryptoView cryptoView = new CryptoView();

    public static void main(String[] args) {
        launch(args);
    }

    int updateStage(String jsonResult) {
        this.jsonResult = jsonResult;
        return 0;
    }

    String getAPI() {
        return cryptoModule.readApi(label);
    }

    void completableFuture() {
        while (true) {
            CompletableFuture<String> cf = new CompletableFuture<>();
            cf.supplyAsync(this::getAPI).thenApply(this::updateStage);
            while (true) {
                try {
                    mainStage.setScene(cryptoView.createScene(this, mainStage, cryptoModule.getCryptoData(jsonResult), label));
                    break;
                } catch (Exception e) {
                    System.out.println("Waiting for json");
                }
            }
            mainStage.show();
            break;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;
        completableFuture();
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (actionEvent.getSource().toString().contains("Days") && !label.equals("Days")) {
            label = "Days";
        } else if (actionEvent.getSource().toString().contains("Hours") && !label.equals("Hours")) {
            label = "Hours";
        } else if (actionEvent.getSource().toString().contains("Minutes") && !label.equals("Minutes")) {
            label = "Minutes";
        }
        completableFuture();
    }
}
