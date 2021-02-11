package Supply_Demand_Program;
import java.util.Scanner;

public abstract class Quantity <T extends Quantity<T>>{
    private double intercept;
    private double slope;
    
    public double getQuantity(Price pr){
	return intercept + slope * pr.getPrice();
    }

    public Quantity<T> initialize(){
	Scanner input = new Scanner(System.in);
	if(this instanceof QuantitySupply){
	    System.out.println("Supply Equation");
	}else{
	    System.out.println("Demand Equation");
	}
	System.out.print("Enter the y-intercept (starting quantity at price zero): ");
	this.intercept = setter(input);
	System.out.print("Enter the slope ");
        this.slope = setter(input);
	return this;    
    }

    public double setter(Scanner input){
	double value = 0.0;
	boolean condition = true;
        while(condition){
	    if(input.hasNextDouble()){
		value = input.nextDouble();
		condition = false;
	    }else{
		System.out.print("Enter a valid number: ");
		input.next();
	    }
	}
	return value;
    }

    public double getSlope(){
	return this.slope;
    }

    public double getYIntercept(){
	return this.intercept;
    }

    public String toString(){
	String newStr = "";
	if(this instanceof QuantitySupply){
	    newStr += "Q(S) = ";
	}else{
	    newStr += "Q(D) = ";
	}
	newStr += intercept;
	if(slope > 0){
	    newStr += " + " + slope;
	}else if(slope < 0){
	    newStr += " - " + (slope * -1);
	}
	newStr += "P";
	return newStr;
    }
}
