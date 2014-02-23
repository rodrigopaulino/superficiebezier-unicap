package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import matematica.Ponto;
import matematica.SuperficieBezier;

/**
 * Classe operadora das funções do OpenGL.
 * 
 * @author Rodrigo Paulino Ferreira de Souza
 *
 */
public class Renderer extends KeyAdapter implements GLEventListener {
	private GLU aGLU;
	private GLAutoDrawable aGLAutoDrawable;
	private GL aGL;
	private SuperficieBezier aSBezier;

	public void init(GLAutoDrawable pDrawable) {
		this.aGLU = new GLU();
		this.aGLAutoDrawable = pDrawable;
		this.aGL = pDrawable.getGL();

		pDrawable.setGL(this.aGL);

		this.aGL.glMatrixMode(GL.GL_PROJECTION);
		this.aGLU.gluOrtho2D(2.5, -1.0, -1, 1.0);
		this.aGL.glMatrixMode(GL.GL_MODELVIEW);
	}

	public void display(GLAutoDrawable pDrawable) {
		ArrayList<Ponto> superficie;
		if (this.aSBezier != null) {
			this.aGL.glClear(GL.GL_COLOR_BUFFER_BIT);
			this.aGL.glLoadIdentity();
			this.aGL.glTranslatef(1, 0, 0);
			this.aGL.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
			
			superficie = this.aSBezier.getProximaTransformacao();
			
			this.aGL.glBegin(GL.GL_POINTS);
			this.aGL.glPointSize(13.0f);
			for (int i = 0; i < superficie.size(); i++) {
				this.aGL.glVertex3f(superficie.get(i).getX().floatValue()
					, superficie.get(i).getY().floatValue()
					, superficie.get(i).getZ().floatValue());
			}
			this.aGL.glEnd();
		}
	}

	public void reshape(GLAutoDrawable pDrawable, int pX, int pY, int pWidth,
			int pHeight) {
		this.aGL.glViewport(0, 0, pWidth, pHeight);
	}

	public void displayChanged(GLAutoDrawable pDrawable, boolean pModeChanged,
			boolean pDeviceChanged) {
	}

	public void keyPressed(KeyEvent pEvent) {
		switch (pEvent.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		case KeyEvent.VK_Q:
			System.exit(0);
			break;
		case KeyEvent.VK_P:
			break;
		case KeyEvent.VK_SPACE:
			break;
		}
		this.aGLAutoDrawable.display();
	}
	
	public void setSuperficieBezier(SuperficieBezier pSBezier) {
		this.aSBezier = pSBezier;
	}
}