package com.example.simplecalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.util.ArrayList;

public class HelloController {
    public ToggleGroup mathAction;
    public ToggleGroup modify;
    public RadioButton modifyB;
    public Button submitButton;
    @FXML
    private TextField inputFieldResult;
    @FXML
    private TextField inputFieldA;
    @FXML
    private TextField inputFieldB;
    @FXML
    private RadioButton modifyA;
    @FXML
    private RadioButton checkboxAdd;
    @FXML
    private RadioButton checkboxSub;
    @FXML
    private RadioButton checkboxMulti;
    @FXML
    private RadioButton checkboxDiv;


    @FXML
    protected void getButtonValue(ActionEvent event){

        Button clickedButton = (Button) event.getSource();

        String buttonValue = clickedButton.getText();


        String inputFieldText = modifyA.isSelected() ?
                inputFieldA.getText() : inputFieldB.getText();

        if(inputFieldText.equals("0") && !buttonValue.equals(","))
            inputFieldText = "";

        if (inputFieldText.contains(",") && buttonValue.equals(","))
            return;

        String textToDisplay = inputFieldText + buttonValue;
        if(modifyA.isSelected())
            inputFieldA.setText(textToDisplay);
        else
            inputFieldB.setText(textToDisplay);
    }
    @FXML
    protected void clearTextField(){
        String text = modifyA.isSelected() ? inputFieldA.getText() : inputFieldB.getText();
        text = text.length() <= 1 ? "0" : text.substring(0, text.length() - 1);

        if (modifyA.isSelected())
            inputFieldA.setText(text);
        else
            inputFieldB.setText(text);

    }
    @FXML
    protected void updateTextField() {

        ArrayList<RadioButton> checkboxes = new ArrayList<>();
        checkboxes.add(checkboxAdd);
        checkboxes.add(checkboxSub);
        checkboxes.add(checkboxMulti);
        checkboxes.add(checkboxDiv);

        String matchAction = "";

        for (RadioButton checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                matchAction = checkbox.getText();
            }
        }

        doMatch(matchAction);
    }

    public void doMatch(String matchAction){

        float numA = 0;
        float numB = 0;
        float result = 0;
        String a = inputFieldA.getText();
        String b = inputFieldB.getText();

        if(a.contains(",")){
            int index = a.indexOf(",");
            if(a.length()-1 == index)
                a += "00";
        }
        if(b.contains(",")){
            int index = b.indexOf(",");
            if(b.length()-1 == index)
                b += "00";
        }

        try{
            numA = Integer.parseInt(a);
            numB = Integer.parseInt(b);
        } catch (Exception e) {
            System.out.println("failed");
            inputFieldResult.setText("Błąd");
        }

        if ((numB == 0 || numA == 0 ) && matchAction.equals("/"))
            inputFieldResult.setText("Nie dzielimy przez 0");
        else {
            result = switch (matchAction) {
                case "+" -> numA + numB;
                case "-" -> numA - numB;
                case "*" -> numA * numB;
                case "/" -> numA / numB;
                default -> -9999;
            };
        }
        inputFieldResult.setText(String.valueOf(result));
    }
}