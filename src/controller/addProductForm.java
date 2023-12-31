package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This controller class allows to add Product object to the ObservableList allProducts of the <code>Inventory</code> class
 * and to add or delete associated part(s) to a product.
 * <p>
 * The <code>addProductForm</code> class implements the <code>Initializable</code> interface.
 * Provides control to the flow of data into objects and updates the addProductForm FXML file.
 *
 *
 *
 * @author Myriam Drouin-Sagar
 */
public class addProductForm implements Initializable {

    /**
     * The list that contains all the associated part(s) to a product
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * The product ID text field
     */
    public TextField addProductIDText;

    /**
     * The product name text field
     */
    public TextField addProductNameText;

    /**
     * The product inventory level text field
     */
    public TextField addProductInvText;

    /**
     * The product price text field
     */
    public TextField addProductPriceText;

    /**
     * The product maximum stock text field
     */
    public TextField addProductMaxText;

    /**
     * The product minimum stock text field
     */
    public TextField addProductMinText;

    /**
     * The table view displaying the parts
     */
    public TableView<Part> partsPane2;

    /**
     * The id column for the part's table view
     */
    public TableColumn<Part, Integer> partID;

    /**
     * The name column for the part's table view
     */
    public TableColumn<Part, String> partName;

    /**
     * The inventory level column for the part's table view
     */
    public TableColumn<Part, Integer> partInventoryLevel;

    /**
     * The price column for the part's table view
     */
    public TableColumn<Part, Double> partPricePerUnit;

    /**
     * The table view displaying the associated parts to a product
     */
    public TableView<Part> AssociatedPartsPane;

    /**
     * The id column for the associated part's table view
     */
    public TableColumn<Part, Integer> assocPartID;

    /**
     * The name column for the associated part's table view
     */
    public TableColumn<Part, String> assocPartName;
    /**
     * The inventory level for the associated part's table view
     */
    public TableColumn<Part, Integer> assocPartInventoryLevel;

    /**
     * The price for the associated part's table view
     */
    public TableColumn<Part, Double> assocPartPricePerUnit;

    /**
     * The part's table view text field to search for a part
     */
    public TextField searchPartText;

    /**
     * The button to initiate a part search
     */
    public Button searchPartButton;

    /**
     * The button to save the product's information typed in the <code>addProductForm</code> and return to the <code>mainForm</code>
     */
    public Button saveProductButton;

    /**
     * The button to cancel the <code>addProductForm</code> and return to the <code>mainForm</code>
     */
    public Button cancelAddProductButton;

    /**
     * The button to remove an associated part from the associated parts' table view AssociatedPartsPane
     */
    public Button removeAssocPartButton;

    /**
     * The button to add a part to the associated part's table view AssociatedPartsPane
     */
    public Button addAssocPartButton;

    /**Searches a part by id first, and then by partial or full name.
     * <p>
     * Gets the value from the searchPartText text field.
     * This method catches a <code>NumberFormatException</code>: if a string is found, the method will search for the partial name.
     * If the text field searchPartText is empty, the table view is repopulated with all the parts.
     * <p>
     * If a part is found by id, the part object is highlighted in the table view.
     * If a part is found by name, the part object is filtered in the table view.
     * If not part is found an information message is displayed.
     *
     * @param actionEvent The event generated by the Search button searchPartButton clicked by the user or passed from the keyEvent (never used)
     */
    public void onSearchPart(ActionEvent actionEvent) {
        try {
            if (searchPartText.getText().isEmpty()){
                partsPane2.setItems(Inventory.getAllParts());
                return;
            }
            Part part = Inventory.lookupPart(Integer.parseInt(searchPartText.getText()));
            if(part == null){
                throw new NumberFormatException();
            }
            partsPane2.getSelectionModel().select(part);

        }
        catch (NumberFormatException e ) {
            String sp = searchPartText.getText();
            ObservableList<Part> partsNameFound = Inventory.lookupPart(sp);
            if(partsNameFound == null){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Part not Found");
                alert.showAndWait();
                searchPartText.clear();
                return;
            }
            partsPane2.setItems(partsNameFound);
        }
    }

    /**Calls the <code>onSearchPart</code> action event.
     * This method allows the user to initiate the search with the keyboard (ENTER)
     * and to empty the parts' table view search text field (backSpace).
     *
     * @param keyEvent The event generated when the user press the ENTER button on the keyboard and the focus is on the Search text field searchPartText
     */
    public void onSearchPartText(KeyEvent keyEvent) {
        if(searchPartText.getText().isEmpty()){
            partsPane2.setItems(Inventory.getAllParts());
            partsPane2.getSelectionModel().clearSelection();
        }
        if(keyEvent.getCode() == KeyCode.ENTER){
            onSearchPart(new ActionEvent());
        }
    }

    /**Associates a part with the new product.
     * It adds the part selected by the user in the table view partsPane3> to the
     * table view AssociatedPartsPane.
     * <p>
     * This is done by calling the <code>add</code> method to append the part at the end of the ObservableList associatedParts.
     * <p>
     * If no part is selected, an error message is displayed.
     * @param actionEvent The event generated by the Add button addAssocPartButton when clicked by the user (never used)
     */
    public void onAddAssocPart(ActionEvent actionEvent) {
        Part selectedPart = partsPane2.getSelectionModel().getSelectedItem();
        if (selectedPart != null){
            associatedParts.add(selectedPart);
            partsPane2.getSelectionModel().clearSelection();
        }
        else {
            errorDisplay(6);
        }
    }

    /** Dissociates a part from the new product.
     * It removes the associated part selected by the user in the table view AssociatedPartsPane.
     * <p>
     * This is done by calling the <code>remove</code> method to remove the part from the ObservableList associatedParts.
     * <p>
     * If no part is selected, an error message is displayed.
     * @param actionEvent The event generated by the Remove Associated Part button removeAssocPartButton when clicked by the user (never used)
     */
    public void onRemoveAssocPart(ActionEvent actionEvent) {
        Part selectedPart = AssociatedPartsPane.getSelectionModel().getSelectedItem();

        if(selectedPart != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to dissociate " + selectedPart.getName().toUpperCase() + " from this product?");
            Optional<ButtonType> showResult = alert.showAndWait();

            if(showResult.isPresent() && showResult.get() == ButtonType.OK) {
                associatedParts.remove(selectedPart);
            }
        }
        else {
            errorDisplay(5);
        }
    }

    /**Cancels the <code>addProductForm</code> and returns to the <code>mainForm</code>.
     * <p>
     * It displays a confirmation dialog box.
     * If confirmed, a call to the function <code>returnToMainScreen</code> will redirect the user to the <code>mainForm</code>.
     *
     * @param actionEvent The event generated by the Cancel button cancelAddPartButton when clicked by the user
     * @throws IOException from the FXMLLoader in the method <code>returnToMainScreen</code>
     */
    public void onCancelAddProduct(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel this product and return to the Main Form?");
        Optional<ButtonType> showResult = alert.showAndWait();
        if(showResult.isPresent() && showResult.get() == ButtonType.OK){
            returnToMainScreen(actionEvent);
        }
    }

    /**Gets and saves a new <code>Product</code> and its values entered by the user in the <code>addProductForm</code> and loads the <code>mainForm</code>.
     * <p>
     * It adds the new <code>Product</code> object to the ObservableList allProducts by calling the <code>addProduct</code> method of the <code>Inventory</code> class.
     * It also adds associated part(s) to the ObservableList associateParts by calling the <code>addAssociatedPart</code> method of the <code>Product</code> class.
     * <p>
     * A call to the <code>minMaxValid</code> method and <code>invValid</code> method is also made, and error messages are displayed if min or inv is not valid.
     * An error message is also displayed if the name field is empty.
     * <p>
     * <code>NumberFormatException</code> to catch invalid values (string in an int text field) or an empty text field.
     * If caught, an error message is displayed.
     *
     * @param actionEvent The event generated by the Save button saveAddProductButtonwhen clicked by the user
     * @throws IOException from the <code>FXMLoader</code> in the method <code>returnToMainScreen</code>
     */
    public void onSaveProduct(ActionEvent actionEvent) throws IOException {
        try {
            if (!minMaxValid(Integer.parseInt(addProductMinText.getText()), Integer.parseInt(addProductMaxText.getText()))) {
                errorDisplay(1);

                return;
            }
            if(!invValid(Integer.parseInt(addProductMinText.getText()), Integer.parseInt(addProductMaxText.getText()),Integer.parseInt(addProductInvText.getText()))){
                errorDisplay(2);
                return;
            }

            String name = addProductNameText.getText();
            int stock = Integer.parseInt(addProductInvText.getText());
            double price = Double.parseDouble(addProductPriceText.getText());
            int min = Integer.parseInt(addProductMinText.getText());
            int max = Integer.parseInt(addProductMaxText.getText());
            Product newProduct = new Product(Inventory.getNextProductID(), name, price, stock, min, max);

            if (name.isEmpty()){
                errorDisplay(4);
                return;
            }
            for (Part part : associatedParts) {
                newProduct.addAssociatedPart(part);
            }

            Inventory.addProduct(newProduct);
            returnToMainScreen(actionEvent);

        }catch(NumberFormatException e){
            errorDisplay(3);
        }
    }

    /** Defines multiple error messages for the <code>addProductForm</code>.
     *
     * @param caseNumber The case associated with the error message to be displayed (passed from a parent method)
     */
    private void errorDisplay(int caseNumber) {
        Alert alertError = new Alert(Alert.AlertType.ERROR);

        switch (caseNumber) {
            case 1:
                alertError.setTitle("Error");
                alertError.setHeaderText("Invalid Inventory.");
                alertError.setContentText("Min must be greater than 0 and less than Max.");
                alertError.showAndWait();
                break;
            case 2:
                alertError.setTitle("Error");
                alertError.setHeaderText("Invalid Inventory");
                alertError.setContentText("Inventory must be between Min and Max");
                alertError.showAndWait();
                break;
            case 3:
                alertError.setTitle("Error");
                alertError.setHeaderText("Invalid Values.");
                alertError.setContentText("Please enter valid values in the text fields.");
                alertError.showAndWait();
                break;
            case 4:
                alertError.setTitle("Error");
                alertError.setHeaderText("Name Field is Empty.");
                alertError.setContentText("Name field cannot be empty. Please enter a valid Name for the Product.");
                alertError.showAndWait();
                break;
            case 5:
                alertError.setTitle("Error");
                alertError.setHeaderText("No Part Associated Part Selected.");
                alertError.setContentText("Please select a Part to dissociate from the Product.");
                alertError.showAndWait();
                break;
            case 6:
                alertError.setTitle("Error");
                alertError.setHeaderText("No Part Selected");
                alertError.setContentText("Please select a part to associate with the product.");
                alertError.showAndWait();
                break;

        }
    }

    /**Verifies if the min is valid.
     * Valid min is less than max and greater than 0.
     *
     * @param min the min value for the product to add
     * @param max the max value for the product to add
     * @return <code>true</code> if valid, and <code>false</code> if not valid
     */
    private boolean minMaxValid(int min, int max){
        boolean minMaxIsValid = true;
        if((min <= 0) || (min >= max)) {
            minMaxIsValid = false;
        }
        return minMaxIsValid;
    }

    /**Verifies if the inventory is valid.
     * Valid stock is less than min, greater than max, and not equal to 0.
     *
     * @param min the min value for the product to add
     * @param max the max value for the product to add
     * @param stock the stock value for the product to add
     * @return <code>true</code> if valid, and <code>false</code> if not valid
     */
    private boolean invValid(int min, int max, int stock){
        boolean invIsValid = true;

        if((stock < min) ||(stock > max) || (stock == 0)) {
            invIsValid = false;
        }
        return invIsValid;
    }

    /**Loads the <code>mainForm</code> controller class.
     * It allows to open the <code>mainForm</code>.
     *
     * @param actionEvent passed from the parent method
     * @throws IOException from the <code>FXMLoader</code>
     */
    private void returnToMainScreen(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1400, 675);
        stage.setTitle("Main Form");
        stage.setScene(scene);
        stage.show();
    }

    /**First method to run in this class, and populates the tables views.
     * The <code>initialize</code> method is implemented by the <code>Initializable</code> class.
     * <p>
     * Called when the <code>FXMLoader</code> calls the addPartForm FXML file.
     *
     * @param url the location used to retrieve relative paths for the root object (null if location unknown)
     * @param resourceBundle the resources used to localize the root object (null if root object not localized)
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("User opened the Add Product Form.");

        partsPane2.setItems(Inventory.getAllParts());

        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

        AssociatedPartsPane.setItems(associatedParts);

        assocPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartPricePerUnit.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
}


