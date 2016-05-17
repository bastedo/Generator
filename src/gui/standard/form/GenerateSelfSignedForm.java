package gui.standard.form;

import gui.main.form.MainFrame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.security.KeyStore;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import actions.standard.form.CancelAction;
import actions.standard.form.SaveAction;
import net.miginfocom.swing.MigLayout;

public class GenerateSelfSignedForm extends JDialog{
	private static final long serialVersionUID = 1L;
	

	private JButton btnSave, btnCancel ;
	private JTextField tfValidity = new JTextField(4);
	private JTextField tfSurName = new JTextField(20);
	private JTextField tfGivenName = new JTextField(20);
	private JTextField tfOrganisationUnit = new JTextField(20);
	private JTextField tfOrganisationName = new JTextField(20);
	
	private JTextField tfStateName = new JTextField(20);
	private JTextField tfCountry = new JTextField(3);
	private JTextField tfEmail = new JTextField(20);
	private String passwordKey = null ;
	private KeyStore keyStore= null;
	
	
	public GenerateSelfSignedForm(){

		setLayout(new MigLayout("fill"));

		setSize(new Dimension(400, 300));
		setTitle("Generate Self Signed Certificat ");
		setLocationRelativeTo(MainFrame.getInstance());
		setModal(true);
		setResizable(false);
		
		initToolbar();
		
		initGui();
		
	}
	public GenerateSelfSignedForm(KeyStore keyStore1, String pass){
		this.passwordKey = pass ;
		setLayout(new MigLayout("fill"));
		this.keyStore = keyStore1 ;
		setSize(new Dimension(400, 300));
		setTitle("Generate Self Signed Certificat ");
		setLocationRelativeTo(MainFrame.getInstance());
		setModal(true);
		setResizable(false);
		
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
		dataPanel.setLayout(new GridLayout(8,2));

		JPanel buttonsPanel = new JPanel(new GridLayout(0,2));
		btnSave = new JButton(new SaveAction(this));
		btnCancel = new JButton(new CancelAction(this));
		btnSave.setText("Save");
		btnCancel.setText("Cancel");
//
//
		JLabel lblValidity = new JLabel ("Validity(days):");
		JLabel lblSurName = new JLabel("Surname:");
		JLabel lblOrganisationUnit = new JLabel("Organisation Unit:");
		JLabel lblOrganisationName = new JLabel("Organisation Name:");
		JLabel lblGivenName = new JLabel("Given Name:");
		JLabel lblStateName= new JLabel("StateName:");
		JLabel lblCountry = new JLabel("Country:");
		JLabel lblEmail = new JLabel("Email:");
//
		dataPanel.add(lblValidity);
		dataPanel.add(tfValidity);
		dataPanel.add(lblSurName);
		dataPanel.add(tfSurName);
		dataPanel.add(lblGivenName);
		dataPanel.add(tfGivenName);
		dataPanel.add(lblOrganisationUnit);
		dataPanel.add(tfOrganisationUnit);

		dataPanel.add(lblStateName);
		dataPanel.add(tfStateName);
		dataPanel.add(lblCountry);
		dataPanel.add(tfCountry);
		dataPanel.add(lblEmail);
		dataPanel.add(tfEmail);
		
		
//		dataPanel.add(tfSifra,"wrap");
//		dataPanel.add(lblNaziv);
//		dataPanel.add(tfNaziv);
//		bottomPanel.add(dataPanel);
//
//
//		buttonsPanel.setLayout(new MigLayout("wrap"));
		dataPanel.add(btnSave);
		dataPanel.add(btnCancel);
		add(dataPanel, "dock center");

		//add(buttonsPanel, "dock south");
	}



	public String getTfValidity() {
		return tfValidity.getText();
	}




	public String getTfSurName() {
		return tfSurName.getText();
	}




	public String getTfOrganisationUnit() {
		return tfOrganisationUnit.getText();
	}



	public String getTfOrganisationName() {
		return tfOrganisationName.getText();
	}



	public String getTfGivenName() {
		return tfGivenName.getText();
	}


	public String getTfStateName() {
		return tfStateName.getText();
	}



	public String getTfCountry() {
		return tfCountry.getText();
	}







	public String getPasswordKey() {
		return passwordKey;
	}
	public void setPasswordKey(String passwordKey) {
		this.passwordKey = passwordKey;
	}
	public KeyStore getKeyStore() {
		return keyStore;
	}
	public void setKeyStore(KeyStore keyStore) {
		this.keyStore = keyStore;
	}
	public String getTfEmail() {
		return tfEmail.getText();
	}
	

}
