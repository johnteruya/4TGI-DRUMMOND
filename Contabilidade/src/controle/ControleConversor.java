package controle;

public class ControleConversor {
	//Método que realiza a conversão de Strings para Double
		//Passagem do parâmetro String par
		public double convertDouble(String par){
			//num será igual ao valor de par, este sendo convertido para o tipo Double
			double valor = Double.parseDouble(par);
			//Retornando num
			return valor;
		}
		
		public float convertFloat(String par){
			//num será igual ao valor de par, este sendo convertido para o tipo Double
			float valor = Float.parseFloat(par);
			//Retornando num
			return valor;
		}
		//Método que realiza a conversão de Strings para Inteiros	
		//Passagem do parâmetro String par
		public int convertInt(String par){
			//num será igual ao valor de par, este sendo convertido para o tipo Int
			int codigo = Integer.parseInt(par);
			//Retornando num
			return codigo;
		}
		
		
}
