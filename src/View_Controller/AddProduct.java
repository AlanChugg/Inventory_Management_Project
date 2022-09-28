package View_Controller;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.Optional;



/**This is the class controller for the AddProduct fxml file.*/
public class AddProduct {

    private Stage stage;
    private ArrayList<Part> addPartToProductL = new ArrayList<>();
    private ObservableList<Part> addPartToProductOList = FXCollections.observableList(addPartToProductL);



    @FXML
    private TextField addProductNameTxt;
    @FXML
    private TextField addProductInventoryTxt;
    @FXML
    private TextField addProductPriceTxt;
    @FXML
    private TextField addProductMaxTxt;
    @FXML
    private TextField addProductMinTxt;
    @FXML
    private TextField addProductSearch;


    @FXML
    private TableView<Part> addProductTablePart;
    @FXML
    private TableColumn<Part, Integer> addProductTablePartId;
    @FXML
    private TableColumn<Part, String> addProductTablePartName;
    @FXML
    private TableColumn<Part, Integer> addProductTablePartInventory;
    @FXML
    private TableColumn<Part, Double> addProductTablePartPrice;


    @FXML
    private TableView<Part> addProductTableAssoc;
    @FXML
    private TableColumn<Part, Integer> addProductTableAssocId;
    @FXML
    private TableColumn<Part, String> addProductTableAssocName;
    @FXML
    private TableColumn<Part, Integer> addProductTableAssocInventory;
    @FXML
    private TableColumn<Part, Double> addProductTableAssocPrice;


    @FXML
    private Label addProductNotificationF;



    /**This method correlates to the search bar at the top left of the screen. Specifically is takes a partial string or integer and looks for the corresponding object in the allProducts array.*/
    @FXML
    void onActionAddProductSearch(ActionEvent event)
    {
        String query = addProductSearch.getText();
        ObservableList<Part> partSearchResult = Inventory.lookupPart(query);
        int resultIsEmpty = 0;

        if (partSearchResult.isEmpty())
        {
            resultIsEmpty =1;

            try
            {
                //check to see if its actually an ID
                int whatIfId = Integer.parseInt(query);
                Part tempPart = Inventory.lookupPart(whatIfId);
                if (tempPart != null)
                {
                    partSearchResult.add(tempPart);
                    resultIsEmpty =0;
                }

            }
            catch (NumberFormatException e)
            {
                //Ignore the exception
            }
        }
        if(resultIsEmpty == 1)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Search Results");
                alert.setContentText("No Search Results Found.");
                alert.showAndWait();
            }
        addProductTablePart.setItems(partSearchResult);
    }



    /**This method associates a selected part to a product. It is called when the add button is pressed and displays the temporary list holding the products associated parts.*/
    @FXML
    void onActionAddProductButtonAdd(ActionEvent event)
    {
        if (addProductTablePart.getSelectionModel().getSelectedItem() != null && addProductTablePart.getSelectionModel().getSelectedItem().getId() != 0)
        {
            Part selectedPart = addProductTablePart.getSelectionModel().getSelectedItem();
            addPartToProductOList.add(selectedPart);
            addProductTableAssoc.setItems(addPartToProductOList);
        }
    }

    /**This method is called when the remove associated part is pressed and will remove the selected part. Specifically this part removes the object from the temporary list and re displays that list.*/
    @FXML
    void onActionAddProductButtonRemove(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected part?");
        Optional<ButtonType> results = alert.showAndWait();

        if (results.isPresent() && results.get() == ButtonType.OK)
        {

            if (addProductTableAssoc.getSelectionModel().getSelectedItem() != null)
            {
                for (int i = 0; i < addPartToProductOList.size(); i++)
                {
                     if (addProductTableAssoc.getSelectionModel().getSelectedItem().getId() == addPartToProductOList.get(i).getId())
                     {
                          addPartToProductOList.remove(i);
                          break;
                     }
                }

            addProductTableAssoc.setItems(addPartToProductOList);
            }
            else
            {
                //Nothing is selected error
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Something went wrong!");
                alert2.setContentText("Please select a part to remove.");
                alert2.showAndWait();
            }
        }
    }

    /**This method is called when the save button is pressed and saves the product information. Specifically it will update the respective associated parts array using the temporary list and then add the product to the allProducts array.*/
    @FXML
    void onActionAddProductButtonSave(ActionEvent event) {
        int invalidIsTrue = 0;
        int id = OptionalFileManagement.generateNewIndex();
        String name = "DEFAULT";
        int stock = 0;
        double price = 0.00;
        int min = 0;
        int max = 0;

// obligatory try catch block for casting.
        try
        {
            name = String.valueOf(addProductNameTxt.getText());
        }
        catch (IllegalFormatException | NullPointerException e)
        {
            addProductNotificationF.setText("PART NOT SAVED: Name is not a string!");

            invalidIsTrue = 1;
        }

        try
        {
            stock = Integer.parseInt(String.valueOf(addProductInventoryTxt.getText()));
        }
        catch (NumberFormatException e)
        {
            if (invalidIsTrue == 0)
            {
                addProductNotificationF.setText("PART NOT SAVED: Inventory is not an integer!");
            }

            invalidIsTrue = 1;
        }

        try
        {
            price = Double.parseDouble(String.valueOf(addProductPriceTxt.getText()));
        }
        catch (NumberFormatException e)
        {
            if (invalidIsTrue == 0)
            {
                addProductNotificationF.setText("PART NOT SAVED: Price is not a double!");
            }

            invalidIsTrue = 1;
        }

        try
        {
            min = Integer.parseInt(String.valueOf(addProductMinTxt.getText()));
        }
        catch (NumberFormatException e)
        {
            if (invalidIsTrue == 0)
            {
                addProductNotificationF.setText("PART NOT SAVED: Min is not an integer!");
            }

            invalidIsTrue = 1;
        }

        try
        {
            max = Integer.parseInt(String.valueOf(addProductMaxTxt.getText()));
        }
        catch (NumberFormatException e)
        {
            if (invalidIsTrue == 0)
            {
                addProductNotificationF.setText("PART NOT SAVED: Max is not an integer!");
            }
            invalidIsTrue = 1;
        }


        if (min > max)
        {
            if (invalidIsTrue == 0)
            {
                addProductNotificationF.setText("PART NOT SAVED: Min is greater than Max!");
            }

            invalidIsTrue = 1;
        }

        if (stock > max)
        {
            if (invalidIsTrue == 0)
            {
                addProductNotificationF.setText("PART NOT SAVED: Inventory is greater than Max!");
            }

            invalidIsTrue = 1;
        }

        if (stock < min)
        {
            if (invalidIsTrue == 0)
            {
                addProductNotificationF.setText("PART NOT SAVED: Inventory is less than Min!");
            }

            invalidIsTrue = 1;
        }

        if (name.isEmpty())
        {
            if (invalidIsTrue == 0)
            {
                addProductNotificationF.setText("PART NOT SAVED: Name is Blank!");
            }
            invalidIsTrue = 1;
        }

        if (invalidIsTrue == 0)
        {
                Product tempProduct = new Product(id, name, price, stock, min, max);

                for (int i = 0; i < addPartToProductOList.size(); i++)
                {
                    tempProduct.addAssociatedPart(addPartToProductOList.get(i));
                }

                Inventory.addProduct(tempProduct);

                OptionalFileManagement.emptyGuard();
                addProductNameTxt.clear();
                addProductInventoryTxt.clear();
                addProductPriceTxt.clear();
                addProductMinTxt.clear();
                addProductMaxTxt.clear();
                addPartToProductOList.clear();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Save Successful");
            alert.setContentText("Save Was Successful!");
            alert.showAndWait();

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        }

    }

    /**Called when the cancel button is pressed this method confirms and then returns to the main screen.*/
    @FXML
    void onActionAddProductButtonCancel(ActionEvent event) throws IOException
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to close and return to the main screen? Data will not be saved.");
        Optional<ButtonType> results = alert.showAndWait();

        if (results.isPresent() && results.get() == ButtonType.OK)
        {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }


    /**This initialize will set the columns in the table. It will not populate the table but will prepare it for data entry.*/
    public void initialize()
    {
        addProductTablePart.setItems(Inventory.getAllParts());
        addProductTablePartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductTablePartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductTablePartInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductTablePartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));


        addProductTableAssocId.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProductTableAssocName.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProductTableAssocInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProductTableAssocPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**Unused*/
    @FXML
    void onActionAddProductNameTxt(ActionEvent event){}
    /**Unused*/
    @FXML
    void onActionAddProductInventoryTxt(ActionEvent event){}
    /**Unused*/
    @FXML
    void onActionAddProductPriceTxt(ActionEvent event){}
    /**Unused*/
    @FXML
    void onActionAddProductMaxTxt(ActionEvent event){}
    /**Unused*/
    @FXML
    void onActionAddProductMinTxt(ActionEvent event){}

}
