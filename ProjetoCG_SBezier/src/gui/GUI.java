package gui;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.swing.JFrame;

import matematica.SuperficieBezier;

/**
 * Classe que cria a tela a ser exibida.
 * 
 * @author Rodrigo Paulino Ferreira de Souza
 *
 */
public class GUI extends JFrame {
	//~Atributos da Classe---------------------------------------------------------
	/**
	 * 
	 */
	private static final long serialVersionUID = -4898514470689193891L;

	//~Construtores----------------------------------------------------------------
	/**
	 * Construtor de uma tela que exibirá a Superfície de Bézier
	 * 
	 * @param pSBezier Superfície de Bézier a ser impressa na tela
	 */
	public GUI(SuperficieBezier pSBezier) {
		GLCapabilities glCapabilities;
		GLCanvas glCanvas;
		Renderer renderer;
		
		this.setTitle("ProjetoCG - Superfície de Bézier - Rodrigo Paulino");
		this.setSize(700, 500);
		this.setLocation(0,0);
		this.setDefaultCloseOperation(GUI.EXIT_ON_CLOSE);
		
		glCapabilities = new GLCapabilities();
		glCapabilities.setRedBits(8);
		glCapabilities.setBlueBits(8);
		glCapabilities.setGreenBits(8);
		glCapabilities.setAlphaBits(8);
		
		renderer = new Renderer();
		renderer.setSuperficieBezier(pSBezier);
		
		glCanvas = new GLCanvas(glCapabilities);
		glCanvas.addGLEventListener(renderer);
		glCanvas.addKeyListener(renderer);
		
		this.add(glCanvas);
		this.setVisible(true);
	}
}