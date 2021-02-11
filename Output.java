package Supply_Demand_Program;

import java.io.*;
import java.util.*;
import java.text.*;

public class Output{
    
    private String filename;
    private File f;
    
    public Output(String name) throws IOException{
	filename = name;
	if(!(checkExtension().equals("tsv") || checkExtension().equals("csv"))){
	    throw new IOException();
	}else{
	    f = new File(filename);
	    if(!f.exists()){
		f.createNewFile();
	    }
	    if(!f.canWrite()){
		f.setWritable(true);
	    }
	    if(!f.canRead()){
		f.setReadable(true);
	    }
	}
    }

    public void writeOut(List<Double> p, List<Double> QD, List<Double> QS, Equilibrium eq) throws IOException{
	FileWriter fw = new FileWriter(f);
	String str = "";
	if(checkExtension().equals("tsv")){
	    str = messageEquilibrium(eq);
	    fw.write(str);
	    str = String.format("%s\t%s\t%s\n", "Price", "Q(D)", "Q(S)");
	    fw.write(str);
	    for(int i = 0; i < p.size(); i++){
		str = String.format("%s\t%s\t%s\n", p.get(i), QD.get(i), QS.get(i));
		fw.write(str);
	    }
	}
	else if(checkExtension().equals("csv")){
	    str = messageEquilibrium(eq);
	    fw.write(str);
	    str = String.format("%s, %s, %s\n", "Price", "Q(D)", "Q(S)");
	    fw.write(str);
	    for(int i = 0; i < p.size(); i++){
		str = String.format("%s, %s, %s\n", p.get(i), QD.get(i), QS.get(i));
		fw.write(str);
	    }
	}
	fw.flush();
	fw.close();
    }

    public String messageEquilibrium(Equilibrium eq){
	String str = "";
	if(eq.hasPositiveEq()){
	    if(checkExtension().equals("tsv")){
		str = String.format("%s%s\n", doSpaces(25), "Positive Equilibrium");
		str += String.format("%s\t%s\t%s\n", eq.getPrice(), eq.getQuantity(), eq.getQuantity());
	    }else if(checkExtension().equals("csv")){
		str = String.format("%s%s%s", doSpaces(13), "Positive Equilibrium", doSpaces(13));
		str += String.format("%s, %s, %s\n", eq.getPrice(), eq.getQuantity(), eq.getQuantity());
	    }
	}
	return str;
    }

    public String doSpaces(int num){
	String str = "";
	for(int i = 0; i < num; i++){
	    str += " ";
	}
	return str;
    }
	    

    public File giveBack(){
	return this.f;
    }

    public String checkExtension(){
	return filename.substring(filename.length() - 3);
    }

}
