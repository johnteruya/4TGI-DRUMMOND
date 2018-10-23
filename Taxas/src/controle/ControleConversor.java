package controle;

//Classe de conversão
public class ControleConversor {
	//Método que realiza a conversão de Strings para Double
	//Passagem do parâmetro String par
	public double convertDouble(String par){
		//num será igual ao valor de par, este sendo convertido para o tipo Double
		double num = Double.parseDouble(par);
		//Retornando num
		return num;
	}
	//Método que realiza a conversão de Strings para Inteiros	
	//Passagem do parâmetro String par
	public int convertInt(String par){
		//num será igual ao valor de par, este sendo convertido para o tipo Int
		int num = Integer.parseInt(par);
		//Retornando num
		return num;
	}
	
}
