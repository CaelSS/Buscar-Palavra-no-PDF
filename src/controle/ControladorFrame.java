package controle;

import visao.Frame;
import visao.PanelBuscaPalavraChave;

public class ControladorFrame {

	static PanelBuscaPalavraChave pbc;
	static ControladorBuscaPalavraChave cbpc;
	
	public ControladorFrame() {
		
	}
	
	public static void main(String[] args) {
		Frame f = new Frame();
		pbc = new PanelBuscaPalavraChave();
		pbc.getButtonPesquisar().setLocation(134, 527);
		pbc.getButtonRemover().setLocation(274, 491);
		pbc.getRadioButtonOu().setLocation(10, 515);
		pbc.getRadioButtonE().setLocation(10, 491);
		pbc.getListaPalavrasChave().setSize(382, 95);
		pbc.getListaPalavrasChave().setLocation(10, 373);
		pbc.getButtonAddPalavraChave().setLocation(284, 328);
		pbc.getTextFieldKey().setLocation(10, 323);
		pbc.getFileChooser().setLocation(10, 10);
		pbc.getPanel().setBounds(10, 10, 387, 278);
		cbpc = new ControladorBuscaPalavraChave(pbc);
		f.setContentPane(pbc);
	}
}
