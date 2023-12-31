package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

import java.io.IOException;
import java.net.URL;

import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This controller class allows to add InHouse and Outsourced object of type Part to the ObservableList allParts of the <code>Inventory</code> class.
 * <p>
 * The <code>addPartForm</code> class implements the <code>Initializable</code> interface.
 * Provides control to the data flow into objects and updates the view addPartForm FXML file.
 *
 * @author Myriam Drouin-Sagar
 */
public class addPartForm implements Initializable {

    /**
     * The button to save the part's information typed in the <code>addPartForm</code> and return to the <code>mainForm</code>
     */
    public Button saveAddPartButton;

    /**
     * The button to cancel the <code>addPartForm </code>and return to the <code>mainForm</code>
     */
    public Button cancelAddPartButton;

    /**
     * The In-House radio button
     */
    public RadioButton inHouseAddPartRadio;

    /**
     * The Outsourced radio button
     */
    public RadioButton outsourcedAddPartRadio;

    /**
     * The part id text field
     */
    public TextField addPartIDText;

    /**
     * The part name text field
     */
    public TextField addPartNameText;

    /**
     * The part inventory level text field
     */
    public TextField addPartInvText;

    /**
     * The part price text field
     */
    public TextField addPartPriceText;

    /**
     * The part maximum stock text field
     */
    public TextField addPartMaxText;

    /**
     * the part minimum stock text field
     */
    public TextField addPartMinText;

    /**
     * The machine id/company name text field for the part
     */
    public TextField addPartMachineOrCompanyText;

    /**
     * The machine id/company name label for the part
     */
    public Label addPartMachineOrCompany;




    /**Sets the Machine ID/Company Name label to "Machine ID".
     * The label is populated when the In-House radio button is selected by the user.
     *
     * @param actionEvent The event generated by the In-House radio button inHouseAddPartRadio when selected by the user (never used)
     */
    public void onInHouseAddPart(ActionEvent actionEvent) {
        addPartMachineOrCompany.setText("Machine ID");

    }
    /**Sets the Machine ID/Company Name label to "Company Name".
     * The label is populated when the Outsourced radio button is selected by the user.
     *
     * @param actionEvent The event generated by the Outsourced radio button outsourcedAddPartRadio when selected by the user (never used)
     */
    public void onOutsourcedAddPart(ActionEvent actionEvent) {
        addPartMachineOrCompany.setText("Company Name");
    }

    /**Cancels the <code>addParForm</code> and returns to the <code>mainForm</code>.
     * <p>
     * It displays a confirmation dialog box.
     * If confirmed, a call to the function <code>returnToMainScreen</code> will redirect the user to the <code>mainForm</code>.
     *
     * @param actionEvent The event generated by the Cancel button cancelAddPartButton when clicked by the user
     * @throws IOException from the <code>FXMLLoader</code> in the method <code>returnToMainScreen</code>
     */
    public void onCancelAddPart(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel this part and return to the Main Form?");
        Optional<ButtonType> showResult = alert.showAndWait();
        if(showResult.isPresent() && showResult.get() == ButtonType.OK){
            returnToMainScreen(actionEvent);
        }
    }

    /**Gets and saves a new <code>InHouse </code>or <code>Outsourced</code> object and its values entered by the user in the <code>addPartForm</code> and loads the <code>mainForm</code>.
     * <p>
     * It adds a new object of type <code>Part</code> to the ObservableList allParts by calling the <code>addPart</code> method of the <code>Inventory</code> class.
     * <p>
     * A call to the <code>minMaxValid</code> method and <code>invValid</code> method is made, and an error message is displayed if min or inv is not valid.
     * It gets the machine ID value if the In-house radio button is selected.
     * It gets the company name value if the Outsourced radio button is selected.
     * <p>
     * <code>NumberFormatException</code> to catch invalid values (string in an int text field) or an empty text field.
     * If caught, an error message is displayed.
     *
     * @param actionEvent The event generated by the Save button saveAddPartButton when clicked by the user
     * @throws IOException from the FXMLoader in the method <code>returnToMainScreen</code>
     */
    public void onSaveAddPart(ActionEvent actionEvent) throws IOException{

        try {
            if (!minMaxValid(Integer.parseInt(addPartMinText.getText()), Integer.parseInt(addPartMaxText.getText()))) {
                errorDisplay(1);
                return;
            }
            if(!invValid(Integer.parseInt(addPartMinText.getText()), Integer.parseInt(addPartMaxText.getText()), Integer.parseInt(addPartInvText.getText()))){
                errorDisplay(2);
                return;
            }

            String name = addPartNameText.getText();
            int stock = Integer.parseInt(addPartInvText.getText());
            double price = Double.parseDouble(addPartPriceText.getText());
            int min = Integer.parseInt(addPartMinText.getText());
            int max = Integer.parseInt(addPartMaxText.getText());
            int machineId;
            String companyName;

            if(name.isEmpty()){
                errorDisplay(4);
                return;
            }
            if (inHouseAddPartRadio.isSelected()) {
                machineId = Integer.parseInt(addPartMachineOrCompanyText.getText());
                InHouse newInHousePartId = new InHouse(Inventory.getNextPartID(), name, price, stock, min, max, machineId);
                Inventory.addPart(newInHousePartId);
            }
            if (outsourcedAddPartRadio.isSelected()) {
                companyName = addPartMachineOrCompanyText.getText();
                Outsourced newOutsourcedPartId = new Outsourced(Inventory.getNextPartID(), name, price, stock, min, max, companyName);
                Inventory.addPart(newOutsourcedPartId);
            }

            returnToMainScreen(actionEvent);

        }catch(NumberFormatException e) {
            errorDisplay(3);

        }

    }

    /**Defines multiple error messages for the <code>addPartForm</code>.
     *
     * @param caseNumber the case associated with the error message to be displayed (passed from the parent method)
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
                alertError.setContentText("Name field cannot be empty. Please enter a valid Name for the part.");
                alertError.showAndWait();
                break;
        }
    }

    /**Verifies if the min is valid.
     * Valid min is less than max and greater than 0.
     *
     * @param min the min value for the part to add
     * @param max the max value for the part to add
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
     * @param min the min value for the part to add
     * @param max the max value for the part to add
     * @param stock the stock value for the part to add
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
    private void returnToMainScreen(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 1400, 675);
        stage.setTitle("Main Form");
        stage.setScene(scene);
        stage.show();
    }

    /**First method to run in this class, and a print message will appear in the command prompt.
     * <p>
     * The <code>initialize</code> method is implemented by the Initializable class.
     * Called when the <code>FXMLoader</code> calls the addPartForm FXML file.
     *
     *
     * @param url The location used to retrieve relative paths for the root object (null if location unknown)
     * @param resourceBundle The resources used to localize the root object (null if root object not localized)
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("User opened the Add Part Form.");

    }
}
