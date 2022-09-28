package View_Controller;

import com.sun.javafx.runtime.VersionInfo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**FUTURE ENHANCEMENT It would be really helpful for the end users if the program had an additional button that launched an update inventory or stock screen. On this
 screen it would accept a name or id and the current inventory level. The program would then search the appropriate list, find the corresponding object, and then update is stock. If a
 match is found, the form should then automatically clear its text fields. This way the user can quickly update multiple items without needing to clear each text field between entries.*/
public class Main extends Application
{


    /**The JavaDoc files and index.html are located in /InventoryMgmtSystem_AlanChugg_v1/JavaDoc.*/
    public static void main(String[] args) { launch(args); }


    /**This is the default start method which loads any database csv files and launches main screen.*/
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        OptionalFileManagement.loadAllFiles();
//VersionInfo.getRuntimeVersion();





//DO NOT DELETE THIS!
        Parent root = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        primaryStage.setTitle("System");
        primaryStage.setScene(new Scene(root, 1000, 400));
        primaryStage.show();
    }
}


