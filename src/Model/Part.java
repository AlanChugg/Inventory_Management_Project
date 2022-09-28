package Model;


/**This is the supplied class Part.java. No changes have been made.
  @author Alan Chugg
 */
public abstract class Part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;    
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /** This supplied method returns the part ID.
     @return the id
     */
    public int getId() {
        return id;
    }

    /**This supplied method sets the part ID.
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**This supplied method returns the part name.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**This supplied method sets the part name.
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**This supplied method returns the part price.
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**This supplied method sets the part price.
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**This supplied method returns the part stock.
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**This supplied method sets the part stock.
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**This supplied method returns the part min value.
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**This supplied method sets the part min value.
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**This supplied method returns the part max value.
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**This supplied method sets the part max value.
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }
    
}