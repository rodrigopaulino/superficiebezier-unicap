package matematica;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

import excecoes.ExcecaoNumeroNegativoNaoPermitido;

/**
 * Classe SuperficieBezier
 * 
 * @author Rodrigo Paulino Ferreira de Souza
 *
 */
public class SuperficieBezier extends MatematicaBinomial{
	//~Constantes------------------------------------------------------------------
	/**
	 * Constante que define o tipo da transforma��o 'Escala'
	 */
	public static final char TIPO_TRANSFORMACAO_ESCALA = 's';
	
	/**
	 * Constante que define o tipo da transforma��o 'Transla��o'
	 */
	public static final char TIPO_TRANSFORMACAO_TRANSLACAO = 't';
	
	/**
	 * Constante que define o tipo da transforma��o 'Rota��o' em torno do eixo X
	 */
	public static final char TIPO_TRANSFORMACAO_ROTACAO_X = 'x';
	
	/**
	 * Constante que define o tipo da transforma��o 'Rota��o' em torno do eixo Y
	 */
	public static final char TIPO_TRANSFORMACAO_ROTACAO_Y = 'y';
	
	/**
	 * Constante que define o tipo da transforma��o 'Rota��o' em torno do eixo Z
	 */
	public static final char TIPO_TRANSFORMACAO_ROTACAO_Z = 'z';
	
	/**
	 * Constante que define o tipo da transforma��o 'Cisalhamento'
	 */
	public static final char TIPO_TRANSFORMACAO_CISALHAMENTO = 'c';
	
	/**
	 * Constante que define o tipo da transforma��o 'Espelhamento'
	 */
	public static final char TIPO_TRANSFORMACAO_ESPELHAMENTO = 'e';
	
	//~Atributos da Classe---------------------------------------------------------
	/**
	 * Pontos de Controle da Superf�cie de B�zier
	 */
	private ArrayList<ArrayList<Ponto>> aPontosControle;
	
	/**
	 * Pontos pertencentes � Superf�cie de B�zier em uma determinada precis�o
	 */
	private ArrayList<Ponto> aPontosSuperficie;
	
	/**
	 * Pontos pertencentes � Superf�cie de B�zier que sofrem transforma��o
	 */
	private ArrayList<Ponto> aPontosSuperficieOriginais;
	
	/**
	 * Precis�o, ou seja proximidade, dos pontos da Superf�cie de B�zier
	 */
	private BigDecimal aPrecisao;
	
	/**
	 * Cole��o de todas as linhas de correspondentes �s transforma��es lidas no arquivo
	 */
	private ArrayList<String> aTransformacoes;
	
	/**
	 * Iterator que cont�m todas as linhas de transforma��es lidas no arquivo
	 */
	private Iterator<String> aIteratorTransformacoes;
	
	//~Construtores----------------------------------------------------------------
	/**
	 * Construtor simples
	 */
	public SuperficieBezier() {
	}
	
	/**
	 * Construtor complexo
	 * 
	 * @param pPontosControle Pontos de Controle geradores da Superf�cie de B�zier
	 * @param pPrecisao Proximidade dos pontos da Superf�cie
	 * @throws ExcecaoNumeroNegativoNaoPermitido
	 */
	public SuperficieBezier(ArrayList<ArrayList<Ponto>> pPontosControle, BigDecimal pPrecisao) throws ExcecaoNumeroNegativoNaoPermitido {
		this.aPontosControle = pPontosControle;
		this.aPrecisao = pPrecisao;
		
		this.gerarSuperficieBezier();
	}
	
	//~M�todos--------------------------------------------------------------------
	/**
	 * Define a cole��o de transforma��es a serem feitas na Superf�cie
	 * 
	 * @param pTransformacoes Cole��o de Transforma��es
	 */
	public void setTransformacoes(ArrayList<String> pTransformacoes) {
		this.aTransformacoes = pTransformacoes;
	}
	
	/**
	 * Atribui valor de Precis�o � Superf�cie
	 * 
	 * @param pPrecisao Precis�o da Superf�cie
	 * @throws ExcecaoNumeroNegativoNaoPermitido
	 */
	public void setPrecisao(BigDecimal pPrecisao) throws ExcecaoNumeroNegativoNaoPermitido {
		this.aPrecisao = pPrecisao;
	}
	
	/**
	 * Retorna o valor da Precis�o da Superf�cie
	 * 
	 * @return Precis�o da Superf�cie
	 */
	public BigDecimal getPrecisao() {
		return this.aPrecisao;
	}

	/**
	 * Atribui valor aos Pontos de Controle geradores da Superf�cie
	 * 
	 * @param pPontosControle Pontos de Controle da Superf�cie
	 * @throws ExcecaoNumeroNegativoNaoPermitido
	 */
	public void setPontosControle(ArrayList<ArrayList<Ponto>> pPontosControle) throws ExcecaoNumeroNegativoNaoPermitido {
		this.aPontosControle = pPontosControle;
		
		this.gerarSuperficieBezier();
	}
	
	/**
	 * Retorna os pontos da Superf�cie de B�zier gerados
	 * 
	 * @return Pontos da Superf�cie de B�zier
	 */
	public ArrayList<Ponto> getPontosSuperficie() {
		return this.aPontosSuperficie;
	}
	
	/**
	 * M�todo respons�vel por realizar a pr�xima transforma��o dada pelo arquivo, come�ando com a Superf�cie original
	 * 
	 * @return Pontos da Superf�cie de B�zier com a seguinte transforma��o
	 */
	public ArrayList<Ponto> getProximaTransformacao() {
		ArrayList<Ponto> superficieTransformada = null;
		Ponto pontoTransformado = null;
		String linhaTransformacao = "";
		String[] valoresTransformacao = null;
		char tpTransformacao;
		BigDecimal valor1, valor2, valor3;
		
		if (this.aTransformacoes.isEmpty()) {
			return null;
		} else if (this.aIteratorTransformacoes == null || !this.aIteratorTransformacoes.hasNext()) {
			this.aIteratorTransformacoes = this.aTransformacoes.iterator();
			this.aPontosSuperficie = new ArrayList<Ponto>(this.aPontosSuperficieOriginais);
			return this.aPontosSuperficie;
		}
		
		linhaTransformacao = this.aIteratorTransformacoes.next();
		valoresTransformacao = linhaTransformacao.split(" ");
		tpTransformacao = valoresTransformacao[0].charAt(0);
		
		switch(tpTransformacao) {
		case SuperficieBezier.TIPO_TRANSFORMACAO_ESCALA:
			superficieTransformada = new ArrayList<Ponto>();
			
			valor1 = new BigDecimal(valoresTransformacao[1]);
			valor2 = new BigDecimal(valoresTransformacao[2]);
			valor3 = new BigDecimal(valoresTransformacao[3]);
			
			for (Ponto pontoSuperficie : this.aPontosSuperficie) {
				pontoTransformado = new Ponto(pontoSuperficie.getX().multiply(valor1)
						, pontoSuperficie.getY().multiply(valor2), pontoSuperficie.getZ().multiply(valor3));
				superficieTransformada.add(pontoTransformado);
			}
			break;
		case SuperficieBezier.TIPO_TRANSFORMACAO_TRANSLACAO:
			superficieTransformada = new ArrayList<Ponto>();
			
			valor1 = new BigDecimal(valoresTransformacao[1]);
			valor2 = new BigDecimal(valoresTransformacao[2]);
			valor3 = new BigDecimal(valoresTransformacao[3]);
			
			for (Ponto pontoSuperficie : this.aPontosSuperficie) {
				pontoTransformado = new Ponto(pontoSuperficie.getX().add(valor1)
						, pontoSuperficie.getY().add(valor2), pontoSuperficie.getZ().add(valor3));
				superficieTransformada.add(pontoTransformado);
			}
			break;
		case SuperficieBezier.TIPO_TRANSFORMACAO_ROTACAO_X:
			superficieTransformada = new ArrayList<Ponto>();
			
			valor1 = new BigDecimal(valoresTransformacao[1]);
			
			for (Ponto pontoSuperficie : this.aPontosSuperficie) {
				pontoTransformado = new Ponto(pontoSuperficie.getX()
						, pontoSuperficie.getY().multiply(new BigDecimal(Math.cos(valor1.doubleValue())))
						.subtract(pontoSuperficie.getZ().multiply(new BigDecimal(Math.sin(valor1.doubleValue()))))
						, pontoSuperficie.getY().multiply(new BigDecimal(Math.sin(valor1.doubleValue())))
						.add(pontoSuperficie.getZ().multiply(new BigDecimal(Math.cos(valor1.doubleValue())))));
				superficieTransformada.add(pontoTransformado);
			}
			break;
		case SuperficieBezier.TIPO_TRANSFORMACAO_ROTACAO_Y:
			superficieTransformada = new ArrayList<Ponto>();
			
			valor1 = new BigDecimal(valoresTransformacao[1]);
			
			for (Ponto pontoSuperficie : this.aPontosSuperficie) {
				pontoTransformado = new Ponto(pontoSuperficie.getX().multiply(new BigDecimal(Math.cos(valor1.doubleValue())))
						.add(pontoSuperficie.getZ().multiply(new BigDecimal(Math.sin(valor1.doubleValue()))))
						, pontoSuperficie.getY()
						, pontoSuperficie.getZ().multiply(new BigDecimal(Math.cos(valor1.doubleValue())))
						.subtract(pontoSuperficie.getX().multiply(new BigDecimal(Math.sin(valor1.doubleValue())))));
				superficieTransformada.add(pontoTransformado);
			}
			break;
		case SuperficieBezier.TIPO_TRANSFORMACAO_ROTACAO_Z:
			superficieTransformada = new ArrayList<Ponto>();
			
			valor1 = new BigDecimal(valoresTransformacao[1]);
			
			for (Ponto pontoSuperficie : this.aPontosSuperficie) {
				pontoTransformado = new Ponto(pontoSuperficie.getX().multiply(new BigDecimal(Math.cos(valor1.doubleValue())))
						.subtract(pontoSuperficie.getY().multiply(new BigDecimal(Math.sin(valor1.doubleValue()))))
						, pontoSuperficie.getX().multiply(new BigDecimal(Math.sin(valor1.doubleValue())))
						.add(pontoSuperficie.getY().multiply(new BigDecimal(Math.cos(valor1.doubleValue()))))
						, pontoSuperficie.getZ());
				superficieTransformada.add(pontoTransformado);
			}
			break;
		case SuperficieBezier.TIPO_TRANSFORMACAO_CISALHAMENTO:
			superficieTransformada = new ArrayList<Ponto>();
			
			valor1 = new BigDecimal(valoresTransformacao[1]);
			valor2 = new BigDecimal(valoresTransformacao[2]);
			valor3 = new BigDecimal(valoresTransformacao[3]);
			
			for (Ponto pontoSuperficie : this.aPontosSuperficie) {
				if (valor1.equals(new BigDecimal(0))) {
					pontoTransformado = new Ponto(pontoSuperficie.getX()
							, pontoSuperficie.getY().add(pontoSuperficie.getX().multiply(valor2))
							, pontoSuperficie.getZ().add(pontoSuperficie.getX().multiply(valor3)));
				} else if (valor2.equals(new BigDecimal(0))) {
					pontoTransformado = new Ponto(pontoSuperficie.getX().add(pontoSuperficie.getY().multiply(valor1))
							, pontoSuperficie.getY()
							, pontoSuperficie.getZ().add(pontoSuperficie.getY().multiply(valor3)));
				} else if (valor3.equals(new BigDecimal(0))) {
					pontoTransformado = new Ponto(pontoSuperficie.getX().add(pontoSuperficie.getZ().multiply(valor1))
							, pontoSuperficie.getY().add(pontoSuperficie.getZ().multiply(valor2))
							, pontoSuperficie.getZ());
				}
				superficieTransformada.add(pontoTransformado);
			}
			break;
		case SuperficieBezier.TIPO_TRANSFORMACAO_ESPELHAMENTO:
			superficieTransformada = new ArrayList<Ponto>();
			
			valor1 = new BigDecimal(valoresTransformacao[1]);
			valor2 = new BigDecimal(valoresTransformacao[2]);
			valor3 = new BigDecimal(valoresTransformacao[3]);
			
			for (Ponto pontoSuperficie : this.aPontosSuperficie) {
				if (valor1.equals(new BigDecimal(0))) {
					pontoTransformado = new Ponto(pontoSuperficie.getX().multiply(new BigDecimal(-1))
							, pontoSuperficie.getY(), pontoSuperficie.getZ());
				} else if (valor2.equals(new BigDecimal(0))) {
					pontoTransformado = new Ponto(pontoSuperficie.getX()
							, pontoSuperficie.getY().multiply(new BigDecimal(-1)), pontoSuperficie.getZ());
				} else if (valor3.equals(new BigDecimal(0))) {
					pontoTransformado = new Ponto(pontoSuperficie.getX(), pontoSuperficie.getY()
							, pontoSuperficie.getZ().multiply(new BigDecimal(-1)));
				}
			}
			break;
		}
		
		return superficieTransformada;
	}
	
	/**
	 * Gera todas os pontos de uma Superf�cie de B�zier.
	 * 
	 * @throws ExcecaoNumeroNegativoNaoPermitido
	 */
	private void gerarSuperficieBezier() throws ExcecaoNumeroNegativoNaoPermitido {
		Ponto p = null;
		Ponto pontoSuperficie;
		BigDecimal s;
		BigDecimal t = new BigDecimal(0);
		BigDecimal coeficienteInterpolacao = null;
		this.aPontosSuperficieOriginais = new ArrayList<Ponto>();
		ArrayList<Ponto> pontosControleAux = null;
		Ponto pontoControleAux = null;
		
		while (t.compareTo(new BigDecimal(1)) <= 0) {
			s = new BigDecimal(0);
			pontosControleAux = new ArrayList<Ponto>();
			
			for (int n = 0; n < this.aPontosControle.size(); n++) {
				pontoControleAux = new Ponto(new BigDecimal(0), new BigDecimal(0), new BigDecimal(0));
				
				for (int m = 0; m < this.aPontosControle.get(n).size(); m++) {
					p = this.aPontosControle.get(n).get(m);
					
					coeficienteInterpolacao = MatematicaBinomial.coeficienteInterpolacao(this.aPontosControle.size() - 1, m, t);
					
					pontoControleAux.setX(pontoControleAux.getX().add(coeficienteInterpolacao.multiply(p.getX())));
					pontoControleAux.setY(pontoControleAux.getY().add(coeficienteInterpolacao.multiply(p.getY())));
					pontoControleAux.setZ(pontoControleAux.getZ().add(coeficienteInterpolacao.multiply(p.getZ())));
				}
				
				pontosControleAux.add(pontoControleAux);
			}
			
			while (s.compareTo(new BigDecimal(1)) <= 0) {
				pontoSuperficie = new Ponto(new BigDecimal(0), new BigDecimal(0), new BigDecimal(0));
				
				for (int n = 0; n < pontosControleAux.size(); n++) {
					p = pontosControleAux.get(n);
					
					coeficienteInterpolacao = MatematicaBinomial.coeficienteInterpolacao(this.aPontosControle.size() - 1, n, s);
					
					pontoSuperficie.setX(pontoSuperficie.getX().add(coeficienteInterpolacao.multiply(p.getX())));
					pontoSuperficie.setY(pontoSuperficie.getY().add(coeficienteInterpolacao.multiply(p.getY())));
					pontoSuperficie.setZ(pontoSuperficie.getZ().add(coeficienteInterpolacao.multiply(p.getZ())));
				}
				this.aPontosSuperficieOriginais.add(pontoSuperficie);
				s = s.add(this.aPrecisao);
			}
			t = t.add(this.aPrecisao);
		}
		
		this.aPontosSuperficie = new ArrayList<Ponto>(this.aPontosSuperficieOriginais);
	}
}