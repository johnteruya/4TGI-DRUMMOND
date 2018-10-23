package controle;

//Classe de convers�o
public class ControleConversor {
	//M�todo que realiza a convers�o de Strings para Double
	//Passagem do par�metro String par
	public double convertDouble(String par){
		//num ser� igual ao valor de par, este sendo convertido para o tipo Double
		double num = Double.parseDouble(par);
		//Retornando num
		return num;
	}
	//M�todo que realiza a convers�o de Strings para Inteiros	
	//Passagem do par�metro String par
	public int convertInt(String par){
		//num ser� igual ao valor de par, este sendo convertido para o tipo Int
		int num = Integer.parseInt(par);
		//Retornando num
		return num;
	}
	
}
