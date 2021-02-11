package Supply_Demand_Program;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.text.DecimalFormat;


public class SupplyDemandObject{

    Output o;
    File toRecord;
    Equilibrium eq;
    QuantityDemand qd;
    QuantitySupply qs;
    List<Double> demand;
    List<Double> supply;
    List<Double> p;
    int length;
    double scale;
    Scanner input;
    DecimalFormat df;

    public SupplyDemandObject(){
	qd = new QuantityDemand();
	qs = new QuantitySupply();
	eq = new Equilibrium(qd, qs);
	input = new Scanner(System.in);
	init();
    }

    public void init(){
	if(getLengthValue() && getIncrementValue()){
	    df = places();
	    p = fill();
	    demand = fill(qd);
	    supply = fill(qs);
	    System.out.println("Enter created or uncreated file name with a tsv or csv extension: ");
	    try{
		String filename = input.next();
		o = new Output(filename);
		o.writeOut(p, demand, supply, eq);
		toRecord = o.giveBack();
		System.out.println("Succesfully created file and fully instantiated the object with data!");
	    }catch(Exception e){
		//Has to be either the IO exception from the file output or with string input somehow. Should be error code
		System.out.println("Invalid file to write to but data still processed");
	    }
	}else{
	    // Will have to throw error code instead. Just need info and if not provide a warning or print to console no file output or data.
	    System.out.println("Weren't provided length or increment for values");
	    }
    }
    public static void main(String [] args){
	SupplyDemandObject obj = new SupplyDemandObject();
    }
    

    public List<Double> fill(){
	List<Double> list = new ArrayList<Double>();
	BigDecimal bd = new BigDecimal(0);
	for(int i = 0; i < length; i++){
	    list.add(bd.doubleValue());	   
	    bd = bd.add(new BigDecimal(df.format(scale)));
	}
	return list;
    }

    public List<Double> fill(Quantity q){
	List<Double> quantities = new ArrayList<Double>(p.size());
	for(int i = 0; i < p.size(); i++){
	    quantities.add(Double.valueOf(df.format(q.getQuantity(new Price(p.get(i))))));
	}
	return quantities;
    }

    public boolean getLengthValue(){
	boolean check = true;
	System.out.println("How many values should the price be calculated for? ");
	try{
	    length = input.nextInt();
	}catch(InputMismatchException e){
	    check = false;
	}
	return check;
    }

    public boolean getIncrementValue(){
	boolean check = true;
	System.out.println("And going by what scale? ");
	try{
	    scale = input.nextDouble();
	}catch(InputMismatchException e){
	    check = false;
	}
	return check;
    }

    public DecimalFormat places(){
	String [] arr = String.valueOf(scale).split("\\.");
	String newStr = "";
	//do{
	newStr += "0";
	    //	}while(length / 10 != 0);
	newStr += ".";
	for(int i = 0; i < arr[1].length(); i++){
	    newStr += "0";
	}
	return new DecimalFormat(newStr);
    }

    /*public void printThem(double scale){
	String str = String.valueOf(scale);
	String [] arr = str.split("\\.");
	String newStr = "";
	do{
	    newStr += "#";
	}while(test.range() / 10 != 0);
	newStr += ".";     
	for(int i = 0; i < arr[1].length(); i++){
	    newStr += "#";
	}
	DecimalFormat df = new DecimalFormat(newStr);
	for(int i = 0; i < p.size(); i++){
	    System.out.printf("%s ", df.format(p.get(i)));
	}
	}*/

}
