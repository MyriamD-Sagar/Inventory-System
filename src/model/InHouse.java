package model;

/**
 * The <code>InHouse</code> class is a subclass of the <code>Part</code> class.
 * It inherits data members from the Part class.
 * The <code>Part</code> class is an abstract class, which cannot be instantiated, but its subclasses can.
 * The <code>InHouse </code>class provides then implementations for the <code>Part</code> class.
 *
 * @author Myriam Drouin-Sagar
 */
public class InHouse extends Part {
    /**
     * The machine id for the part
     */
    private int machineId;

    /**The full constructor of the <code>InHouse</code> class.
     *
     * @param id the id of the <code>InHouse</code> object
     * @param name the name of the <code>InHouse</code> object
     * @param price the price of the <code>InHouse</code> object
     * @param stock the stock of the <code>InHouse</code> object
     * @param min the min of the <code>InHouse</code> object
     * @param max the max of the <code>InHouse</code> object
     * @param machineId the machine id of the <code>InHouse</code> object
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max); // Data members inherited from the Part class
        this.machineId = machineId;

    }

    /**Sets the machine id for the part
     *
     * @param machineId the machine id to set
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /**Gets the machine id value for the part
     *
     * @return the machine id value
     */
    public int getMachineId(){
        return machineId;
    }

}
