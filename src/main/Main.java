package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

/**
 * This class is the Main class, which extends the <code>javaFX Application</code> class.
 * It has two methods: main and start.
 * <p>
 * FUTURE ENHANCEMENT to the company name text fields: A drop-down list of company names already in the system and
 * an add button redirecting the user to another form to add a new company to the system (name, phone number, address, etc.).
 * <p>
 *
 * @author Myriam Drouin-Sagar
 */
public class Main extends Application {

    /**The start method will be the first thing to run in this class, and in the whole program.
     * <p>
     * It implements the primaryStage and loads the first screen, which is the mainForm.
     * <p>
     * This method overrides the start method in the javaFX Application class.
     *
     * @param primaryStage set the stage for the program
     * @throws Exception tells the program that exceptions might cause this method to suddenly exit.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        primaryStage.setTitle("Main Form");
        primaryStage.setScene(new Scene(root,1400, 675 ));
        primaryStage.show();
    }


    /**The main method is where the program begins, and allows to run the class from an IDE.
     * <p>
     * I provided data to create new In-House and Outsourced objects of type Part, and new instance of Product.
     * I also associated parts to the products.
     * <p>
     * This method calls the launch method, which is a method defined in the javaFX Application class to launch the application.
     * <p>
     * JavaDocs directory: C482\JavaDoc
     * @param args meaning arguments to launch the program
     */
    public static void main(String[] args){
        InHouse part1 = new InHouse(Inventory.getNextPartID(), "Monitor", 99.99, 5, 1, 20, 111);
        InHouse part2 = new InHouse(Inventory.getNextPartID(), "Keyboard", 34.99, 10, 1, 20, 111);

        InHouse part3 = new InHouse(Inventory.getNextPartID(), "SheetFeeder", 4.99, 10, 1, 20, 222);
        InHouse part4 = new InHouse(Inventory.getNextPartID(), "PowerCord", 9.99, 5, 1, 20, 222);

        Outsourced part5 = new Outsourced(Inventory.getNextPartID(), "Amplifier", 29.99, 2, 1, 20, "Sony");
        Outsourced part6 = new Outsourced(Inventory.getNextPartID(), "Speaker", 50.99, 5, 1, 20, "ElectroSound");

        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);
        Inventory.addPart(part5);
        Inventory.addPart(part6);

        Product A = new Product(Inventory.getNextProductID(), "Computer", 198.99, 3, 1,10);
        Product B = new Product(Inventory.getNextProductID(), "Speaker System", 69.99, 3, 1, 10);
        Product C = new Product(Inventory.getNextProductID(), "Printer", 150.99, 8, 1, 10);

        Inventory.addProduct(A);
        Inventory.addProduct(B);
        Inventory.addProduct(C);
        A.addAssociatedPart(part1);
        A.addAssociatedPart(part2);
        B.addAssociatedPart(part5);
        B.addAssociatedPart(part6);
        C.addAssociatedPart(part3);
        C.addAssociatedPart(part4);


        launch(args);
    }


}
