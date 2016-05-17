package actions.main.form;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import gui.standard.form.KeyStorePasswordDialog;

public class GenerateSignedAction extends AbstractAction {

	public GenerateSignedAction() {
		KeyStroke ctrlDKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK);
		putValue(ACCELERATOR_KEY,ctrlDKeyStroke);
		putValue(SHORT_DESCRIPTION, "Generate Signed Certificate");
		putValue(NAME, "Generate Signed Certificate");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String keyStorePath = openKeyStoreDialog();
		
		KeyStorePasswordDialog kspDialog = new KeyStorePasswordDialog(keyStorePath);
		kspDialog.setVisible(true);
		

	}

	
	public static String openKeyStoreDialog() {
		String path = null;

		JFileChooser openDialog = new JFileChooser();
		openDialog.setFileFilter(new FileNameExtensionFilter(
				"KeyStore(*.jks)", "jks"));

		int openDialogRetVal = openDialog.showOpenDialog(null);

		if (openDialogRetVal == JFileChooser.APPROVE_OPTION) {
			File file = openDialog.getSelectedFile();
			path = file.getPath();
		}

		return path;
	}
}
