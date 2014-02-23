package matematica;

import java.math.BigDecimal;

import excecoes.ExcecaoNumeroNegativoNaoPermitido;

/**
 * Classe MatematicaBinomial
 * 
 * @author Rodrigo Paulino Ferreira de Souza
 *
 */
public class MatematicaBinomial {
	/**
	 * Calcula o coeficiente binomial de um Bin�mino de Newton.
	 * 
	 * @param n Expoente, ou n�mero da linha, do Bin�mio de Newton
	 * @param k N�mero da coluna do Bin�mio de Newton
	 * @return N�mero combina��es de n elementos agrupados k a k
	 * @throws ExcecaoNumeroNegativoNaoPermitido
	 */
	private static int coeficienteBinomial(int n, int k) throws ExcecaoNumeroNegativoNaoPermitido {
		if (n >= 0 && k >= 0) {
			if (k == 0 || k == n) {
				return 1;
			} else {
				return MatematicaBinomial.coeficienteBinomial(n - 1, k - 1) + MatematicaBinomial.coeficienteBinomial(n - 1 , k);
			}
		} else {
			throw new ExcecaoNumeroNegativoNaoPermitido();
		}
	}
	
	/**
	 * Calcula o coeficiente de interpola��o de um Bin�mio de Newton.
	 * 
	 * @param n Grau do bin�mio
	 * @param i N�mero do coeficiente de interpola��o
	 * @param t Par�metro do bin�mio
	 * @return Coeficiente Binomial, multiplicado por (1 - t) elevado a (n - i)-�sima pot�ncia, multiplicado por t elevado a i-�sima pot�ncia
	 * @throws ExcecaoNumeroNegativoNaoPermitido
	 */
	protected static BigDecimal coeficienteInterpolacao(int n, int i, BigDecimal t) throws ExcecaoNumeroNegativoNaoPermitido {
		return new BigDecimal(MatematicaBinomial.coeficienteBinomial(n, i)).multiply(new BigDecimal(1).subtract(t).pow(n - i).multiply(t.pow(i)));
	}
}
