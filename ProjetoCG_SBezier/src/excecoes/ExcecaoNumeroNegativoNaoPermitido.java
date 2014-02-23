package excecoes;

/**
 * Exceção levantada quando algum valor negativo é passado indevidamente
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
	 * Mensagem da Exceção
	 */
	private String aMessagem;
	
	//~Construtores----------------------------------------------------------------
	/**
	 * Construtor da Exceção
	 */
	public ExcecaoNumeroNegativoNaoPermitido() {
		super();
		this.aMessagem = "Número negativo não permitido!";
	}
	
	//~Métodos--------------------------------------------------------------------
	/**
	 * Método que retorna a mensagem da Exceção
	 */
	public String getMessage() {
		return this.aMessagem;
	}
}
