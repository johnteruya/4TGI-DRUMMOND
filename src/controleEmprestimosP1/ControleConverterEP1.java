package controleEmprestimosP1;

public class ControleConverterEP1 {

	int numInt;
	String str;
	
	public int convertToInt (String str){
		
		numInt = Integer.parseInt(str);
		
		return numInt;
	}
	
	public String convertToString (int num){
		
		str = Integer.toString(num);
		
		return str;
	}
	
}
