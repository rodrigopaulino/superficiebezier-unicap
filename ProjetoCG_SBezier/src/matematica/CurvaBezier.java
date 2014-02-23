package matematica;

import java.math.BigDecimal;
import java.util.ArrayList;

import excecoes.ExcecaoNumeroNegativoNaoPermitido;

/**
 * Classe CurvaBezier
 * 
 * @author Rodrigo Paulino Ferreira de Souza
 *
 */
public class CurvaBezier extends MatematicaBinomial{
	//~Atributos da Classe---------------------------------------------------------
	/**
	 * Pontos de Controle da Curva de B�zier
	 */
	private ArrayList<Ponto> aPontosControle;
	
	/**
	 * Pontos pertencentes � Curva de B�zier em uma determinada precis�o
	 */
	private ArrayList<Ponto> aPontosCurva;
	
	/**
	 * Precis�o, ou seja proximidade, dos pontos da Curva de B�zier
	 */
	private BigDecimal aPrecisao;
	
	//~Construtores----------------------------------------------------------------
	/**
	 * Construtor simples
	 */
	public CurvaBezier() {
	}
	
	/**
	 * Construtor complexo
	 * 
	 * @param pPontosControle Pontos de Controle geradores da Curva de B�zier
	 * @param pPrecisao Proximidade dos pontos da Curva
	 * @throws ExcecaoNumeroNegativoNaoPermitido
	 */
	public CurvaBezier(ArrayList<Ponto> pPontosControle, BigDecimal pPrecisao) throws ExcecaoNumeroNegativoNaoPermitido {
		this.aPontosControle = pPontosControle;
		this.aPrecisao = pPrecisao;
		
		this.gerarCurvaBezier();
	}
	
	//~M�todos--------------------------------------------------------------------
	/**
	 * Atribui valor de Precis�o � Curva
	 * 
	 * @param pPrecisao Precis�o da Curva
	 * @throws ExcecaoNumeroNegativoNaoPermitido
	 */
	public void setPrecisao(BigDecimal pPrecisao) throws ExcecaoNumeroNegativoNaoPermitido {
		this.aPrecisao = pPrecisao;
	}
	
	/**
	 * Retorna o valor da Precis�o da Curva
	 * 
	 * @return Precis�o da Curva
	 */
	public BigDecimal getPrecisao() {
		return this.aPrecisao;
	}

	/**
	 * Atribui valor aos Pontos de Controle geradores da Curva
	 * 
	 * @param pPontosControle Pontos de Controle da Curva
	 * @throws ExcecaoNumeroNegativoNaoPermitido
	 */
	public void setPontosControle(ArrayList<Ponto> pPontosControle) throws ExcecaoNumeroNegativoNaoPermitido {
		this.aPontosControle = pPontosControle;
		
		this.gerarCurvaBezier();
	}
	
	/**
	 * Retorna os pontos da Curva de B�zier gerados
	 * 
	 * @return Pontos da Curva de B�zier
	 */
	public ArrayList<Ponto> getPontosCurva() {
		return this.aPontosCurva;
	}
	
	/**
	 * Gera todas os pontos de uma Curva de B�zier.
	 * 
	 * @throws ExcecaoNumeroNegativoNaoPermitido
	 */
	private void gerarCurvaBezier() throws ExcecaoNumeroNegativoNaoPermitido{
		Ponto p = null;
		Ponto pontoCurva;
		BigDecimal t = new BigDecimal(0);
		BigDecimal coeficienteInterpolacao = null;
		this.aPontosCurva = new ArrayList<Ponto>();
		
		while(t.compareTo(new BigDecimal(1)) <= 0){
			pontoCurva = new Ponto(new BigDecimal(0), new BigDecimal(0), new BigDecimal(0));
			
			for (int i = 0; i < this.aPontosControle.size(); i++) {
				p = this.aPontosControle.get(i);
				
				coeficienteInterpolacao = MatematicaBinomial.coeficienteInterpolacao(this.aPontosControle.size() - 1, i, t);
				
				pontoCurva.setX(pontoCurva.getX().add(coeficienteInterpolacao.multiply(p.getX())));
				pontoCurva.setY(pontoCurva.getY().add(coeficienteInterpolacao.multiply(p.getY())));
				pontoCurva.setZ(pontoCurva.getZ().add(coeficienteInterpolacao.multiply(p.getZ())));
			}
			
			this.aPontosCurva.add(pontoCurva);
			
			t = t.add(this.aPrecisao);
		}
	}
}