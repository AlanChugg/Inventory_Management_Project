package Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;


/**The Inventory Class directly manages the Parts and Products arrays and contains methods that can add, modify or delete objects in those arrays.*/
public class Inventory
{
    static private ArrayList<Part> listPart = new ArrayList<>();
    static private ObservableList<Part> allParts = FXCollections.observableList(listPart);
    static private ArrayList<Product> listProduct = new ArrayList<>();
    static private ObservableList<Product> allProducts = FXCollections.observableList(listProduct);


    /**This method adds a Part object to the end of the allParts array. @param newPart Adds the referenced object to the allParts array.*/
    public static void addPart(Part newPart)
    {
        allParts.add(newPart);
    }

    /**This method adds a Product object to the end of the allProducts array. @param newProduct Adds the referenced object to the allProduct array.*/
    public static void addProduct(Product newProduct)
    {
        allProducts.add(newProduct);
    }

    /**This method returns the allParts array. Used when another class needs to access the array directly.*/
    public static ObservableList<Part> getAllParts()
    {
        return allParts;
    }

    /**This method returns the allProducts array. Used when another class needs to access the array directly*/
    public static ObservableList<Product> getAllProducts()
    {
        return allProducts;
    }

    /**This method is used to look up a Part object that matches a partial string. Specifically that substring will be compared to the name associated with the object.
       @param partName This is the substring to look up.
       @return Will return a part array. */
    public static ObservableList <Part> lookupPart (String partName)
    {
        ObservableList <Part> namedParts = FXCollections.observableArrayList();
        ObservableList <Part> tempAllParts = Inventory.getAllParts();

        for (Part n : tempAllParts)
        {
            if (n.getName().contains(partName))
            {
                namedParts.add(n);
            }
        }

        return namedParts;
    }

    /**This method is used to look up a Part object that matches an ID. Specifically that integer will be compared to the ID associated with the object.
     @param partId This is the integer to look up.
     @return Will return a part if a match is found, null otherwise. */
    public static Part lookupPart(int partId)
    {
        ObservableList <Part> tempAllParts = Inventory.getAllParts();

        for (Part n : tempAllParts)
        {
            if (n.getId() == partId)
                return n;
        }
        return null;
    }


    /**This method is used to look up a Product that matches an ID. Specifically that integer will be compared to the ID associated with the object.
     @param productId This is the integer to look up.
     @return Will return a product if a match is found, null otherwise. */
    public static Product lookupProduct (int productId )
    {
        ObservableList <Product> tempAllProduct = Inventory.getAllProducts();

        for (Product n : tempAllProduct)
        {
            if (n.getId() == productId)
                return n;
        }
        return null;
    }


    /**This method is used to look up a Product that matches a partial string. Specifically that substring will be compared to the name associated with the object.
     @param productName This is the substring to look up.
     @return Will return a product array. */
    public static ObservableList<Product> lookupProduct (String productName)
    {
        ObservableList <Product> namedProduct = FXCollections.observableArrayList();
        ObservableList <Product> tempAllProduct = Inventory.getAllProducts();

        for (Product n : tempAllProduct)
        {
            if (n.getName().contains(productName))
            {
                namedProduct.add(n);
            }
        }

        return namedProduct;
    }


    /**This method is used to delete an item in the allProducts array. Specifically will delete the entry identical to the passed parameter.
       @param selectedProduct This is the object to delete.
       @return Will return true if a match is found, false otherwise.*/
    public static boolean deleteProduct(Product selectedProduct)
    {
        for (int i = 0; i < allProducts.size(); i++)
        {
            if (allProducts.get(i).getId() == selectedProduct.getId())
            {
                allProducts.remove(i);
                return true;
            }
        }
        return false;
    }


    /**This method is used to delete an item in the allParts array. Specifically will delete the entry identical to the passed parameter.
     @param selectedPart This is the object to delete.
     @return Will return true if a match is found, false otherwise.*/
    public static boolean deletePart(Part selectedPart)
    {
        for (int i = 0; i < allParts.size(); i++)
        {
            if (allParts.get(i).getId() == selectedPart.getId())
            {
                allParts.remove(i);
                return true;
            }
        }
        return false;
    }


    /**This method is used to update the allParts array with the corresponding parameter and index number.
        @param index This is the exact position of the object in the array to be updated
        @param selectedPart This parameter points to the object that will be replacing the current object in question.*/
    public static void updatePart(int index, Part selectedPart)
    {
        allParts.remove(index);
        addPart(selectedPart);
    }


    /**This method is used to update the allProducts array with the corresponding parameter and index number.
     @param index This is the exact position of the object in the array to be updated
     @param newProduct This parameter points to the object that will be replacing the current object in question.*/
    public static void updateProduct(int index, Product newProduct)
    {
        allProducts.remove(index);
        addProduct(newProduct);
    }

}

