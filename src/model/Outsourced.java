package model;

/**
 * The<code>Outsourced</code> class is a subclass of the Part class.
 * It inherits data members from the Part class.
 * The <code>Part</code> class is an abstract class, which cannot be instantiated, but its subclasses can.
 * The <code>Outsourced</code> class provides then implementations for the <code>Part</code> class.
 *
 * @author Myriam Drouin-Sagar
 */
public class Outsourced extends Part {

    /**
     * The company name for the part
     */
    private String companyName;

    /**The full constructor of the <code>Outsourced</code> class.
     *
     * @param id the id of the <code>Outsourced</code> object
     * @param name the name of the <code>Outsourced</code> object
     * @param price the price of the <code>Outsourced</code> object
     * @param stock the stock of the <code>Outsourced</code> object
     * @param min the min of the <code>Outsourced</code> object
     * @param max the max of the <code>Outsourced</code> object
     * @param companyName the company name of the <code>Outsourced</code> object
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max); // Data members inherited from the Part class
        this.companyName = companyName;
    }

    /**Sets the company name for the part.
     *
     * @param companyName the company name to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**Gets the company name value for the part.
     *
     * @return the company name value
     */
    public String getCompanyName(){
        return companyName;
    }
}
