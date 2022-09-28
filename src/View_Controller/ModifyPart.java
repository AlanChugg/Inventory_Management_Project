package View_Controller;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.IllegalFormatException;
import java.util.Optional;

/**This is the class controller for the AddProduct fxml file.*/
public class ModifyPart
{
    private Stage stage;
    private Part passingPart = null;


    @FXML
    private RadioButton modifyPartToggleHouse;
    @FXML
    private RadioButton modifyPartToggleOutsource;



    @FXML
    private TextField modifyPartIdTxt;
    @FXML
    private TextField modifyPartMinTxt;
    @FXML
    private TextField modifyPartInventoryTxt;
    @FXML
    private TextField modifyPartMaxTxt;
    @FXML
    private TextField modifyPartMachCompTxt;
    @FXML
    private TextField modifyPartPriceTxt;
    @FXML
    private TextField modifyPartNameTxt;


    @FXML
    private Label modifyPartLableSwitch;
    @FXML
    private Label modifyPartLabelNotificationF;




    /**This method switches the text to machine ID correlated with the radio button at the top of the screen*/
    @FXML
    void onActionModifyPartToggleHouse(ActionEvent event)
    {
        if (modifyPartToggleHouse.isSelected())
        {
            modifyPartLableSwitch.setText("Machine ID");
        }
    }

    /**This method switches the text to Company Name correlated with the radio button at the top of the screen*/
    @FXML
    void onActionModifyPartToggleOutsource(ActionEvent event)
    {
        if (modifyPartToggleOutsource.isSelected())
        {
            modifyPartLableSwitch.setText("Company Name");
        }
    }

    /**This method is called when the save button is pressed. Specifically it creates a new part and then calls the updatePart method to update the respective array.*/
    @FXML
    void onActionModifyPartButtonSave(ActionEvent event)
    {
        int invalidIsTrue = 0;
        int passingIndex = 0;
        String name = "DEFAULT";
        int stock = 0 ;
        double price = 0.00;
        int min = 0;
        int max = 0;
        int machineID = 0;
        String companyName = "DEFAULT";
        int saveSuccessful=0;


        //obtains the unique id of the part to be updated
        for (int i = 0; i < Inventory.getAllParts().size(); i++)
        {
            if (Inventory.getAllParts().get(i).getId() == passingPart.getId())
            {
                passingIndex = i;
                break;
            }
        }

        //Try block that converts user entered text.
        try { name = String.valueOf(modifyPartNameTxt.getText()); }
        catch(IllegalFormatException | NullPointerException e)
        {
            modifyPartLabelNotificationF.setText("PART NOT SAVED: Name is not a string!");

            invalidIsTrue = 1;
        }

        try { stock = Integer.parseInt(String.valueOf(modifyPartInventoryTxt.getText())); }
        catch(NumberFormatException e)
        {
            if (invalidIsTrue == 0){modifyPartLabelNotificationF.setText("PART NOT SAVED: Inventory is not an integer!");}

            invalidIsTrue = 1;
        }

        try { price = Double.parseDouble(String.valueOf(modifyPartPriceTxt.getText())); }
        catch(NumberFormatException e)
        {
            if (invalidIsTrue == 0){modifyPartLabelNotificationF.setText("PART NOT SAVED: Price is not a double!");}

            invalidIsTrue = 1;
        }

        try
        { min = Integer.parseInt(String.valueOf(modifyPartMinTxt.getText())); }
        catch(NumberFormatException e)
        {
            if (invalidIsTrue == 0){modifyPartLabelNotificationF.setText("PART NOT SAVED: Min is not an integer!");}

            invalidIsTrue = 1;
        }

        try
        { max = Integer.parseInt(String.valueOf(modifyPartMaxTxt.getText())); }
        catch(NumberFormatException e)
        {
            if (invalidIsTrue == 0){modifyPartLabelNotificationF.setText("PART NOT SAVED: Max is not an integer!");}
            invalidIsTrue = 1;
        }

        if(modifyPartToggleHouse.isSelected())
        {
            try { machineID = Integer.parseInt(String.valueOf(modifyPartMachCompTxt.getText())); }
            catch(NumberFormatException e)
            {
                if (invalidIsTrue == 0){modifyPartLabelNotificationF.setText("PART NOT SAVED: Machine ID is not an integer!");}

                invalidIsTrue = 1;
            }
        }
        else if (modifyPartToggleOutsource.isSelected())
        {
            try
            {
                companyName = String.valueOf(modifyPartMachCompTxt.getText());

                if (companyName.isEmpty())
                {
                    if (invalidIsTrue == 0){modifyPartLabelNotificationF.setText("PART NOT SAVED: Company Name is blank!");}
                    invalidIsTrue = 1;
                }
            }
            catch(IllegalFormatException | NullPointerException e)
            {
                if (invalidIsTrue == 0){modifyPartLabelNotificationF.setText("PART NOT SAVED: Company Name is not a string!");}
                invalidIsTrue = 1;
            }
        }
        else
        {
            if (invalidIsTrue == 0){modifyPartLabelNotificationF.setText("PART NOT SAVED: Radio button is not selected!");}

            invalidIsTrue = 1;
        }

        if (min > max)
        {
            if (invalidIsTrue == 0){modifyPartLabelNotificationF.setText("PART NOT SAVED: Min is greater than Max!");}

            invalidIsTrue = 1;
        }

        if (stock > max)
        {
            if (invalidIsTrue == 0){modifyPartLabelNotificationF.setText("PART NOT SAVED: Inventory is greater than Max!");}

            invalidIsTrue = 1;
        }

        if (stock < min)
        {
            if (invalidIsTrue == 0){modifyPartLabelNotificationF.setText("PART NOT SAVED: Inventory is less than Min!");}

            invalidIsTrue = 1;
        }

        if (name.isEmpty())
        {
            if (invalidIsTrue == 0){modifyPartLabelNotificationF.setText("PART NOT SAVED: Name is Blank!");}
            invalidIsTrue = 1;
        }

        if (passingPart == null)
        {
            invalidIsTrue = 1;
        }

        //Create and pass the new object to updatePart
        if( invalidIsTrue== 0 && modifyPartToggleHouse.isSelected())
        {
                InHousePart tempPart = new InHousePart(passingPart.getId(), name, price, stock, min, max, machineID );

                Inventory.updatePart(passingIndex, tempPart);

                saveSuccessful =1;
        }

        else if (invalidIsTrue== 0 && modifyPartToggleOutsource.isSelected())
        {
            OutsourcedPart tempPart = new OutsourcedPart(passingPart.getId(), name, price, stock, min, max, companyName);

            Inventory.updatePart(passingIndex, tempPart);

            saveSuccessful =1;

        }

        if (saveSuccessful ==1)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Save Successful!");
            alert.setContentText("Save Was Successful.");
            alert.showAndWait();
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        }

    }

    /**This function is called when the cancel button is pressed. Specifically upon user confirmation will close then current stage and load the main screen stage.*/
    @FXML
    void onActionModifyPartButtonCancel(ActionEvent event) throws IOException
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to close and return to the main screen? Data will not be saved.");
        Optional<ButtonType> results = alert.showAndWait();

        if (results.isPresent() && results.get() == ButtonType.OK)
        {

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    /**This function is called when the modify part button is pressed on the main screen and is responsible for transferring information about the selected part on the main screen to the update part screen.
      @param tempPart This parameter points to the object that was selected on the main screen part table view.*/
    public void setPartFromMainScreen (Part tempPart)
    {
        passingPart = tempPart;


        modifyPartIdTxt.setText(String.valueOf(tempPart.getId()));
        modifyPartMinTxt.setText(String.valueOf(tempPart.getMin()));
        modifyPartInventoryTxt.setText(String.valueOf(tempPart.getStock()));
        modifyPartMaxTxt.setText(String.valueOf(tempPart.getMax()));
        modifyPartPriceTxt.setText(String.valueOf(tempPart.getPrice()));
        modifyPartNameTxt.setText(String.valueOf(tempPart.getName()));

        if (tempPart instanceof InHousePart)
        {
            modifyPartMachCompTxt.setText(String.valueOf(((InHousePart) tempPart).getMachineID()));
            modifyPartToggleHouse.setSelected(true);
        }


        if (tempPart instanceof OutsourcedPart)
        {
            modifyPartMachCompTxt.setText(String.valueOf(((OutsourcedPart)tempPart).getCompanyName()));
            modifyPartToggleOutsource.setSelected(true);
        }

    }

    /**Unused*/
    @FXML
    void onActionModifyPartIdTxt(ActionEvent event){}
    /**Unused*/
    @FXML
    void onActionModifyPartNameTxt(ActionEvent event){}
    /**Unused*/
    @FXML
    void onActionModifyPartInventoryTxt(ActionEvent event){}
    /**Unused*/
    @FXML
    void onActionModifyPartPriceTxt(ActionEvent event){}
    /**Unused*/
    @FXML
    void onActionModifyPartMaxTxt(ActionEvent event){}
    /**Unused*/
    @FXML
    void onActionModifyPartMinTxt(ActionEvent event){}
    /**Unused*/
    @FXML
    void onActionModifyPartMachCompTxt(ActionEvent event){}

}
