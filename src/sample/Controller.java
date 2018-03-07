package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private ComboBox fromType;
    @FXML private ComboBox toType;
    @FXML private TextField result;
    @FXML private TextField num1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Binary",
                        "Decimal",
                        "Hexadecimal",
                        "IEEE754"
                );
        fromType.getItems().addAll(options);
        toType.getItems().addAll(options);
        //from1.getItems().addAll("Binary", "Decimal", "Hexadecimal", "IEEE754", "Octal");
        //to1.getItems().addAll("Binary", "Decimal", "Hexadecimal", "IEEE754", "Octal");
    }

    @FXML
    private void handleConvert(ActionEvent event) {
        String v = num1.getText();
        String f = fromType.getSelectionModel().getSelectedItem().toString();
        String t = toType.getSelectionModel().getSelectedItem().toString();
        String o = "";
        switch(f) {
            case "Binary":
                switch(t) {
                    case "Binary":
                        o = v;
                        break;
                    case "Decimal":
                        o = String.valueOf(Converter.BinaryToDecimal(v));
                        break;
                    case "Hexadecimal":
                        o = Converter.BinaryToHex(v);
                        break;
                    case "IEEE754":
                        o = Converter.BinaryToIEEE(v);
                        break;
                }
                break;
            case "Decimal":
                switch(t) {
                    case "Binary":
                        o = Converter.DecimalToBinary(Integer.parseInt(v));
                        break;
                    case "Decimal":
                        o = v;
                        break;
                    case "Hexadecimal":
                        o = Converter.DecimalToHex(Integer.parseInt(v));
                        break;
                    case "IEEE754":
                        o = Converter.DecimalToIEEE(v);
                        break;
                }
                break;
            case "Hexadecimal":
                switch(t) {
                    case "Binary":
                        o = Converter.HexToBinary(v);
                        break;
                    case "Decimal":
                        o = String.valueOf(Converter.HexToDecimal(v));
                        break;
                    case "Hexadecimal":
                        o = v;
                        break;
                    case "IEEE754":
                        o = Converter.HexToIEEE(v);
                        break;
                }
                break;
            case "IEEE754":
                switch(t) {
                    case "Binary":
                        o = Converter.IEEEToBinary(v);
                        break;
                    case "Decimal":
                        o = Converter.IEEEToDecimal(v);
                        break;
                    case "Hexadecimal":
                        break;
                    case "IEEE754":
                        o = v;
                        break;
                }
                break;
            case "Octal":
                switch(t) {
                    case "Binary":
                        break;
                    case "Decimal":
                        break;
                    case "Hexadecimal":
                        break;
                    case "IEEE754":
                        break;
                    case "Octal":
                        o = v;
                        break;
                }
                    break;
        }

        result.setText(o);


    }


}
