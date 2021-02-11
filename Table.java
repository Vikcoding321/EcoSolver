package Supply_Demand_Program;
import java.util.Scanner;
import java.math.*;

public class Table{
    private Scanner input;
    private Equilibrium e;
    private Price p;
    private double ld;
    private double ls;
    private int len;
    private double step;

    public Table(int val, Equilibrium e, double increment){
	this.e = e;
	len = val;
	step = increment;		
    }

    public void printTable(){
	System.out.printf("%s\t%s\t%s\n", "Price", "Q(D)", "Q(S)");
	double value = 0;
	BigDecimal bd = new BigDecimal(value);
	Price p = new Price(value);
	for(int i = 0; i < len; i++){
	    ld = e.equationD().getQuantity(p);
	    ls = e.equationS().getQuantity(p);
	    System.out.printf("%.1f\t%.1f\t%.1f\n", p.getPrice(), ld, ls);
	    bd = bd.add(new BigDecimal(step));
	    p = new Price(bd.doubleValue());
	}
    }
}
