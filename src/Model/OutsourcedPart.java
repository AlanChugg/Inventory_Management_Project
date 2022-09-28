package Model;


/**This class creates Outsourced parts and inherits from the abstract Part class.*/
public class OutsourcedPart extends Part{

    private String companyName;


    /**The full Outsourced Part constructor. This declared constructor takes the id, name, price, stock, min, max, and company name as parameters. Invokes the Part superclass constructor. */
    public OutsourcedPart(int id, String name, double price, int stock, int min, int max, String companyName )
    {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**This method can be used to change the company name of an Outsourced part.
       @param companyName Replaces the current company name with this parameter.*/
    public void setCompanyName( String companyName)
    {
        this.companyName = companyName;
    }

    /**This method returns the current company name of an Outsourced part.*/
    public String getCompanyName()
    {
        return this.companyName;
    }
}

