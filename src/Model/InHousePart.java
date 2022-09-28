package Model;


/**This class creates In House parts and inherits from the abstract Part class.*/
public class InHousePart extends Part {

    private int machineID;


    /**The full In House Part constructor. This declared constructor takes the id, name, price, stock, min, max, and machine id as parameters. Invokes the Part superclass constructor. */
    public InHousePart(int id, String name, double price, int stock, int min, int max, int machineID )
    {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }

    /**This method can be used to change the machine ID of an In House Part.
       @param machineID Replaces the current machine ID with this parameter.*/
    public void setMachineID( int machineID)
    {
        this.machineID = machineID;
    }

    /**This method returns the current machine ID of an In House Part.*/
    public int getMachineID()
    {
        return this.machineID;
    }

}


