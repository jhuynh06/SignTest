package com.example.signtest;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class HelloController {
    public int options;
    @FXML
    public Label terms;
    @FXML
    public Label NegativeSigns;
    @FXML
    public Text negativeCount;
    @FXML
    public Text pValueDisplay;
    @FXML
    public Text positiveCount;
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
    public Text nTermsDisplay;
    @FXML
    public JFXButton executeCommand;
    @FXML
    public MenuItem dropDownSigns;
    @FXML
    public MenuItem dropDownPValue;
    @FXML
    public MenuItem dropDownCalculate;
    @FXML
    public MenuButton CalculateMenu;
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
    @FXML
    public void handleMenuButton(MouseEvent event) {
        System.out.println(event.getSource());
    }
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
    public void handleDropDownSigns(ActionEvent event) {
        options = 2;
        CalculateMenu.setText("Calculate Signs");
    }
    @FXML
    public void handledropDownPValue(ActionEvent event) {
        options = 1;
        CalculateMenu.setText("Calculate P-Value");
    }
    @FXML
    public void handledropDownCalculate(ActionEvent event) {
        options = 0;
        CalculateMenu.setText("Conclude");
    }
    @FXML
    public void handleExecuteButton(MouseEvent Event) {
        if (options == 0) {
            handleConclusionButton();
            options = 3;
        }
        else if (options == 1) {
            handlePbutton();
            options = 3;
        }
        else if (options == 2) {
            handleCalculate();
            options = 3;
        }

    }

    public void handleSigLevel(KeyEvent keyEvent) {
        sig = Double.parseDouble(sigLevel.getText());
    }

    public void handleProbLevel(KeyEvent keyEvent) {
        prob =  Double.parseDouble(probField.getText());
    }
    public void handleMedian(KeyEvent keyEvent) {
        medianForOneSample = Double.parseDouble(medianField.getText());
    }
    public void handleCalculate() {
        signTest = new SignTest();
        signTest.takeSignsTwoSample(DataSet.getData1(), DataSet.getData2());
        nTermsDisplay.setText(Integer.toString(signTest.getnTerms()));
        positiveCount.setText(Integer.toString(signTest.getPos()));
        negativeCount.setText(Integer.toString(signTest.getNeg()));
    }
    @FXML
    public void handlePbutton() {
        signTest.pValue();
        pValueDisplay.setText(signTest.getpValue());
    }
    @FXML
    public void handleConclusionButton() {
        conclusionLabel.setText("Conclusion: " + signTest.conclusion());
    }
}