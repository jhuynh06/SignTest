package com.example.signtest;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class HelloController {
    @FXML
    public Label terms;
    @FXML
    public Label positiveSigns;
    @FXML
    public Label NegativeSigns;
    @FXML
    public Label pLabel;
    @FXML
    public Label conclusionLabel;
    @FXML
    public JFXButton pvalueButton;
    @FXML
    public JFXButton conclusionButton;
    @FXML
    public Label size;
    @FXML
    private TextField probField;
    @FXML
    private TextField sigLevel;
    @FXML
    private Label label;
    @FXML
    private Label insertFile;
    @FXML
    private Button openFile;
    @FXML
    private Label medianLabel;
    @FXML
    private TextField medianField;
    private double medianForOneSample;
    protected static String path;
    protected static double sig;
    protected static double prob;
    protected static boolean SampleNumber;
    private SignTest signTest;
    private DataSet given;
    final FileChooser fileChooser = new FileChooser();
    public void setTextToLabel (String text) {
        insertFile.setText(text);
    }
    public void setTextforSize (String text) {
        size.setText(text + " kb");
    }
    @FXML
    public void handleOpenFile(ActionEvent actionEvent) {
        try {
            fileChooser.setTitle("My File Chooser");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("csv", "*.csv"));
            File file = fileChooser.showOpenDialog(null);
            path = file.getAbsolutePath();
            System.out.println(path);
            setTextToLabel(path);
            setTextforSize(String.valueOf(file.length()/1024));
            OpenCSV convert = new OpenCSV();
            given = convert.readColumns(path);

        }
        catch (Exception e) {
            System.out.println("No file selected!");
        }
    }


    @FXML
    public void handleSigLevel(KeyEvent keyEvent) {
        sig = Double.parseDouble(sigLevel.getText());
    }
    @FXML
    public void handleProbLevel(KeyEvent keyEvent) {
        prob =  Double.parseDouble(probField.getText());
    }
    @FXML
    public void handleMedian(KeyEvent keyEvent) {
        medianForOneSample = Double.parseDouble(medianField.getText());
    }
    @FXML
    public void handleCalculate(MouseEvent mouseEvent) {
        signTest = new SignTest();
        signTest.takeSignsTwoSample(DataSet.getData1(), DataSet.getData2());
        terms.setText("nTerms: " + signTest.getnTerms());
        positiveSigns.setText("Positive Signs: " + signTest.getPos());
        NegativeSigns.setText("Negative Signs: " + signTest.getNeg());
    }
    @FXML
    public void handlePbutton(MouseEvent mouseEvent) {
        signTest.pValue();
        pLabel.setText("pValue: " + signTest.getpValue());
    }
    @FXML
    public void handleConclusionButton(MouseEvent mouseEvent) {
        conclusionLabel.setText("Conclusion: " + signTest.conclusion());
    }
}