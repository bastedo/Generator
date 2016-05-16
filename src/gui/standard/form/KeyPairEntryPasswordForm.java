package gui.standard.form;

import gui.main.form.MainFrame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import actions.standard.form.CancelAction;
import actions.standard.form.SaveAction;
import net.miginfocom.swing.MigLayout;

public class KeyPairEntryPasswordForm extends JDialog{
	private static final long serialVersionUID = 1L;
	

	private JButton btnSave, btnCancel ;
	private JPasswordField jpfKsPassword = new JPasswordField();
	private JPasswordField jpfConfirmKsPassword = new JPasswordField();
	private HashMap<String, String> dictionary = new HashMap<String, String>();
	
	
	public KeyPairEntryPasswordForm(HashMap<String, String> dic){

		setLayout(new MigLayout("fill"));
		this.dictionary = dic ;
		setSize(new Dimension(300, 180));
		setTitle("Generate Self Signed Certificat ");
		setLocationRelativeTo(MainFrame.getInstance());
		setModal(true);
		
		initToolbar();
		
		initGui();
		
	}
	
	
	
	private void initToolbar(){

		//toolBar = new JToolBar();
		//btnSearch = new JButton(new SearchAction(this));
		//toolBar.add(btnSearch);


		

		//add(toolBar, "dock north");
	}
	
	private void initGui(){
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(0,2));
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new GridLayout(2,1));

		JPanel buttonsPanel = new JPanel(new GridLayout(0,2));
		btnSave = new JButton(new SaveAction(this));
		btnCancel = new JButton(new CancelAction(this));
		btnSave.setText("Save");
		btnCancel.setText("Cancel");
//
//
		JLabel lblEnterNewPassword = new JLabel ("Enter New Password:");
		JLabel lblConfrmNewPassword = new JLabel ("Confrm New Password:");
//
		dataPanel.add(lblEnterNewPassword);
		dataPanel.add(jpfKsPassword);
		dataPanel.add(lblConfrmNewPassword);
		dataPanel.add(jpfConfirmKsPassword);


		dataPanel.add(btnSave);
		dataPanel.add(btnCancel);
		add(dataPanel, "dock center");

		//add(buttonsPanel, "dock south");
	}



	public String getJpfKsPassword() {
		return jpfKsPassword.getText();
	}



	public HashMap<String, String> getDictionary() {
		return dictionary;
	}
	
	public void generate
	



}
