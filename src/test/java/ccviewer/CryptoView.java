package ccviewer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CryptoView{

    private CryptoController mainController;

    public Scene createScene(CryptoController cryptoController, Stage stage, cryptoData cryptoData, String Label) {
        mainController = cryptoController;
        stage.setTitle("Crypto Currency Viewer by Tyrone Veneracion");
        //Declarations of variables
        BorderPane root = new BorderPane();
        Label currentValue = setCurrentPriceLabel(cryptoData);
        BorderPane Top = new BorderPane();
        GridPane buttonsGrid = ButtonsGrid(Label);
        LineChart<String, Number> lineChart = cryptoLineChart(cryptoData, Label);

        //Alignments and Adjustments
        Top.setPadding(new Insets(10,10,10,10));
        currentValue.setAlignment(Pos.TOP_LEFT);
        buttonsGrid.setAlignment(Pos.TOP_RIGHT);
        Top.setLeft(currentValue);
        Top.setRight(buttonsGrid);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setTop(Top);
        root.setCenter(lineChart);

        Scene scene = new Scene(root, 1024, 720);
        scene.getStylesheets().add("MainStyle.css");
        return scene;
    }

    private Label setCurrentPriceLabel(cryptoData cryptoData) {
        String currentValue = "Current Price\n$" + formatDecimal(cryptoData.getData()[cryptoData.getData().length - 1].getClose());
        Label label = new Label(currentValue);
        return label;
    }

    private GridPane ButtonsGrid(String Label) {
        GridPane buttonsGrid = new GridPane();
        Button daysButton = new Button("Days");
        daysButton.setOnAction(mainController);

        GridPane.setConstraints(daysButton, 1, 0);
        Button hoursButton = new Button("Hours");
        hoursButton.setOnAction(mainController);

        GridPane.setConstraints(hoursButton,2,0);
        Button minutesButton = new Button("Minutes");
        minutesButton.setOnAction(mainController);

        GridPane.setConstraints(minutesButton,3,0);
        buttonsGrid.getChildren().addAll(daysButton,hoursButton,minutesButton);

        if(Label.equals("Days")){
            daysButton.setStyle("-fx-background-color: #D38400");
        }
        else if(Label.equals("Hours")){
            hoursButton.setStyle("-fx-background-color: #D38400");
        }
        else if(Label.equals("Minutes")){
            minutesButton.setStyle("-fx-background-color: #D38400");
        }

        return buttonsGrid;
    }

    private LineChart<String, Number> cryptoLineChart(cryptoData cryptoData, String xLabel) {
        Float lowestValue = null;
        Float highestValue = null;
        for (dataPerTime dpt : cryptoData.getData()) {
            if (lowestValue == null) {
                lowestValue = dpt.getClose();
            } else if (lowestValue > dpt.getClose()) {
                lowestValue = dpt.getClose();
            }
            if (highestValue == null) {
                highestValue = dpt.getClose();
            } else if (highestValue < dpt.getClose()) {
                highestValue = dpt.getClose();
            }
        }
        //defining the axes
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis(lowestValue -100,highestValue + 100,50);
        xAxis.setLabel(xLabel);
        //creating the chart
        final LineChart<String,Number> lineChart =
                new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Crypto Currency Viewer");
        //defining a series
        XYChart.Series series = setChartData(cryptoData);


        lineChart.getData().add(series);
        return lineChart;
    }

    public XYChart.Series setChartData(cryptoData cryptoData) {
        XYChart.Series series = new XYChart.Series();
        series.setName("BitCoin");
        //Sets the format of the xAxis
        SimpleDateFormat formatter = formatter(cryptoData.getTimeFrom(), cryptoData.getTimeTo());

        for (dataPerTime dpt : cryptoData.getData()) {

            Calendar newCalendar = getTimeAsDate(dpt.getTime());
            String formatted = formatter.format(newCalendar.getTime());
            series.getData().add(new XYChart.Data(formatted, dpt.getClose()));

        }
        return series;
    }

    SimpleDateFormat formatter(int timeStart, int timeEnd) {
        float seconds = timeEnd - timeStart;
        float minutes = seconds / 60;
        float hours = minutes / 60;
        float days = hours / 24;

        SimpleDateFormat minutesFormat = new SimpleDateFormat("mm:ss");
        SimpleDateFormat HoursFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat DateFormat = new SimpleDateFormat("MM-dd-yyyy");

        if (days > 1) {
            return DateFormat;
        }
        else if(hours > 1){
            return HoursFormat;
        }
        else{
            return minutesFormat;
        }
    }

    //Converts time in to a calendar
    Calendar getTimeAsDate(int time) {
        Calendar calendar = Calendar.getInstance();
        long seconds = time;
        long millis = seconds * 1000;
        calendar.setTimeInMillis(millis);
        return calendar;
    }

    public String formatDecimal(float number) {
        float epsilon = 0.004f; // 4 tenths of a cent
        if (Math.abs(Math.round(number) - number) < epsilon) {
            return String.format("%10.0f", number); // sdb
        } else {
            return String.format("%10.2f", number); // dj_segfault
        }
    }


}