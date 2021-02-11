package Supply_Demand_Program;

import java.math.*;

public class Equilibrium{
    private Quantity qd, qs;
    private double price, quantity;
    private boolean test = true;

    public Equilibrium (QuantityDemand dEquation, QuantitySupply sEquation){
	qd = dEquation;
	qs = sEquation;
	try{
	    price = findPrice();
	}catch(Exception e){
	    test = false;
	}
	quantity = findQuantity();
    }

    public boolean hasPositiveEq(){
	return this.test;
    }

    public double getPrice(){
	return this.price;
    }

    private double findPrice() throws BelowZeroException, ArithmeticException{
	/*BigDecimal bd = new BigDecimal(0.0);
	Price p = new Price(bd.doubleValue());
        boolean condition = false;*/
	double slope = qs.getSlope() - qd.getSlope();
	double intercept = qd.getYIntercept() -  qs.getYIntercept();
	double price = intercept / slope;
	if(Double.valueOf(price).equals(Double.POSITIVE_INFINITY) || Double.valueOf(price).equals(Double.NEGATIVE_INFINITY)){
	    throw new ArithmeticException("Essentially divide by zero, equilibrium price can't be calculated. This due to quantity supply and demand never being equivalent as same slope different intercept. Please enter different equation(s)");
	}
	/*do{
	    bd = bd.add(new BigDecimal(0.1));
	    p = new Price(bd.doubleValue());
	    qd = d.getQuantity(p);
	    qs = s.getQuantity(p);
	    condition = Math.abs(qd - qs) <= minSlope(d, s);
	}while(((qd != qs) && condition == false) && (qd > 0 && qs > 0));
	while(qd != qs && (qd > 0 && qs > 0)){
	    bd = bd.add(new BigDecimal(0.000000001));
	    p = new Price(bd.doubleValue());
	    qd = d.getQuantity(p);
	    qs = s.getQuantity(p);
	    }*/
	if(price < 0){
	    throw new BelowZeroException();
	}
	return price;
    }

    private double findQuantity(){
	return qd.getQuantity(new Price(getPrice()));
    }

    public double getQuantity(){
	return this.quantity;
    }

    public String toString(){
	return qd + " == "  + qs + " at price " + getPrice() + " and quantity " + getQuantity();
    }
	
    public Quantity equationD(){
	return qd;
    }

    public Quantity equationS(){
	return qs;
    }

    public double minSlope(Quantity q1, Quantity q2){
	return Math.min(Math.abs(q1.getSlope()), Math.abs(q2.getSlope()));
    }
	
 
}
    
