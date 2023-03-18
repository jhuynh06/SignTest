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
    public boolean alreadyCalculated = false;
    public boolean nullCheck;
    public boolean alternativeCheck;
    public String hypothesisStorage;
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
    public TextField hypothesis;
    @FXML
    public Label transparentlabel;
    @FXML
    public Label Output;
    @FXML
    public TextArea textArea;
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
    public double medianForOneSample;
    protected static String path;
    protected static double sig;
    protected static double prob;
    protected static boolean SampleNumber;
    public boolean oneSample = false;
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
            if (oneSample == true) {
                convert.setOneSample(true);
                System.out.println(oneSample);
            }
            given = convert.readColumns(path);
            System.out.println(DataSet.getData1());
            System.out.println(DataSet.getData2());

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
        if (medianForOneSample > 0) {
            oneSample = true;
        }
    }
    public void handleCalculate() {
        signTest = new SignTest();
        if (oneSample == false) {
            signTest.takeSignsTwoSample(DataSet.getData1(), DataSet.getData2());
        }
        else {
            signTest.takeSignsOneSample(DataSet.getData1(), medianForOneSample);;
        }
        nTermsDisplay.setWrappingWidth(1000);
        positiveCount.setWrappingWidth(1000);
        negativeCount.setWrappingWidth(1000);
        nTermsDisplay.setText(Integer.toString(signTest.getnTerms()));
        positiveCount.setText(Integer.toString(signTest.getPos()));
        negativeCount.setText(Integer.toString(signTest.getNeg()));
    }
    @FXML
    public void handlePbutton() {
        signTest.pValue();
        pValueDisplay.setWrappingWidth(1000);
        pValueDisplay.setText(signTest.getpValue());
    }
    @FXML
    public void handleConclusionButton() {
        Output.setText("Look to the right for conclusion.");
        textArea.setWrapText(true);
        textArea.setText(signTest.conclusion());
    }
    @FXML
    public void handleParseHypothesis(KeyEvent keyEvent) {
        transparentlabel.setVisible(false);
        hypothesisStorage = hypothesis.getText();
        nullCheck = hypothesisStorage.contains("null");
        alternativeCheck = hypothesisStorage.contains("alternative");
        if ((alternativeCheck == false) && (nullCheck == false) && (alreadyCalculated == false)) {
            Output.setText("Missing alternative and null hypothesis!");
        }
        else if (alreadyCalculated == false){
            Output.setText("No output currently.");
        }

    }

}