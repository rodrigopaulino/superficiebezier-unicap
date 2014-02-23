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
	 * Calcula o coeficiente binomial de um Binômino de Newton.
	 * 
	 * @param n Expoente, ou número da linha, do Binômio de Newton
	 * @param k Número da coluna do Binômio de Newton
	 * @return Número combinações de n elementos agrupados k a k
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
	 * Calcula o coeficiente de interpolação de um Binômio de Newton.
	 * 
	 * @param n Grau do binômio
	 * @param i Número do coeficiente de interpolação
	 * @param t Parâmetro do binômio
	 * @return Coeficiente Binomial, multiplicado por (1 - t) elevado a (n - i)-ésima potência, multiplicado por t elevado a i-ésima potência
	 * @throws ExcecaoNumeroNegativoNaoPermitido
	 */
	protected static BigDecimal coeficienteInterpolacao(int n, int i, BigDecimal t) throws ExcecaoNumeroNegativoNaoPermitido {
		return new BigDecimal(MatematicaBinomial.coeficienteBinomial(n, i)).multiply(new BigDecimal(1).subtract(t).pow(n - i).multiply(t.pow(i)));
	}
}
