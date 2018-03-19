import java.util.ArrayList;
import java.util.Random;


public class element {
	private ArrayList<String> strArr=new ArrayList<String>();
	private String operations="+-*/";
	private String exp="";
	private int result=0;


	public ArrayList<String> getStrArr() {
		return strArr;
	}

	public void setStrArr(ArrayList<String> strArr) {
		this.strArr = strArr;
	}

	element(){

		
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getOperations() {
		return operations;
	}

	public void setOperations(String operations) {
		this.operations = operations;
	}
	
	 public String  random_N() {
		  return String.valueOf(new Random().nextInt(100)+1);
	}
	 public String  random_E() {
		  int rand = new Random().nextInt(4)+1;
		  switch (rand) {
		  	case 1: return "+";
		  	case 2: return "-";
		  	case 3: return "*";
		  	case 4: return "/";
		}
		  return "+";
	}
	  
	

}
