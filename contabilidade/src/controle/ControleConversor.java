package controle;

public class ControleConversor {
	//M�todo que realiza a convers�o de Strings para Double
		//Passagem do par�metro String par
		public double convertDouble(String par){
			//num ser� igual ao valor de par, este sendo convertido para o tipo Double
			double valor = Double.parseDouble(par);
			//Retornando num
			return valor;
		}
		
		public float convertFloat(String par){
			//num ser� igual ao valor de par, este sendo convertido para o tipo Double
			float valor = Float.parseFloat(par);
			//Retornando num
			return valor;
		}
		//M�todo que realiza a convers�o de Strings para Inteiros	
		//Passagem do par�metro String par
		public int convertInt(String par){
			//num ser� igual ao valor de par, este sendo convertido para o tipo Int
			int codigo = Integer.parseInt(par);
			//Retornando num
			return codigo;
		}
		
		
}
