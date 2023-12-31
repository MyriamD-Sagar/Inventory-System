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
import model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class allows to modify values of In-House and Outsourced objects of type Part
 * and to update the ObservableList allParts in the <code>Inventory</code> class.
 * <p>
 * The <code>modifyPartForm</code> class implements the <code>Initializable</code> class interface.
 * It provides control to the data flow into model objects and updates the view modifyPartForm FXML class.
 *
 * @author Myriam Drouin-Sagar
 */
public class modifyPartForm implements Initializable {

    /**
     * The reference variable of type <code>Part</code> selected by the user in the <code>mainForm</code>
     */
    private Part selectedPart;

    /**
     * The part id text field
     */
    public TextField modifyPartIDText;

    /**
     * The part name text field
     */
    public TextField modifyPartNameText;

    /**
     * The part inventory level text field
     */
    public TextField modifyPartInvText;

    /**
     * The part price text field
     */
    public TextField modifyPartPriceText;

    /**
     * The part maximum stock text field
     */
    public TextField modifyPartMaxText;

    /**
     * The part minimum stock text field
     */
    public TextField modifyPartMinText;

    /**
     * The In-House radio button
     */
    public RadioButton inHouseModifyPartRadio;

    /**
     * The Outsourced radio button
     */
    public RadioButton outsourcedModifyPartRadio;

    /**
     * The button to save the part's information typed in the <code>modifyPartForm </code>and return to the <code>mainForm</code>
     */
    public Button saveModifyPartButton;

    /**
     * The button to cancel the <code>modifyPartForm</code> and return to the <code>mainForm</code>
     */
    public Button cancelModifyPartButton;

    /**
     * The machine id/company name label for the part
     */
    public Label modifyPartMachineOrCompany;

    /**
     * The machine id/company name text field for the part
     */
    public TextField modifyPartMachineOrCompanyText;


    /**Sets the Machine ID/Company Name label to "Machine ID".
     * The label is populated when the In-House radio Button is selected by the user.
     *
     * @param actionEvent The event generated by the In-House radio button inHouseModifyPartRadio when selected by the user (never used)
     */
    public void onInHouseModifyPart(ActionEvent actionEvent) {
        modifyPartMachineOrCompany.setText("Machine ID");
    }

    /**Sets the Machine ID/Company Name label to "Company Name".
     * The label is populated when the Outsourced radio button is selected by the user.
     *
     * @param actionEvent The event generated by the Outsourced radio Button outsourcedModifyPartRadio when selected by the user (never used)
     */
    public void onOutsourcedModifyPart(ActionEvent actionEvent) {
        modifyPartMachineOrCompany.setText("Company Name");
    }

    /**Cancels the <code>modifyPartForm </code>and returns to the <code>mainForm</code>.
     * It displays a confirmation dialog box.
     * If confirmed, a call to the function <code>returnToMainScreen</code> will redirect the user to the <code>mainForm</code>.
     * @param actionEvent The event generated by the Cancel button cancelModifyPartButton when clicked by the user
     * @throws IOException from the <code>FXMLoader</code> in the method <code>returnToMainScreen</code>
     */
    public void onCancelModifyPart(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure you want to Cancel this modification and return to the Main Form?");
        Optional<ButtonType> showResult = alert.showAndWait();
        if(showResult.isPresent() && showResult.get() == ButtonType.OK){
            returnToMainScreen(actionEvent);
        }
    }

    /** Gets and save a new <code>InHouse</code> or <code>Outsourced</code> object's values entered by the user in the <code>modifyPartForm</code> and loads the <code>mainForm</code>.
     * <p>
     * It updates the ObservableList allParts with the newly created instance of <code>InHouse</code> or <code>Outsourced</code> type <code>Part</code>
     * by calling the <code>updatePart</code> method of the <code>Inventory</code> class.
     *<p>
     * A call to the <code>minMaxValid</code> method and <code>invValid</code> method is made, and an error message is displayed if min or inv is not valid.
     * It gets the machine ID value if the In-house radio button is selected.
     * It gets the company name value if the Outsourced radio button is selected.
     * <p>
     * <code>NumberFormatException</code> to catch invalid values (string in an int text field) or an empty text field.
     * If caught, an error message is displayed.
     *
     * @param actionEvent The event generated by the Save button saveModifyPartButton when clicked by the user
     * @throws IOException from the <code>FXMLoader</code> in the method <code>returnToMainScreen</code>
     */
    public void onSaveModifyPart(ActionEvent actionEvent) throws IOException {
        try {
            if (!minMaxValid(Integer.parseInt(modifyPartMinText.getText()), Integer.parseInt(modifyPartMaxText.getText()))) {
                errorDisplay(1);
                return;
            }
            if(!invValid(Integer.parseInt(modifyPartMinText.getText()), Integer.parseInt(modifyPartMaxText.getText()),Integer.parseInt(modifyPartInvText.getText()))){
                errorDisplay(2);
                return;
            }

            int id = selectedPart.getId(); // except the part id
            String name = modifyPartNameText.getText();
            int stock = Integer.parseInt(modifyPartInvText.getText());
            double price = Double.parseDouble(modifyPartPriceText.getText());
            int min = Integer.parseInt(modifyPartMinText.getText());
            int max = Integer.parseInt(modifyPartMaxText.getText());
            int machineId;
            String companyName;
            if (name.isEmpty()){
                errorDisplay(4);
                return;
            }

            if (inHouseModifyPartRadio.isSelected()) {
                machineId = Integer.parseInt(modifyPartMachineOrCompanyText.getText());
                InHouse newModifyInHousePart = new InHouse(id, name, price, stock, min, max, machineId);
                Inventory.updatePart(newModifyInHousePart.getId() - 1, newModifyInHousePart);
            }
            if (outsourcedModifyPartRadio.isSelected()) {
                companyName = modifyPartMachineOrCompanyText.getText();
                if (companyName.isEmpty()){
                    errorDisplay(5);
                    return;
                }
                else {
                    Outsourced newModifyOutsourcedPart = new Outsourced(id, name, price, stock, min, max, companyName);
                    Inventory.updatePart(newModifyOutsourcedPart.getId() - 1, newModifyOutsourcedPart);
                }
            }
            returnToMainScreen(actionEvent);
        }catch(NumberFormatException e) {
            errorDisplay(3);
        }
    }

    /**Defines multiple error messages for the <code>modifyPartForm</code>.
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
            case 5:
                alertError.setTitle("Error");
                alertError.setHeaderText("Company Name Field is Empty.");
                alertError.setContentText("Company Name field cannot be empty. Please enter a valid Company Name for the part.");
                alertError.showAndWait();
                break;
        }
    }

    /**Verifies if the min is valid.
     * Valid min is less than max and greater than 0.
     *
     * @param min the min value for the part to modify
     * @param max the max value for the part to modify
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
     * @param min the min value for the part to modify
     * @param max the max value for the part to modify
     * @param stock the stock value for the part to modify
     * @return <code>true</code> if valid, and <code>false</code> if not valid
     */
    private boolean invValid(int min, int max, int stock){
        boolean invIsValid = true;

        if((stock < min) ||(stock > max) || (stock == 0)) {
            invIsValid = false;
        }
        return invIsValid;
    }

    /**Loads the <code><mainForm</code> controller class.
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


    /**Gets the part selected in the <code>mainForm</code> and display its values in the corresponding text fields.
     * <p>
     * The <code>initialize</code> method is implemented by the <code>Initializable</code> class.
     * This method will be call when the FXMLoader call the addPartForm FXML file.
     * <p>
     * First method to run in this controller class.
     *
     * @param url The location used to retrieve relative paths for the root object (null if location unknown)
     * @param resourceBundle The resources used to localize the root object (null if root object not localized)
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("User opened the Modify Part Form.");

        selectedPart = mainForm.getPartToModify();

        if(selectedPart instanceof InHouse) {
            inHouseModifyPartRadio.setSelected(true);
            modifyPartMachineOrCompany.setText("Machine ID");
            modifyPartMachineOrCompanyText.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));
        }
        if (selectedPart instanceof Outsourced){
            outsourcedModifyPartRadio.setSelected(true);
            modifyPartMachineOrCompany.setText("Company Name");
            modifyPartMachineOrCompanyText.setText(((Outsourced) selectedPart).getCompanyName());
        }
        modifyPartIDText.setText(String.valueOf(selectedPart.getId()));
        modifyPartNameText.setText(selectedPart.getName());
        modifyPartInvText.setText(String.valueOf(selectedPart.getStock()));
        modifyPartPriceText.setText(String.valueOf(selectedPart.getPrice()));
        modifyPartMinText.setText(String.valueOf(selectedPart.getMin()));
        modifyPartMaxText.setText(String.valueOf(selectedPart.getMax()));
    }
}
