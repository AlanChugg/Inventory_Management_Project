package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;


/**The main screen controller class is responsible for the main screen fxml file. In addition this class contains methods associated with the main screen interface.*/
public class MainScreen
{

private Stage stage;
private ObservableList<Part> mainScreenPartList = Inventory.getAllParts();
private ObservableList<Product> mainScreenProductList = Inventory.getAllProducts();



    @FXML
    private TextField mainScreenProductSearch;
    @FXML
    private TextField mainScreenPartsSearch;



    @FXML
    private TableView<Product> mainProductsTable;
    @FXML
    private TableColumn<Product, Integer> tableProductsProductID;
    @FXML
    private TableColumn<Product, String> tableProductsProductName;
    @FXML
    private TableColumn<Product, Integer> tableProductsInventoryLevel;
    @FXML
    private TableColumn<Product, Double> tableProductsPriceUnit;



    @FXML
    private TableView<Part> mainPartsTable;
    @FXML
    private TableColumn<Part, Integer> tablePartsPartID;
    @FXML
    private TableColumn<Part, String> tablePartsPartName;
    @FXML
    private TableColumn<Part, Integer> tablePartsInventoryLevel;
    @FXML
    private TableColumn<Part, Double> tablePartsPriceUnit;


    /**This method is called when the add part button is pressed and launches the add part stage.*/
    @FXML
    void onActionMainButtonAddPart(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/addPart.fxml"));
        Parent root = loader.load();
        stage = new Stage();
        stage.setScene( new Scene(root));
        stage.show();
    }


/**This method is called when the modify part button is pressed and launches the modify part stage.*/
    @FXML
    void onActionMainButtonModifyPart(ActionEvent event) throws IOException
    {
        if (mainPartsTable.getSelectionModel().getSelectedItem() != null)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/modifyPart.fxml"));
            Parent root = loader.load();
            ModifyPart mp = loader.getController();

            Part selectedPart = mainPartsTable.getSelectionModel().getSelectedItem();
            mp.setPartFromMainScreen(selectedPart);

            stage = new Stage();
            stage.setScene( new Scene(root));
            stage.show();
        }
        else
        {
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Select A Part");
            alert2.setContentText("Please Select A Part.");
            alert2.showAndWait();
        }
    }


/**This method is called when the delete part button is pressed. Upon user confirmation this method deletes the selected part.*/
    @FXML
    void onActionMainButtonDeletePart(ActionEvent event)
    {

        if (mainPartsTable.getSelectionModel().getSelectedItem() != null)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected part?");
            Optional<ButtonType> results = alert.showAndWait();

            if (results.isPresent() && results.get() == ButtonType.OK)
            {
                boolean didItWork = Inventory.deletePart(mainPartsTable.getSelectionModel().getSelectedItem());
                OptionalFileManagement.emptyGuard();

                if (didItWork)
                {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setContentText("Part Deleted Successfully.");
                    alert2.setTitle("Success!");
                    alert2.showAndWait();
                }
                if (didItWork == false)
                {
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setTitle("Failed to Delete Part.");
                    alert2.setContentText("ERROR!");
                    alert2.showAndWait();
                }
            }
        }
        else
        {
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Select A Part");
            alert2.setContentText("Please Select A Part.");
            alert2.showAndWait();
        }
        mainPartsTable.setItems(mainScreenPartList);
    }


    /**This method is called when the add product button is pressed and launches the add product stage.*/
    @FXML
    void onActionMainButtonAddProduct(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/addProduct.fxml"));
        Parent root = loader.load();
        stage = new Stage();
        stage.setScene( new Scene(root));
        stage.show();
    }


    /**This method is called when the modify product button is pressed and launches the modify product stage.*/
    @FXML
    void onActionMainButtonModifyProduct(ActionEvent event) throws IOException
    {
        if (mainProductsTable.getSelectionModel().getSelectedItem() != null)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View_Controller/modifyProduct.fxml"));
            Parent root = loader.load();
            ModifyProduct mp = loader.getController();

            Product selectedProduct = mainProductsTable.getSelectionModel().getSelectedItem();
            mp.setProductFromMainScreen(selectedProduct);

            stage = new Stage();
            stage.setScene( new Scene(root));
            stage.show();
        }
        else
        {
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Select A Product");
            alert2.setContentText("Please Select A Product.");
            alert2.showAndWait();
        }
    }


    /**This method is called when the delete product button is pressed. Upon user confirmation this method deletes the selected product.*/
    @FXML
    void onActionMainButtonDeleteProduct(ActionEvent event)
    {

        if (mainProductsTable.getSelectionModel().getSelectedItem() != null)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the selected product?");
            Optional<ButtonType> results = alert.showAndWait();

            if (results.isPresent() && results.get() == ButtonType.OK)
            {
                if (mainProductsTable.getSelectionModel().getSelectedItem().getAllAssociatedParts().isEmpty()
                        || mainProductsTable.getSelectionModel().getSelectedItem().getAllAssociatedParts().get(0).getId() == 0 )
                {
                    boolean didItWork = Inventory.deleteProduct(mainProductsTable.getSelectionModel().getSelectedItem());
                    OptionalFileManagement.emptyGuard();

                    if (didItWork)
                    {
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setContentText("Product Deleted Successfully.");
                        alert2.setTitle("Success!");
                        alert2.showAndWait();
                    }
                    if (didItWork == false)
                    {
                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Failed to Delete Product.");
                        alert2.setContentText("ERROR!");
                        alert2.showAndWait();
                    }

                }
                else
                    {
                        Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                        alert3.setTitle("Failed to Delete Product.");
                        alert3.setContentText("You Cannot Delete A Product That Has Associated Parts.");
                        alert3.showAndWait();
                    }
            }
        }
        else
            {
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Select A Product");
                alert2.setContentText("Please Select A Product.");
                alert2.showAndWait();
            }

        mainProductsTable.setItems(mainScreenProductList);
    }


/**This method is called when the exit button is pressed. Specifically this will give the user the option to save work through the saveAllFiles method and then exits the program.*/
    @FXML
    void onActionMainExit(ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do You Want To Save Your Work?");
        Optional<ButtonType> results = alert.showAndWait();

        if (results.isPresent() && results.get() == ButtonType.OK)
        {
            if(OptionalFileManagement.mgmtIsOff ==0)
            {
                OptionalFileManagement.saveAllFiles();
            }
            else
                {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Save Disabled!");
                    alert2.setContentText("Saving Has Been Automatically Disabled.");
                    alert2.showAndWait();
                }
        }
        System.exit(0);
    }

    /**This method allows the user to search the allParts array using the search bar for parts. Specifically it will parse the respective array and update the table view with the results.*/
    @FXML
    void onActionMainScreenPartsSearch(ActionEvent event)
    {
        String query = mainScreenPartsSearch.getText();
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
        mainPartsTable.setItems(partSearchResult);

    }

    /**This method allows the user to search the allProducts array using the search bar for products. Specifically it will parse the respective array and update the table view with the results.*/
    @FXML
    void onActionMainScreenProductSearch(ActionEvent event)
    {
        String query = mainScreenProductSearch.getText();
        ObservableList<Product> productSearchResult = Inventory.lookupProduct(query);
        int resultIsEmpty =0;

        if (productSearchResult.isEmpty())
        {
            resultIsEmpty =1;

            try
            {
                int whatIfId = Integer.parseInt(query);
                Product tempProduct = Inventory.lookupProduct(whatIfId);
                if (tempProduct != null)
                {
                    productSearchResult.add(tempProduct);
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

        mainProductsTable.setItems(productSearchResult);
    }


/**The initialize method prepares the tableviews for data entry and sets the initial data.*/
    public void initialize()
    {


    mainPartsTable.setItems(mainScreenPartList);
    tablePartsPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
    tablePartsPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
    tablePartsInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
    tablePartsPriceUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

    mainProductsTable.setItems(mainScreenProductList);
    tableProductsProductID.setCellValueFactory(new PropertyValueFactory<>("id"));
    tableProductsProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
    tableProductsInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
    tableProductsPriceUnit.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

}
