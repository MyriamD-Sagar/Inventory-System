# Inventory-System


The purpose of this application is to create a JavaFX application based on specific business requirements to produce an inventory system for a small manufactoring organization.

This project was completed for the Software I - C482 course at WGU.

Students were provided a mock-up of the user interface to use in the design and development of the system and a class diagram to assist in developing the application.
Solution statements based on the business requirements are outlined in the "Requirements" section, which were used to develop the application.  



Application Version: 1.0

Date: 5/22/2022




## Author's Information 

Name: Myriam Drouin-Sagar

Email: midrou_10@hotmail.com



## IDE Information

IntelliJ IDEA Community Editition 2021.1.3 x64

JDK Version: jdk-18.0.1

Java FX Version: javafx-sdk-18.0.1 


 
## Requirements
A. Create a JavaFX application with a graphical user interface (GUI) based on the mock-up. You may use JavaFX with or without FXML to create your GUI, or you may use Scene Builder to create your FXML file;use of Swing is not permitted.
      The user interface (UI) should closely match the organization of the GUI layout and contain all UI components (buttons, text fi elds, etc.) in each of the following GUI mock-up forms:
   
      1. Main form
      2. Add Part form
      3. Modify Part form
      4. Add Product form
      5. Modify Product form
      
B.  Provide Javadoc comments for each class member throughout the code (see JavaDoc folder).

C. Create classes with data and logic that map to the UML class diagram. Include all the classes and members as shown in the UMLdiagram. Your code should demonstrate the following:


   * inheritance
   * abstract and concrete classes
   * instance and static variables
   * instance and static methods

D. Add the following functionalities to the Main form:

**The Parts pane**

   * The Add button under the Parts TableView opens the Add Part form.
   * The Modify button under the Parts TableView opens the Modify Part form.
   * The Delete button under the Parts TableView deletes the selected part from the Parts TableView or displays a descriptive error message in the UI or in a dialog box if a part is not deleted.
   * When the user searches for parts by ID or name (partial or full name) using the text field, the application displays matching results in the Parts TableView. (Including a search button is optional.)
     If the part or parts are found, the application highlights a single part or filters multiple parts. If the part is not found, the application displays an error message in the UI or in a dialog box.
   * If the search field is set to empty, the table should be repopulated with all available parts.

**The Products pane** 

   * The Add button under the Products TableView opens the Add Product form.
   * The Modify button under the Products TableView opens the Modify Product form.
   * The Delete button under the Products TableView deletes the selected product (if appropriate) from the Products TableView or displays a descriptive error message in the UI or in a dialog box if a product is not deleted.
   * When the user searches for products by ID or name (partial or full name) using the text field, the application displays matching results in the Products TableView. (Including a search button is optional.) 
     If a product or products are found, the application highlights a single product or products or filters multiple products. If a product or products are not found, the application displays an error message in the UI or in a dialog box.
   * If the search field is set to empty, the table should be repopulated with all available products.

**Exit Button**

   * The Exit button closes the application.

E. Add the listed functionalities to the following parts forms: 

**The Add Part form**

   * The In-House and Outsourced radio buttons switch the bottom label to the correct value (Machine ID orCompany Name).
   * The application auto-generates a unique part ID. The part IDs can be, but do not need to be, contiguous.
     - The part ID text field must be disabled.
   * The user should be able to enter a part name, inventory level or stock, a price, maximum and minimum values,and company name or machine ID values into active text fields.
   * After saving the data, users are automatically redirected to the Main form.
   * Canceling or exiting this form redirects users to the Main form.

**The Modify Part form** 

   * The text fields populate with the data from the chosen part.
   * The In-House and Outsourced radio buttons switch the bottom label to the correct value (Machine ID orCompany Name) and swap In-House parts and Outsourced parts.
     When new objects need to be created after the Save button is clicked, the part ID should be retained.
   * The user can modify data values in the text fields sent from the Main form except the part ID.
   * After saving modifications to the part, the user is automatically redirected to the Main form.
   * Canceling or exiting this form redirects users to the Main form.

F. Add the following functionalities to the following products form:

**The Add Product Form**

   * The application auto-generates a unique product ID. The product IDs can be, but do not need to be, contiguous.
     - The product ID text field must be disabled and cannot be edited or changed.
   * The user should be able to enter a product name, inventory level or stock, a price, and maximum and minimum values.
   * The user can search for parts (top table) by ID or name (partial or full name). If the part or parts are found, the application highlights a single part or filters multiple parts. 
     If the part or parts are not found, the application displays an error message in the UI or in a dialog box.
   * If the search fi eld is set to empty, the table should be repopulated with all available parts.
   * The top table should be identical to the Parts TableView in the Main form.
   * The user can select a part from the top table. The user then clicks the Add button, and the part is copied to the bottom table. (This associates one or more parts with a product.)
   * The Remove Associated Part button removes a selected part from the bottom table. (This dissociates or removes a part from a product.)
   * After saving the data, the user is automatically redirected to the Main form.
   * Canceling or exiting this form redirects users to the Main form.
      
*Note: When a product is deleted, so can its associated parts without affecting the part inventory. The Remove Associated Part button removes a selected part from the bottom table. (This dissociates or removes a partfrom a product.)*
      
 **The Modify Product Form**

   * The text fields populate with the data from the chosen product, and the bottom TableView populates with the associated parts.
   * The user can search for parts (top table) by ID or name (partial or full name). If the part or parts are found, the application highlights a single part or filters multiple parts.
     If the part is not found, the application displays anerror message in the UI or a dialog box.
   * If the search text fi eld is set to empty, the table should be repopulated with all available parts.
   * The top table should be identical to the Parts TableView in the Main form.
   * The user may modify or change data values.
     - The product ID text fi eld must be disabled and cannot be edited or changed.
   * The user can select a part from the top table. The user then clicks the Add button, and the part is copied to thebottom table. (This associates one or more parts with a product.)
   * The user may associate zero, one, or more parts with a product.
   * The user may remove or disassociate a part from a product.
   * After saving modifications to the product, the user is automatically redirected to the Main form.
   * Canceling or exiting this form redirects users to the Main form.

*Note: The Remove Associated Part button removes a selected part from the bottom table. (This dissociates or removes a part from a product.)*

G. Write Code to implement input validation and logical error checks using a dialog box or message in the UI displaying adescriptive error message for each of the following circumstances:

 * Min should be less than Max; and Inv should be between those two values.
 * The user should not delete a product that has a part associated with it.
 * The application confirms the “Delete” and “Remove” actions.
 * The application will not crash when inappropriate user data is entered in the forms; instead, error messages shouldbe generated.

H. Provide a folder containing Javadoc files that were generated from the IDE or via the command prompt from part B. In a comment above the main method header declaration, please specify where this folder is located.

## How to Run the Program 

  1. To run this program, download this project and note the directory where the project resides.
  2. Once this is done, open the project from IntelliJ IDEA and ensure that it is set with the same JDK and JavaFX version described above.
  3. When you are set up, run the the program and the main form will appear.
  4. Explore the different capabilities of the application by doing the following:
     - Add parts
     - Modify parts
     - Delete parts
     - Search for a part by ID or name using the appropriated search bar 
     - Add products and, if desired, its associated part(s)
     - Modify products
     - Delete products
     - Search for a product by ID or name using the appropriated search bar
     - Exit the application
 



