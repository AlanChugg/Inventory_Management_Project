package View_Controller;


import Model.Inventory;
import Model.OutsourcedPart;
import Model.Part;
import Model.Product;
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


/**This controller class is responsible for managing the modify product fxml file. It also includes relevant methods for the respective interface.*/
public class ModifyProduct {
    Stage stage;
    private Product passingProduct = null;
    private ArrayList<Part> modifyPartToProductL = new ArrayList<>();
    private ObservableList<Part> modifyPartToProductOList = FXCollections.observableList(modifyPartToProductL);




    @FXML
    private TextField modifyProductPriceTxt;
    @FXML
    private TextField modifyProductPartSearch;
    @FXML
    private TextField modifyProductMinTxt;
    @FXML
    private TextField modifyProductNameTxt;
    @FXML
    private TextField modifyProductInventoryTxt;
    @FXML
    private TextField modifyProductMaxTxt;
    @FXML
    private TextField modifyProductIdTxt;
    @FXML
    private Label modifyProductNotificationF;


    @FXML
    private TableView<Part> modifyProductPartTable;
    @FXML
    private TableColumn<Part, Integer> modifyProductPartTableId;
    @FXML
    private TableColumn<Part, String> modifyProductPartTableName;
    @FXML
    private TableColumn<Part, Integer> modifyProductPartTableInventory;
    @FXML
    private TableColumn<Part, Double> modifyProductPartTablePrice;



    @FXML
    private TableView<Part> modifyProductAssocTable;
    @FXML
    private TableColumn<Part, Integer> modifyProductAssocTableId;
    @FXML
    private TableColumn<Part, String> modifyProductAssocTableName;
    @FXML
    private TableColumn<Part, Integer> modifyProductAssocTableInventory;
    @FXML
    private TableColumn<Part, Double> modifyProductAssocTablePrice;



    /**This method is correlated to the search bar on the screen. Specifically it takes user input, searches the allProducts array, and updates the local array and tableview with the results.*/
    @FXML
    void onActionModifyProductPartSearch(ActionEvent event)
    {
        String query = modifyProductPartSearch.getText();
        ObservableList<Part> partSearchResult = Inventory.lookupPart(query);
        int resultIsEmpty =0;

        if (partSearchResult.isEmpty())
        {
            resultIsEmpty =1;

            try
            {
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
        modifyProductPartTable.setItems(partSearchResult);
    }

    /**This method adds parts from the part table to the associated part table. Specifically it only makes a copy of the part.*/
    @FXML
    void onActionModifyProductButtonAdd(ActionEvent event)
    {
        if (modifyProductPartTable.getSelectionModel().getSelectedItem() != null && modifyProductPartTable.getSelectionModel().getSelectedItem().getId() != 0)
        {
            Part selectedPart = modifyProductPartTable.getSelectionModel().getSelectedItem();
            modifyPartToProductOList.add(selectedPart);
            OptionalFileManagement.emptyGuard();
            modifyProductAssocTable.setItems(modifyPartToProductOList);
        }

        if (modifyPartToProductOList.size() == 2)
        {
            for (int i = 0; i < modifyPartToProductOList.size(); i++)
            {
                if (modifyPartToProductOList.get(i).getId() == 0)
                {
                    modifyPartToProductOList.remove(i);
                    modifyProductAssocTable.setItems(modifyPartToProductOList);
                }
            }
        }
    }

    /**This method removes an associated part from the associated parts list. This method asks for confirmation from the user.*/
    @FXML
    void onActionModifyProductButtonRemove(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected product?");
        Optional<ButtonType> results = alert.showAndWait();

        if (results.isPresent() && results.get() == ButtonType.OK)
        {
            if (modifyProductAssocTable.getSelectionModel().getSelectedItem() != null)
            {
                for (int i = 0; i < modifyPartToProductOList.size(); i++)
                {
                    if (modifyProductAssocTable.getSelectionModel().getSelectedItem().getId() == modifyPartToProductOList.get(i).getId())
                    {
                        modifyPartToProductOList.remove(i);

                        if (modifyPartToProductOList.isEmpty())
                        {
                            int id = 0;
                            String name = "Demo";
                            double price = 0.00;
                            int stock = 0;
                            int min = 0;
                            int max = 0;
                            String companyName = "demo";

                            OutsourcedPart demoPart = new OutsourcedPart(id, name, price, stock, min, max, companyName);
                            modifyPartToProductOList.add(demoPart);
                        }
                        break;
                    }
                }

                modifyProductAssocTable.setItems(modifyPartToProductOList);
            }
            else
                {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Something went wrong!");
                    alert2.setContentText("Please select a part to remove.");
                    alert2.showAndWait();
                }
        }
    }

    /**RUNTIME ERROR This method, designed to save product information to the appropriate product list and associated part lists could not write more
      than one associated part to a product(continued in method detail). I solved this problem by using a for loop to parse through the product array and an embedded for loop
     to parse through the associated parts list.*/
    @FXML
    void onActionModifyProductButtonSave(ActionEvent event)
    {
        int invalidIsTrue = 0;
        int passingIndex = 0;
        String name = "DEFAULT";
        int stock = 0;
        double price = 0.00;
        int min = 0;
        int max = 0;
        int saveSuccessful =0;



        for (int i= 0; i < Inventory.getAllProducts().size(); i++)
        {
            if (passingProduct.getId() == Inventory.getAllProducts().get(i).getId())
            {
                passingIndex = i;
                break;
            }
        }

        try
        {
            name = String.valueOf(modifyProductNameTxt.getText());
        }
        catch (IllegalFormatException | NullPointerException e)
        {
            modifyProductNotificationF.setText("PART NOT SAVED: Name is not a string!");

            invalidIsTrue = 1;
        }

        try
        {
            stock = Integer.parseInt(String.valueOf(modifyProductInventoryTxt.getText()));
        }
        catch (NumberFormatException e)
        {
            if (invalidIsTrue == 0)
            {
                modifyProductNotificationF.setText("PART NOT SAVED: Inventory is not an integer!");
            }

            invalidIsTrue = 1;
        }

        try
        {
            price = Double.parseDouble(String.valueOf(modifyProductPriceTxt.getText()));
        }
        catch (NumberFormatException e)
        {
            if (invalidIsTrue == 0)
            {
                modifyProductNotificationF.setText("PART NOT SAVED: Price is not a double!");
            }

            invalidIsTrue = 1;
        }

        try
        {
            min = Integer.parseInt(String.valueOf(modifyProductMinTxt.getText()));
        }
        catch (NumberFormatException e)
        {
            if (invalidIsTrue == 0)
            {
                modifyProductNotificationF.setText("PART NOT SAVED: Min is not an integer!");
            }

            invalidIsTrue = 1;
        }

        try
        {
            max = Integer.parseInt(String.valueOf(modifyProductMaxTxt.getText()));
        }
        catch (NumberFormatException e)
        {
            if (invalidIsTrue == 0)
            {
                modifyProductNotificationF.setText("PART NOT SAVED: Max is not an integer!");
            }
            invalidIsTrue = 1;
        }


        if (min > max)
        {
            if (invalidIsTrue == 0)
            {
                modifyProductNotificationF.setText("PART NOT SAVED: Min is greater than Max!");
            }

            invalidIsTrue = 1;
        }

        if (stock > max)
        {
            if (invalidIsTrue == 0)
            {
                modifyProductNotificationF.setText("PART NOT SAVED: Inventory is greater than Max!");
            }

            invalidIsTrue = 1;
        }

        if (stock < min)
        {
            if (invalidIsTrue == 0)
            {
                modifyProductNotificationF.setText("PART NOT SAVED: Inventory is less than Min!");
            }

            invalidIsTrue = 1;
        }

        if (name.isEmpty())
        {
            if (invalidIsTrue == 0)
            {
                modifyProductNotificationF.setText("PART NOT SAVED: Name is Blank!");
            }
            invalidIsTrue = 1;
        }

        if (invalidIsTrue == 0)
        {
            Product tempProduct = new Product(passingProduct.getId(), name, price, stock, min, max);
            Inventory.updateProduct(passingIndex, tempProduct);
            saveSuccessful =1;

            for (int i = 0; i < Inventory.getAllProducts().size(); i++)
            {
                if (Inventory.getAllProducts().get(i).getId() == tempProduct.getId())
                {
                    for (int j = 0; j < modifyPartToProductOList.size(); j++)
                    {
                        Inventory.getAllProducts().get(i).addAssociatedPart(modifyPartToProductOList.get(j));
                    }
                    break;
                }
            }
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

    /**This method is called when the cancel button is pressed and upon user confirmation will return the stage to the main screen.*/
    @FXML
    void onActionModifyProductButtonCancel(ActionEvent event) throws IOException
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to close and return to the main screen? Data will not be saved.");
        Optional<ButtonType> results = alert.showAndWait();

        if (results.isPresent() && results.get() == ButtonType.OK)
        {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    /**This method is responsible for transferring information about the selected product on main screen to the modify product stage. Specifically it stores that info in a private variable, allowing other methods to access and modify as needed.*/
    public void setProductFromMainScreen (Product tempProduct)
    {
        passingProduct = tempProduct;

        modifyProductPriceTxt.setText(String.valueOf(tempProduct.getPrice()));
        modifyProductMinTxt.setText(String.valueOf(tempProduct.getMin()));
        modifyProductNameTxt.setText(String.valueOf(tempProduct.getName()));
        modifyProductInventoryTxt.setText(String.valueOf(tempProduct.getStock()));
        modifyProductMaxTxt.setText(String.valueOf(tempProduct.getMax()));
        modifyProductIdTxt.setText(String.valueOf(tempProduct.getId()));


        modifyPartToProductOList.addAll(passingProduct.getAllAssociatedParts());


        modifyProductAssocTable.setItems(modifyPartToProductOList);
    }




    /**The initialize method is responsible for preparing the table views for data entry and loads data into the parts table.*/
    public void initialize()
    {

        modifyProductPartTable.setItems(Inventory.getAllParts());
        modifyProductPartTableId.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyProductPartTableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProductPartTableInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProductPartTablePrice.setCellValueFactory(new PropertyValueFactory<>("price"));


        modifyProductAssocTableId.setCellValueFactory(new PropertyValueFactory<>("id"));
        modifyProductAssocTableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProductAssocTableInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProductAssocTablePrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }


    /**Unused*/
    @FXML
    void onActionModifyProductIdTxt(ActionEvent event){}
    /**Unused*/
    @FXML
    void onActionModifyProductNameTxt(ActionEvent event){}
    /**Unused*/
    @FXML
    void onActionModifyProductInventoryTxt(ActionEvent event){}
    /**Unused*/
    @FXML
    void onActionModifyProductPriceTxt(ActionEvent event){}
    /**Unused*/
    @FXML
    void onActionModifyProductMaxTxt(ActionEvent event){}
    /**Unused*/
    @FXML
    void onActionModifyProductMinTxt(ActionEvent event){}

}
