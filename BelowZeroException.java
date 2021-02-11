package Supply_Demand_Program;
public class BelowZeroException extends Exception{
    private static final String value = "Price has reached or gone below zero!";

    
    public BelowZeroException(){
	super(value);
    }

   
}
