package Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;


/**This class maintains the list of associated parts and contains the corresponding methods.*/
public class Product {

    private ArrayList<Part> listAssociatedPart = new ArrayList<>();
    private ObservableList<Part> associatedParts = FXCollections.observableList(listAssociatedPart);
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;


    /** This is the full constructor for the Part class.*/
    public Product(int id, String name, double price, int stock, int min, int max)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }


    /** This method sets the ID of a respective associated part.
      @param id the id to set.
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /** This method sets the name of a respective associated part.
      @param name the name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /** This method sets the price of a respective associated part.
      @param price the price to set.
     */
    public void setPrice(double price)
    {
        this.price = price;
    }

    /** This method sets the inventory level of a respective associated part.
      @param stock the stock to set.
     */
    public void setStock(int stock)
    {
        this.stock = stock;
    }

    /** This method sets the minimum value of a respective associated part.
      @param min the min to set.
     */
    public void setMin(int min)
    {
        this.min = min;
    }

    /** This method sets the maximum value of a respective associated part.
      @param max the max to set.
     */
    public void setMax(int max)
    {
        this.max = max;
    }

    /** This is the getter method for the ID of a respective associated part.
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /** This is the getter method for the name of a respective associated part.
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /** This is the getter method for the price of a respective associated part.
     * @return the price
     */
    public double getPrice()
    {
        return price;
    }

    /** This is the getter method for the inventory level of a respective associated part.
     * @return the stock
     */
    public int getStock()
    {
        return stock;
    }

    /** This is the getter method for the minimum value of a respective associated part.
     * @return the min
     */
    public int getMin()
    {
        return min;
    }

    /** This is the getter method for the maximum value of a respective associated part.
     * @return the max
     */
    public int getMax()
    {
        return max;
    }

    /**This method adds a Part object to the associated part array.
       @param part This is the object to be added to the list.*/
    public void addAssociatedPart(Part part)
    {
        associatedParts.add(part);
    }

    /**This method returns the associated part list.
       @return Returns the associated part array.*/
    public ObservableList<Part> getAllAssociatedParts()
    {
        return associatedParts;
    }

    /**This method is used to delete an item from the associated parts list
       @param selectedAssociatedPart This is the part to be deleted from the list
       @return Returns true if the part is removed from the list, false otherwise.*/
    public boolean deleteAssociatedPart(Part selectedAssociatedPart)
    {
        for (int i = 0; i < associatedParts.size(); i++)
        {
            if (selectedAssociatedPart.getId() == associatedParts.get(i).getId())
            {
                associatedParts.remove(i);
                return true;
            }
        }
        return false;
    }


}
