package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This model class shapes the data for the product and its associated part(s).
 *
 * @author Myriam Drouin-Sagar
 */
public class Product {
    /**
     * The list that contains the associated parts to a product.
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int max;
    private int min;

    /**
     * The default constructor of the Product class.
     */
    public Product(){
        this(0,null,0.00,0,0,0);
    }

    /**The full constructor of the Product class.
     *
     * @param id the id of the product
     * @param name the name of the product
     * @param price the price of the product
     * @param stock the inventory of the product
     * @param min the minimum of stock required for the product
     * @param max the maximum of stock a product can have
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.max = max;
        this.min = min;
    }

    /**Gets the id value.
     *
     * @return the id value
     */
    public int getId() {
        return id;
    }

    /**Sets the id.
     *
     * @param id the id to set
     */
    public void setId(int id){

        this.id = id;
    }

    /**Gets the name value.
     *
     * @return the name value
     */
    public String getName(){
        return name;
    }

    /**Sets the name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**Gets the price value.
     *
     * @return the price value
     */
    public double getPrice(){
        return price;
    }

    /**Sets the price.
     *
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**Gets the stock value.
     *
     * @return the stock value
     */
    public int getStock(){
        return stock;
    }

    /**Sets the stock.
     *
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**Gets the min value.
     *
     * @return the min value
     */
    public int getMin(){
        return min;
    }

    /**Sets the min.
     *
     * @param min min to set
     */
    public void setMin(int min){
        this.min = min;
    }

    /**Gets the max value.
     *
     * @return the max value
     */
    public int getMax(){
        return max;
    }

    /**Sets the max.
     *
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**Associates parts to the product.
     * It adds the reference variable part of type Part to the ObservableList associatedParts.
     *
     * @param part the part selected by the user to be associated to the product
     */
    public void addAssociatedPart(Part part) {

        this.associatedParts.addAll(part);
        }

    /**Dissociates parts from the product.
     * It removes the reference variable part of type Part from the ObservableList associatedParts.
     *
     * @param selectedAssociatedPart the part selected by de user to be dissociated from the product
     * @return <code>true</code> if the associated part was deleted, and <code>false</code> if it was not deleted (never used)
     */
    public boolean  deleteAssociatedPart(Part selectedAssociatedPart) {
        if (associatedParts.contains(selectedAssociatedPart)) {
            associatedParts.remove(selectedAssociatedPart);
            return true;
        }
        else {
            return false;
        }
    }

    /**Gets all the parts associated with the product.
     *
     * @return the ObservableList of associated parts objects
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }


}
