package gui.standard.form;

import java.security.KeyStore;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class CertificatePasswordDialog extends JDialog {

	private KeyStore keyStore;
	
	private JLabel lblAlias;
	private JLabel lblPassword;
	
	private JComboBox<String> cbxAlias;
	private JPasswordField jpfPassword;
	
	private JButton btnOk;
	private JButton btnCancel;
	
	
	
	public CertificatePasswordDialog(KeyStore keyStore){
		
		this.keyStore = keyStore;
		
		
		
		
	}
	
}
