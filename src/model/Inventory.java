package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The <code>Inventory</code> class shapes the inventory for all the parts and products.
 *
 * @author Myriam Drouin-Sagar
 */
public class Inventory {

    /**
     * The list that contains all the parts.
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * The list that contains all the products.
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Initialization of the variable used to produce unique part id's.
     */
    private static int nextPartID = 1;

    /**
     * Initialization of the variable to produce unique product id's.
     */
    private static int nextProductID = 1;

    /**Adds a part object to the ObservableList allParts.
     * It utilizes the  ArrayList <code>add</code> method inherited from the <code>javaFXCollections</code> class.
     *
     * @param newPart the parts to be added to the parts inventory
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**Adds a <code>Product</code> object to the ObservableList allProducts.
     * It utilizes the ArrayList <code>add</code> method inherited from the <code>javaFXCollections</code> class.
     *
     * @param newProduct the product to be added to the products inventory
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**Searches for a part by its id in the ObservableList allParts.
     *
     * @param partId the id of the part to search for
     * @return the object part if found, and null if not found
     */
    public static Part lookupPart(int partId){
        Part partIdFound;

        for (Part part : allParts) {
            if (part.getId() == partId) {
                partIdFound = part;
                return partIdFound;
            }
        }
        return null;
    }

    /**Searches for a product by its id in the ObservableList allProducts.
     *
     * @param productId the id of the product to search for
     * @return the object product if found, and null if not found
     */
    public static Product lookupProduct(int productId) {
        Product productIdFound;

        for (Product product : allProducts) {
            if (product.getId() == productId) {
                productIdFound = product;
                return productIdFound;
            }
        }
        return null;
    }

    /**Filters by name the ObservableList allParts.
     * I created an ObservableList partsNameFound, witch contains the part(s) associated with the part's name or partial name entered by the user.
     *
     * @param partName the name or partial name of the part to search for
     * @return a list of the part(s) found, or null if no part are found
     */
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> partsNameFound = FXCollections.observableArrayList();

        for(Part part : allParts) {
            if(part.getName().toLowerCase().contains(partName.toLowerCase())){
                partsNameFound.add(part);
                return partsNameFound;
            }
        }
        return null;
    }

    /**Filters by name the ObservableList allProducts.
     * I created an ObservableList productsNameFound, which contains the products(s) associated with the product's name or partial name.
     *
     * @param productName the name or partial name of the product to search for
     * @return a list of the products(s) found or null id no product are found
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> productsNameFound = FXCollections.observableArrayList();

        for (Product product : allProducts) {
            if(product.getName().toLowerCase().contains(productName.toLowerCase())) {
                productsNameFound.add(product);
                return productsNameFound;
            }
        }
        return null;


    }

    /**Replaces the existing part with the new reference variable of type <code>Part</code> and its values.
     * It calls the ArrayList <code>set</code> method inherited from the <code>JavaFXCollections</code> class.
     *
     * @param index the index of the part in the ObservableList allParts
     * @param selectedPart the reference variable of type <code>Part</code>
     */
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    /**Replaces the existing product  with the new <code>Product</code> object and its values.
     * It calls the ArrayList <code>set</code> method inherited from the <code>javaFXCollections</code> class
     *
     * @param index the index of the product in the ObservableList allProduct
     * @param newProduct the product object of type <code>Product</code>
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /**Removes a part from the ObservableList allParts.
     * It calls the ArrayList remove method inherited from the javaFXCollections class.
     *
     * @param selectedPart the part selected by the user of type <code>Part</code>
     * @return <code>true</code> if the part was removed from the list, and <code>false</code> if not (never used)
     */
    public static Boolean deletePart(Part selectedPart) {
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        } else {
            return false;
        }
    }

    /**Removes a product object from the ObservableList allProducts.
     *
     * @param selectedProduct the product object selected by the user of type <code>Product</code>
     * @return <code>true</code> if the product was removed from the list, and <code>false</code> if not (never used)
     */
    public static Boolean deleteProduct(Product selectedProduct) {
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
            return true;
        }
        else {
            return false;
        }
    }

    /**Gets all the parts.
     *
     * @return an ObservableList of part objects
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**Gets all the products.
     *
     * @return an ObservableList of product objects
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**Generates a unique part id.
     *
     * @return a unique part id
     */
    public static int getNextPartID() {
        return nextPartID++;
    }

    /**Generates a unique product id.
     *
     * @return a unique product id
     */
    public static int getNextProductID() {
        return nextProductID++;
    }
}










