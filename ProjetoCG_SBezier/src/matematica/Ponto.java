package matematica;

import java.math.BigDecimal;

/**
 * Classe representativa de um ponto na tela
 * 
 * @author Rodrigo Paulino Ferreira de Souza
 *
 */
public class Ponto {
	//~Atributos da Classe---------------------------------------------------------
	/**
	 * Coordenada X do espaço
	 */
	private BigDecimal aX;
	
	/**
	 * Coordenada Y do espaço
	 */
	private BigDecimal aY;
	
	/**
	 * Coordenada Z do espaço
	 */
	private BigDecimal aZ;
	
	//~Construtores----------------------------------------------------------------
	/**
	 * Construtor de um Ponto
	 * 
	 * @param pX Coordenada X
	 * @param pY Coordenada Y
	 * @param pZ Coordenada Z
	 */
	public Ponto (BigDecimal pX, BigDecimal pY, BigDecimal pZ) {
		this.aX = pX;
		this.aY = pY;
		this.aZ = pZ;
	}
	
	//~Métodos--------------------------------------------------------------------
	/**
	 * Método que retorna o valor X do ponto
	 * 
	 * @return Coordenada X
	 */
	public BigDecimal getX() {
		return aX;
	}
	
	/**
	 * Método que retorna o valor Y do ponto
	 * 
	 * @return Coordenada Y
	 */
	public BigDecimal getY() {
		return aY;
	}
	
	/**
	 * Método que retorna o valor Z do ponto
	 * 
	 * @return Coordenada Z
	 */
	public BigDecimal getZ() {
		return aZ;
	}
	
	/**
	 * Atribui valor à coordenada X do ponto
	 * 
	 * @param pX Coordenada X a ser definida
	 */
	public void setX(BigDecimal pX) {
		this.aX = pX;
	}
	
	/**
	 * Atribui valor à coordenada Y do ponto
	 * 
	 * @param pY Coordenada Y a ser definida
	 */
	public void setY(BigDecimal pY) {
		this.aY = pY;
	}
	
	/**
	 * Atribui valor à coordenada Z do ponto
	 * 
	 * @param pZ Coordenada Z a ser definida
	 */
	public void setZ(BigDecimal pZ) {
		this.aZ = pZ;
	}
}