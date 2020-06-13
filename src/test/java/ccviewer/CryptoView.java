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

    public Scene SetScene(CryptoController cryptoController,Stage stage, CryptoData cryptoData, String Label){
        mainController = cryptoController;
        stage.setTitle("Crypto Currency Viewer by Tyrone Veneracion");
        //Declarations of variables
        BorderPane root = new BorderPane();
        Label currentValue = SetLabel(cryptoData);
        BorderPane Top = new BorderPane();
        GridPane buttonsGrid = ButtonsGrid(Label);
        LineChart<String,Number> lineChart = CryptoLineChart(cryptoData,Label);

        //Alignments and Adjustments
        Top.setPadding(new Insets(10,10,10,10));
        currentValue.setAlignment(Pos.TOP_LEFT);
        buttonsGrid.setAlignment(Pos.TOP_RIGHT);
        Top.setLeft(currentValue);
        Top.setRight(buttonsGrid);
        root.setPadding(new Insets(10,10,10,10));
        root.setTop(Top);
        root.setCenter(lineChart);

        Scene scene  = new Scene(root,1024,720);
        scene.getStylesheets().add("MainStyle.css");
        return scene;
    }
    Label SetLabel(CryptoData cryptoData){
        String currentValue = "Current Price\n$"+formatDecimal(cryptoData.GetData()[cryptoData.GetData().length-1].GetClose());
        Label label = new Label(currentValue);
        return label;
    }

    private GridPane ButtonsGrid(String Label){
        GridPane buttonsGrid = new GridPane();
        Button daysButton = new Button("Days");
        daysButton.setOnAction(mainController);

        GridPane.setConstraints(daysButton,1,0);
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

    private LineChart<String,Number> CryptoLineChart(CryptoData cryptoData,String xLabel){
        Float lowestValue = null;
        Float highestValue = null;
        for (DataPerTime dpt : cryptoData.GetData()) {
            if(lowestValue == null){
                lowestValue = dpt.GetClose();
            }
            else if(lowestValue > dpt.GetClose()){
                lowestValue = dpt.GetClose();
            }
            if(highestValue == null){
                highestValue = dpt.GetClose();
            }
            else if(highestValue < dpt.GetClose()){
                highestValue = dpt.GetClose();
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
        XYChart.Series series = SetChartData(cryptoData);


        lineChart.getData().add(series);
        return lineChart;
    }

    public XYChart.Series SetChartData(CryptoData cryptoData){
        XYChart.Series series = new XYChart.Series();
        series.setName("BitCoin");
        //Sets the format of the xAxis
        SimpleDateFormat formatter = Formatter(cryptoData.GetTimeFrom(),cryptoData.GetTimeTo());

        for (DataPerTime dpt: cryptoData.GetData()) {

            Calendar newCalendar = GetTimeAsDate(dpt.GetTime());
            String formatted = formatter.format(newCalendar.getTime());
            series.getData().add(new XYChart.Data(formatted, dpt.GetClose()));

        }
        return series;
    }

    SimpleDateFormat Formatter(int timeStart,int timeEnd){
        float seconds = timeEnd - timeStart;
        float minutes = seconds/60;
        float hours = minutes/60;
        float days = hours/24;

        SimpleDateFormat minutesFormat = new SimpleDateFormat("mm:ss");
        SimpleDateFormat HoursFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat DateFormat = new SimpleDateFormat("MM-dd-yyyy");

        if(days > 1){
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
    Calendar GetTimeAsDate(int time){
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