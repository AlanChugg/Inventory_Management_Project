package View_Controller;

import Model.*;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


/**A completely optional class that is intended to make the inventory system function more like a real inventory system. This class includes methods that manage files, unique ids, and protects against empty array references.*/
public class OptionalFileManagement
{

    public static int mgmtIsOff = 0;
    public static ArrayList<Integer> uniqueIdList = new ArrayList<>();
    public static int backUpIndex = 0;
    public static String FILE_NAME = "parts.csv";
    public static String FILE_NAME2 = "products.csv";
    public static String FILE_NAME3 = "associatedparts.csv";


    /**This method protects against key empty array references by adding a demo entry to those arrays. It will also remove the demo entry if data is entered into the respective array.*/
    public static void emptyGuard ()
    {
        if ((Inventory.getAllParts()).isEmpty())
        {
            int id = 0;
            String name = "Demo";
            double price = 0.00;
            int stock = 0;
            int min = 0;
            int max = 0;
            String companyName = "demo";

            OutsourcedPart demoPart = new OutsourcedPart(id, name, price, stock, min, max, companyName);
            (Inventory.getAllParts()).add(demoPart);
        }

        if (Inventory.getAllParts().size() == 2)
        {
            for (int i = 0; i < Inventory.getAllParts().size(); i++)
            {
                if (Inventory.getAllParts().get(i).getId() == 0)
                {
                    Inventory.getAllParts().remove(i);
                    break;
                }
            }
        }

        if ((Inventory.getAllProducts()).isEmpty())
        {
            int id = 0;
            String name = "Demo";
            double price = 0.00;
            int stock = 0;
            int min = 0;
            int max = 0;

            Product demoProduct = new Product(id, name, price, stock, min, max);
            Inventory.getAllProducts().add(demoProduct);
        }

        if ((Inventory.getAllProducts()).size() == 2)
        {
            for (int i = 0; i < Inventory.getAllProducts().size(); i++)
            {
                if (Inventory.getAllProducts().get(i).getId() == 0)
                {
                    Inventory.getAllProducts().remove(i);
                    break;
                }
            }
        }

        if (Inventory.getAllProducts().get(0).getAllAssociatedParts().isEmpty())
        {
            int id = 0;
            String name = "Demo";
            double price = 0.00;
            int stock = 0;
            int min = 0;
            int max = 0;
            String companyName = "demo";

            OutsourcedPart demoPart = new OutsourcedPart(id, name, price, stock, min, max, companyName);
            Inventory.getAllProducts().get(0).addAssociatedPart(demoPart);
        }
        for (int i = 0; i < Inventory.getAllProducts().size(); i++)
        {
            if (Inventory.getAllProducts().get(i).getAllAssociatedParts().size() == 2)
            {
                for (int j = 0; j < Inventory.getAllProducts().get(i).getAllAssociatedParts().size(); j++)
                {
                    if (Inventory.getAllProducts().get(i).getAllAssociatedParts().get(j).getId() == 0)
                    {
                        Inventory.getAllProducts().get(i).getAllAssociatedParts().remove(j);
                        break;
                    }
                }
            }
        }

    }


    /**This method will load and or create csv files for data storage. If an exception occurs, this method will turn off file management entirely and load the arrays with default entries.*/
    public static void loadAllFiles()
    {
       try
        {
            // creates parts.csv file

            Path newFilePath = Paths.get(FILE_NAME);

            if (Files.exists(newFilePath))
            {
                System.out.println("file exists");
            }
            else
                {
                System.out.println("file does not exist");
                Files.createFile(newFilePath);
                    PrintWriter outputFile = new PrintWriter(FILE_NAME);

                    outputFile.print(1+",");
                    outputFile.print("TestPart01"+",");
                    outputFile.print(15.01+",");
                    outputFile.print(1+",");
                    outputFile.print(1+",");
                    outputFile.print(1 + ",");
                    outputFile.println(175);

                    outputFile.print(2+",");
                    outputFile.print("TestPart02"+",");
                    outputFile.print(25.01+",");
                    outputFile.print(1+",");
                    outputFile.print(1+",");
                    outputFile.print(1+",");
                    outputFile.println("TestCompany");


                    outputFile.print(3+",");
                    outputFile.print("TestPart03"+",");
                    outputFile.print(35.01+",");
                    outputFile.print(1+",");
                    outputFile.print(1+",");
                    outputFile.print(1+",");
                    outputFile.println("TestCompany");
                    outputFile.close();
                }

            // creates products.csv file

            Path newFilePath2 = Paths.get(FILE_NAME2);

            if (Files.exists(newFilePath2))
            {
                System.out.println("file2 exists");
            }
            else
                {
                System.out.println("file2 does not exist");
                Files.createFile(newFilePath2);
                    PrintWriter outputFile = new PrintWriter(FILE_NAME2);

                    outputFile.print(7+",");
                    outputFile.print("Product01"+",");
                    outputFile.print(15.01+",");
                    outputFile.print(1+",");
                    outputFile.print(1+",");
                    outputFile.println(1);


                    outputFile.print(8+",");
                    outputFile.print("Product02"+",");
                    outputFile.print(25.01+",");
                    outputFile.print(1+",");
                    outputFile.print(1+",");
                    outputFile.println(1);

                    outputFile.print(9+",");
                    outputFile.print("Product03"+",");
                    outputFile.print(35.01+",");
                    outputFile.print(1+",");
                    outputFile.print(1+",");
                    outputFile.println(1);

                    outputFile.close();

                }

            // creates associatedparts.csv file

            Path newFilePath3 = Paths.get(FILE_NAME3);

            if (Files.exists(newFilePath3))
            {
                System.out.println("file3 exists");
            }
            else
                {
                 System.out.println("file3 does not exist");
                 Files.createFile(newFilePath3);
                    PrintWriter outputFile = new PrintWriter(FILE_NAME3);

                    outputFile.print(7+",");
                    outputFile.print(4+",");
                    outputFile.print("TestAssocPart01"+",");
                    outputFile.print(15.01+",");
                    outputFile.print(1+",");
                    outputFile.print(1+",");
                    outputFile.print(1 + ",");
                    outputFile.println(175);

                    outputFile.print(8+",");
                    outputFile.print(5+",");
                    outputFile.print("TestAssocPart02"+",");
                    outputFile.print(25.01+",");
                    outputFile.print(1+",");
                    outputFile.print(1+",");
                    outputFile.print(1+",");
                    outputFile.println("TestCompany");


                    outputFile.print(9+",");
                    outputFile.print(6+",");
                    outputFile.print("TestAssocPart03"+",");
                    outputFile.print(35.01+",");
                    outputFile.print(1+",");
                    outputFile.print(1+",");
                    outputFile.print(1+",");
                    outputFile.println("TestCompany");
                    outputFile.close();
                }


// Reads Parts.csv file into part array
            File file = new File (FILE_NAME);
            Scanner scanFile = new Scanner (file);
            while (scanFile.hasNext())
            {
                String read = scanFile.nextLine();
                String delims = "[,]";
                String[] tokens = read.split(delims);
                int id = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                double price = Double.parseDouble(tokens[2]);
                int stock = Integer.parseInt(tokens[3]);
                int min = Integer.parseInt(tokens[4]);
                int max = Integer.parseInt(tokens[5]);

                try
                {
                    int mach = Integer.parseInt(tokens[6]);
                    InHousePart part = new InHousePart(id, name, price, stock, min, max, mach);
                    Inventory.addPart(part);


                }
                catch ( NumberFormatException e)
                {
                    String compName = tokens[6];
                    OutsourcedPart partOs = new OutsourcedPart(id, name, price, stock, min, max, compName);
                    Inventory.addPart(partOs);

                }
            }

            scanFile.close();

// Reads products.csv file into part array
            File file2 = new File (FILE_NAME2);
            Scanner scanFile2 = new Scanner (file2);
            while (scanFile2.hasNext())
            {
                String read = scanFile2.nextLine();
                String delims = "[,]";
                String[] tokens = read.split(delims);
                int id = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                double price = Double.parseDouble(tokens[2]);
                int stock = Integer.parseInt(tokens[3]);
                int min = Integer.parseInt(tokens[4]);
                int max = Integer.parseInt(tokens[5]);

                Product prod = new Product(id,name, price, stock, min, max);
                Inventory.addProduct(prod);
            }

            scanFile.close();

// Reads associated Parts into respective array.
            File file3 = new File (FILE_NAME3);
            Scanner scanFile3 = new Scanner (file3);
            while (scanFile3.hasNext())
            {
                String read = scanFile3.nextLine();
                String delims = "[,]";
                String[] tokens = read.split(delims);
                int associatedId = Integer.parseInt(tokens[0]);
                int id = Integer.parseInt(tokens[1]);
                String name = tokens[2];
                double price = Double.parseDouble(tokens[3]);
                int stock = Integer.parseInt(tokens[4]);
                int min = Integer.parseInt(tokens[5]);
                int max = Integer.parseInt(tokens[6]);

                try
                {
                    int mach = Integer.parseInt(tokens[7]);
                    for (int i = 0; i < Inventory.getAllProducts().size(); i++)
                    {
                        if (Inventory.getAllProducts().get(i).getId() == associatedId)
                        {
                            InHousePart part = new InHousePart(id, name, price, stock, min, max, mach);
                            Inventory.getAllProducts().get(i).addAssociatedPart(part);

                        }
                    }
                }
                catch ( NumberFormatException e)
                {
                    String compName = tokens[7];
                    for (int i = 0; i < Inventory.getAllProducts().size(); i++)
                    {
                        if (Inventory.getAllProducts().get(i).getId() == associatedId)
                        {
                            OutsourcedPart part = new OutsourcedPart(id, name, price, stock, min, max, compName);
                            Inventory.getAllProducts().get(i).addAssociatedPart(part);
                        }
                    }
                }
            }

        }

        catch(Exception e)
        {
            mgmtIsOff = 1;

            if(Inventory.getAllParts().isEmpty() == false) {Inventory.getAllParts().clear();}
            if(Inventory.getAllProducts().isEmpty() == false) {Inventory.getAllProducts().clear();}
            if(Inventory.getAllProducts().get(0).getAllAssociatedParts().isEmpty() == false){Inventory.getAllProducts().get(0).getAllAssociatedParts().clear();}

            for (int i =1; i < 4; i++)
            {
               int index = i-1;
               int idPart = i+3;
               int idAssoc = i+6;
               String name = "test";
               double price = 1.01;
               int stock = 1;
               int min = 1;
               int max = 1;
               String compName = "testCompany";
               Product tempProd = new Product(i, name,price,stock,min,max);
               Inventory.getAllProducts().add(tempProd);
               OutsourcedPart tempPart = new OutsourcedPart(idPart,name,price,stock,min,max, compName);
               Inventory.getAllParts().add(tempPart);
               OutsourcedPart tempAssoc = new OutsourcedPart(idAssoc,name,price,stock,min,max, compName);
               Inventory.getAllProducts().get(index).addAssociatedPart(tempAssoc);
            }
        }
    }



    /**This method works in tandem with generateUniqueId to create unique ids for the program. This method takes in account entries loaded from database files as well as entries that have been deleted.*/
    public static void refreshUniqueIdList() {

        ArrayList <Integer> tempList = new ArrayList<>();

        for (int i = 0; i < (Inventory.getAllParts()).size(); i++)
        {
            tempList.add(Inventory.getAllParts().get(i).getId());
        }

        for (int i = 0; i < (Inventory.getAllProducts().size()); i++)
        {
            tempList.add(Inventory.getAllProducts().get(i).getId());

            for (int j = 0; j < Inventory.getAllProducts().get(i).getAllAssociatedParts().size(); j++)
            {
                tempList.add(Inventory.getAllProducts().get(i).getAllAssociatedParts().get(j).getId());
            }
        }

        if(tempList.isEmpty()){tempList.add(0);}

        Collections.sort(tempList);

        int j =0;
        int i = 0;

        // This loop adds numbers not present in tempList to the unique id list.
        while ( i < tempList.size())
        {
            if(tempList.get(i) == j)
            {
                i++;
                j++;
            }
            else if (j>tempList.get(i))
            {
                i++;
            }
            else
                {
                    while(j<tempList.get(i))
                    {
                        uniqueIdList.add(j);
                        j++;
                    }
                }
        }

        int tempListHighest = tempList.get(tempList.size() - 1);
        while (uniqueIdList.size() < 3)
        {
            uniqueIdList.add(++tempListHighest);
        }
    }


/**This method works in tandem with refereshUniqueIdList to generate unique ids for item entries. Will not generate a 0 entry as that is reserved for emptyguard.*/
    public static int generateNewIndex()
    {
        if (uniqueIdList.isEmpty() && mgmtIsOff ==0)
        {
            refreshUniqueIdList();
        }

        if (mgmtIsOff == 0)
        {
                if (uniqueIdList.get(0) == 0){uniqueIdList.remove(0);}
                int tempVar = uniqueIdList.get(0);
                uniqueIdList.remove(0);
                return tempVar;
        }
        else{return ++backUpIndex;}
    }

    /**This method works in tandem with saveAllFiles to save the array lists in Inventory as well as the associated parts information to the csv database files.*/
    public static void saveAllFilesBody() {
        try
        {


                Path newFilePath = Paths.get(FILE_NAME);

                if (Files.exists(newFilePath))
                {
                    PrintWriter outputFile = new PrintWriter(FILE_NAME);

                    for(Part n : Inventory.getAllParts()) {
                        outputFile.print(n.getId() + ",");
                        outputFile.print(n.getName() + ",");
                        outputFile.print(n.getPrice() + ",");
                        outputFile.print(n.getStock() + ",");
                        outputFile.print(n.getMin() + ",");
                        outputFile.print(n.getMax() + ",");
                        if (n instanceof InHousePart) {
                            outputFile.println(((InHousePart) n).getMachineID());
                        }
                        if (n instanceof OutsourcedPart) {
                            outputFile.println(((OutsourcedPart) n).getCompanyName());
                        }
                    }

                     outputFile.close();
                }


            Path newFilePath2 = Paths.get(FILE_NAME2);

            if (Files.exists(newFilePath2))
            {

                PrintWriter outputFile = new PrintWriter(FILE_NAME2);

                for (Product n : Inventory.getAllProducts())
                {
                    outputFile.print(n.getId() + ",");
                    outputFile.print(n.getName() + ",");
                    outputFile.print(n.getPrice() + ",");
                    outputFile.print(n.getStock() + ",");
                    outputFile.print(n.getMin() + ",");
                    outputFile.println(n.getMax());
                }
                outputFile.close();
            }


            Path newFilePath3 = Paths.get(FILE_NAME3);

            if (Files.exists(newFilePath3))
            {
                PrintWriter outputFile = new PrintWriter(FILE_NAME3);

                for (int i = 0; i < Inventory.getAllProducts().size(); i++)
                {
                    for (int j=0; j < Inventory.getAllProducts().get(i).getAllAssociatedParts().size(); j++)
                    {
                        outputFile.print(Inventory.getAllProducts().get(i).getId() + ",");
                        outputFile.print(Inventory.getAllProducts().get(i).getAllAssociatedParts().get(j).getId() + ",");
                        outputFile.print(Inventory.getAllProducts().get(i).getAllAssociatedParts().get(j).getName() + ",");
                        outputFile.print(Inventory.getAllProducts().get(i).getAllAssociatedParts().get(j).getPrice() + ",");
                        outputFile.print(Inventory.getAllProducts().get(i).getAllAssociatedParts().get(j).getStock() + ",");
                        outputFile.print(Inventory.getAllProducts().get(i).getAllAssociatedParts().get(j).getMin() + ",");
                        outputFile.print(Inventory.getAllProducts().get(i).getAllAssociatedParts().get(j).getMax() + ",");

                        if (Inventory.getAllProducts().get(i).getAllAssociatedParts().get(j) instanceof InHousePart) {
                            outputFile.println(((InHousePart) Inventory.getAllProducts().get(i).getAllAssociatedParts().get(j)).getMachineID());
                        }
                        if (Inventory.getAllProducts().get(i).getAllAssociatedParts().get(j) instanceof OutsourcedPart) {
                            outputFile.println(((OutsourcedPart) Inventory.getAllProducts().get(i).getAllAssociatedParts().get(j)).getCompanyName());
                        }

                    }
                }

                outputFile.close();
            }
        }
        catch (Exception e)
        {
            //Do nothing it just wont save.
        }

    }


    /**This is the method that is called when main screen attempts to save data to the csv files. Will not save if there was an exception thrown while loading the csv files.*/
    public static void saveAllFiles()
    {
        if (mgmtIsOff == 0)
        {
            saveAllFilesBody();
        }
    }




}
