package View_Controller;

import Model.InHousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.IllegalFormatException;
import java.util.Optional;



/**This is the class controller for the add part fxml file.*/
public class AddPart {
    private Stage stage;


    @FXML
    private TextField addPartPriceTxt;
    @FXML
    private TextField addPartNameTxt;
    @FXML
    private TextField addPartMinTxt;
    @FXML
    private TextField addPartMachCompTxt;
    @FXML
    private TextField addPartInventoryTxt;
    @FXML
    private TextField addPartMaxTxt;


    @FXML
    private Button addPartButtonCancel;
    @FXML
    private Button addPartButtonSave;


    @FXML
    private RadioButton radioAddPartHouse;
    @FXML
    private RadioButton radioAddPartOuts;
    @FXML
    private ToggleGroup addPartToggle;


    @FXML
    private Label addPartLabelSwitch;
    @FXML
    private Label addPartNotificationS;
    @FXML
    private Label addPartNotificationF;


    /**This method is called when the save button is pressed. Specifically this method creates a new part using the user supplied inputs and adds it to the allParts array.*/
    @FXML
    void onActionAddPartButtonSave(ActionEvent event)
    {
        int invalidIsTrue = 0;
        int id = OptionalFileManagement.generateNewIndex();
        String name = "DEFAULT";
        int stock = 0 ;
        double price = 0.00;
        int min = 0;
        int max = 0;
        int machineID = 0;
        String companyName = "DEFAULT";

// Catch any exceptions thrown when casting types
        try { name = String.valueOf(addPartNameTxt.getText()); }
        catch(IllegalFormatException | NullPointerException e)
        {
            addPartNotificationF.setText("PART NOT SAVED: Name is not a string!");

            invalidIsTrue = 1;
        }

        try { stock = Integer.parseInt(String.valueOf(addPartInventoryTxt.getText())); }
        catch(NumberFormatException e)
        {
            if (invalidIsTrue == 0){addPartNotificationF.setText("PART NOT SAVED: Inventory is not an integer!");}

            invalidIsTrue = 1;
        }

        try { price = Double.parseDouble(String.valueOf(addPartPriceTxt.getText())); }
        catch(NumberFormatException e)
        {
            if (invalidIsTrue == 0){addPartNotificationF.setText("PART NOT SAVED: Price is not a double!");}

            invalidIsTrue = 1;
        }

        try
        { min = Integer.parseInt(String.valueOf(addPartMinTxt.getText())); }
        catch(NumberFormatException e)
        {
            if (invalidIsTrue == 0){addPartNotificationF.setText("PART NOT SAVED: Min is not an integer!");}

            invalidIsTrue = 1;
        }

        try
        { max = Integer.parseInt(String.valueOf(addPartMaxTxt.getText())); }
        catch(NumberFormatException e)
        {
            if (invalidIsTrue == 0){addPartNotificationF.setText("PART NOT SAVED: Max is not an integer!");}
            invalidIsTrue = 1;
        }

        if(radioAddPartHouse.isSelected())
        {
            try { machineID = Integer.parseInt(String.valueOf(addPartMachCompTxt.getText())); }
            catch(NumberFormatException e)
            {
                if (invalidIsTrue == 0){addPartNotificationF.setText("PART NOT SAVED: Machine ID is not an integer!");}

                invalidIsTrue = 1;
            }
        }
        else if (radioAddPartOuts.isSelected())
        {
            try
            {
                companyName = String.valueOf(addPartMachCompTxt.getText());

                if (companyName.isEmpty())
                {
                    if (invalidIsTrue == 0){addPartNotificationF.setText("PART NOT SAVED: Company Name is blank!");}
                    invalidIsTrue = 1;
                }
            }
            catch(IllegalFormatException | NullPointerException e)
            {
                if (invalidIsTrue == 0){addPartNotificationF.setText("PART NOT SAVED: Company Name is not a string!");}
                invalidIsTrue = 1;
            }
        }
        else
            {
                if (invalidIsTrue == 0){addPartNotificationF.setText("PART NOT SAVED: Radio button is not selected!");}

                invalidIsTrue = 1;
            }

        if (min > max)
        {
            if (invalidIsTrue == 0){addPartNotificationF.setText("PART NOT SAVED: Min is greater than Max!");}

            invalidIsTrue = 1;
        }

        if (stock > max)
        {
            if (invalidIsTrue == 0){addPartNotificationF.setText("PART NOT SAVED: Inventory is greater than Max!");}

            invalidIsTrue = 1;
        }

        if (stock < min)
        {
            if (invalidIsTrue == 0){addPartNotificationF.setText("PART NOT SAVED: Inventory is less than Min!");}

            invalidIsTrue = 1;
        }

        if (name.isEmpty())
        {
            if (invalidIsTrue == 0){addPartNotificationF.setText("PART NOT SAVED: Name is Blank!");}
            invalidIsTrue = 1;
        }

        if(invalidIsTrue == 0 && radioAddPartHouse.isSelected())
        {
           InHousePart part = new InHousePart(id, name, price, stock, min, max, machineID);
           Inventory.addPart(part);

           OptionalFileManagement.emptyGuard();
           addPartNotificationF.setText("");
           addPartPriceTxt.clear();
           addPartNameTxt.clear();
           addPartMinTxt.clear();
           addPartMachCompTxt.clear();
           addPartInventoryTxt.clear();
           addPartMaxTxt.clear();

           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Save Successful");
           alert.setContentText("Save Was Successful!");
           alert.showAndWait();

           stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
           stage.close();
        }

        else if (invalidIsTrue == 0 && radioAddPartOuts.isSelected())
        {
            OutsourcedPart part = new OutsourcedPart(id, name, price, stock, min, max, companyName);
            Inventory.addPart(part);

            OptionalFileManagement.emptyGuard();
            addPartNotificationF.setText("");
            addPartPriceTxt.clear();
            addPartNameTxt.clear();
            addPartMinTxt.clear();
            addPartMachCompTxt.clear();
            addPartInventoryTxt.clear();
            addPartMaxTxt.clear();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Save Successful");
            alert.setContentText("Save Was Successful!");
            alert.showAndWait();

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();


        }
    }

    /**This method is called when the cancel button is pressed. Specifically this button confirms cancellation and then launches the MainScreen stage.*/
    @FXML
    void onActionAddPartButtonCancel(ActionEvent event) throws IOException
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to close and return to the main screen? Data will not be saved.");
        Optional<ButtonType> results = alert.showAndWait();

        if (results.isPresent() && results.get() == ButtonType.OK)
        {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    /**This method switches the text to machine ID correlated with the radio button at the top of the screen*/
    @FXML
    void onActionRadioAddPartHouse(ActionEvent event)
    {
        if (radioAddPartHouse.isSelected())
        {
            addPartLabelSwitch.setText("Machine ID");
        }
    }

    /**This method switches the text to Company Name correlated with the radio button at the top of the screen*/
    @FXML
    void onActionRadioAddPartOuts(ActionEvent event)
    {
        if (radioAddPartOuts.isSelected())
        {
            addPartLabelSwitch.setText("Company Name");
        }
    }


}




