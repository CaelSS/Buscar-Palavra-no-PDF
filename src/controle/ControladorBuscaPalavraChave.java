package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import visao.PanelBuscaPalavraChave;

public class ControladorBuscaPalavraChave implements ActionListener{

	PanelBuscaPalavraChave panelBPC;
	public  DefaultListModel dlm = new DefaultListModel();
	
	public ControladorBuscaPalavraChave(PanelBuscaPalavraChave panelBPC) {
		this.panelBPC = panelBPC;
		addEventos();
	}
	
	
	private void addEventos() {
		panelBPC.getListaPalavrasChave().setModel(dlm);
		panelBPC.getButtonAddPalavraChave().addActionListener(this);
		panelBPC.getButtonPesquisar().addActionListener(this);
		panelBPC.getButtonRemover().addActionListener(this);
		
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == panelBPC.getButtonAddPalavraChave()) {
			addListaPalavra(panelBPC.getTextFieldKey().getText());
		}else if (e.getSource() == panelBPC.getButtonPesquisar()) {
			pesquisaArtigos();
		}else if (e.getSource() == panelBPC.getButtonRemover()) {
			removeChaveDaLista();
		}
		
	}

	private void pesquisaArtigos() {
		File arquivo_selecionado = panelBPC.getFileChooser().getSelectedFile();
		
		String texto_arquivo = null;
		List<String> palavra_listadas = new ArrayList<String>();
		List<String>  palavras_chave = new ArrayList<String>();
		String resultado = "";
		
		if(arquivo_selecionado != null && arquivo_selecionado.isFile()) {
			try {
				texto_arquivo = Files.readString(arquivo_selecionado.toPath());
			} catch (Exception e) {
				System.out.println("Deu ruim da na leitura do arquivo:\n" + e);
			}

			texto_arquivo = texto_arquivo.replace(",", "");
			texto_arquivo = texto_arquivo.replace(".", "");
			texto_arquivo = texto_arquivo.replaceAll("\\r\\n", " ");
			texto_arquivo = texto_arquivo.toLowerCase();

			
			//transformo string[] para uma lista que tem funções melhores para a manipulação de dados
			palavra_listadas = Arrays.asList(texto_arquivo.split("\s+"));
			
			if(panelBPC.getRadioButtonE().isSelected()) {
				for (int i = 0; i < panelBPC.getListaPalavrasChave().getModel().getSize(); i++) {
					palavras_chave.add(panelBPC.getListaPalavrasChave().getModel().getElementAt(i).toString());
				}
			} else if(panelBPC.getRadioButtonOu().isSelected()){
				palavras_chave.add(panelBPC.getListaPalavrasChave().getSelectedValue().toString());
			}
			
			
			
			for (String palavra : palavras_chave) {
				resultado += "\"" + palavra.toString()+ "\" = " + Collections.frequency(palavra_listadas, palavra)+"\n"; //criei um loop pra contar quantas strings iguais as palavras chaves tem
			}
			
			JOptionPane.showMessageDialog(null,resultado);
			/*List<String> filtrado = array_texto
					.stream() //api pra manipular collection convertendo pra ''stream de dados''
					.filter( //ele retorna as palavras digitadas
					palavra -> palavra_chave.equals(palavra))
					.collect(Collectors.toList()); //converte a stream<string> em list<string>
			filtrado.forEach(palavra -> System.out.println(palavra+ " "));*/
			
		//}else if(arquivo_selecionado.isDirectory()) {
			
		}
	}


	private void removeChaveDaLista() {
		String palavra = (String)panelBPC.getListaPalavrasChave().getSelectedValue();
		dlm.removeElement(palavra);
		
	}

	private void addListaPalavra(String chave) {
		if (!dlm.contains(chave)) {
			dlm.addElement(chave);
		}
	
	}	
	
}
