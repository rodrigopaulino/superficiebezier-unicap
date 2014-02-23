package excecoes;

/**
 * Exce��o levantada quando algum valor negativo � passado indevidamente
 * 
 * @author Rodrigo Paulino Ferreira de Souza
 *
 */
public class ExcecaoNumeroNegativoNaoPermitido extends Exception {
	//~Atributos da Classe---------------------------------------------------------
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Mensagem da Exce��o
	 */
	private String aMessagem;
	
	//~Construtores----------------------------------------------------------------
	/**
	 * Construtor da Exce��o
	 */
	public ExcecaoNumeroNegativoNaoPermitido() {
		super();
		this.aMessagem = "N�mero negativo n�o permitido!";
	}
	
	//~M�todos--------------------------------------------------------------------
	/**
	 * M�todo que retorna a mensagem da Exce��o
	 */
	public String getMessage() {
		return this.aMessagem;
	}
}
