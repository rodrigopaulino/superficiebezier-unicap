package principal;
import excecoes.ExcecaoNumeroNegativoNaoPermitido;
import gui.GUI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import matematica.Ponto;
import matematica.SuperficieBezier;

/**
 * Classe principal.
 * 
 * @author Rodrigo Paulino Ferreira de Souza
 *
 */
public class Main {
	/**
	 * 
	 * @param args
	 */
    public static void main(String args[]) {
		SuperficieBezier superficie;
		FileReader reader;
		BufferedReader leitor;
		String linha;
		ArrayList<Ponto> pontosArquivo = new ArrayList<Ponto>();
		ArrayList<String> transformacoes = new ArrayList<String>();
		ArrayList<ArrayList<Ponto>> pontosControle = new ArrayList<ArrayList<Ponto>>();
		int grauSuperficie;
		
    	try {
    		reader = new FileReader("sbezier.obj");
    		leitor = new BufferedReader(reader);
    		
    		// Lê as diretrizes do arquivo
    		while(true) {
				linha = leitor.readLine();
				
				if (linha == null) {
					break;
				} else if (linha.startsWith("v")) {
					pontosArquivo.add(new Ponto(new BigDecimal(linha.split(" ")[1]),
	    					new BigDecimal(linha.split(" ")[2]), new BigDecimal(linha.split(" ")[3])));
				} else if (linha.startsWith(Character.toString(SuperficieBezier.TIPO_TRANSFORMACAO_ESCALA))
						|| linha.startsWith(Character.toString(SuperficieBezier.TIPO_TRANSFORMACAO_TRANSLACAO))
						|| linha.startsWith(Character.toString(SuperficieBezier.TIPO_TRANSFORMACAO_ROTACAO_X))
						|| linha.startsWith(Character.toString(SuperficieBezier.TIPO_TRANSFORMACAO_ROTACAO_Y))
						|| linha.startsWith(Character.toString(SuperficieBezier.TIPO_TRANSFORMACAO_ROTACAO_Z))
						|| linha.startsWith(Character.toString(SuperficieBezier.TIPO_TRANSFORMACAO_CISALHAMENTO))
						|| linha.startsWith(Character.toString(SuperficieBezier.TIPO_TRANSFORMACAO_ESPELHAMENTO))) {
					transformacoes.add(linha);
				}
			}
    		
    		// Monta a coleção de Pontos de Controle
    		grauSuperficie = new BigDecimal(Math.sqrt((double) pontosArquivo.size())).intValue();
    		
    		for (int i = 0; i < grauSuperficie; i++) {
    			pontosControle.add(new ArrayList<Ponto>());
    			for (int g = 0; g < grauSuperficie; g++) {
        			pontosControle.get(i).add(pontosArquivo.get(i*grauSuperficie + g));
        		}
    		}
    		
    		// Cria a Superfície de Bezier
    		superficie = new SuperficieBezier(pontosControle, new BigDecimal("0.1"));
    		superficie.setTransformacoes(transformacoes);
    		
    		// Cria a tela
    		new GUI(superficie);
		} catch (ExcecaoNumeroNegativoNaoPermitido e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}