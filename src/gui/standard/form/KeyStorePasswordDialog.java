package gui.standard.form;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.KeyStore;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import net.miginfocom.swing.MigLayout;
import rs.ac.uns.ftn.informatika.ib.security.KeyStoreReader;


public class KeyStorePasswordDialog extends JDialog {

	private JPanel panel;
	private JPasswordField jpfPassword;
	private JButton btnOk;
	private JButton btnCancel;
	private JLabel lblPassword;
	
	private String keyStorePath;
	
	private KeyStore keyStore;
	
	public KeyStorePasswordDialog(String keyStorePath){
		
		init();
		
		this.keyStorePath = keyStorePath;
		
		lblPassword = new JLabel("Enter Key Store Password:");
		jpfPassword = new JPasswordField(20);
		
		btnOk = new JButton("Ok");
		btnCancel = new JButton("Cancel");
		
		addListeners();
		
		this.panel = new JPanel(new MigLayout());
		
		panel.add(lblPassword);
		panel.add(jpfPassword,"wrap");
		panel.add(btnCancel,"gapleft 20");
		panel.add(btnOk,"gapleft 20");
		
		this.add(panel);
		
	}


	private void addListeners() {
		KeyStorePasswordDialog kspDialog = this;
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				kspDialog.setVisible(false);
				
			}
		});
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jpfPassword.getPassword().length == 0) {
					JOptionPane.showMessageDialog(kspDialog, "Unesite šifru!", "Upozorenje!",
							JOptionPane.WARNING_MESSAGE);
				} else {
					
					
					KeyStoreReader reader = new KeyStoreReader();
					keyStore = reader.readKeyStore(keyStorePath, jpfPassword.getPassword());
					
					if (keyStore != null){
						setVisible(false);
						new GenerateSelfSignedForm(keyStore, jpfPassword.getPassword().toString()).setVisible(true);
						
					}else
						JOptionPane.showMessageDialog(kspDialog,
								"Pogrešna šifra!", "Warning!",
								JOptionPane.WARNING_MESSAGE);

					
				}
				
			}
		});
		
	}


	private void init() {
		setSize(300, 100);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)(screenSize.getWidth()/2 - getSize().getWidth()/2);
		int height = (int)(screenSize.getHeight()/2 - getSize().getHeight()/2);
		
		setLocation(width, height);
		setModal(true);
		
	}
	
	
}
