package gui.standard.form;

import gui.main.form.MainFrame;

import java.awt.Dimension;
import java.awt.GridLayout;

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
	private JTextField tfCommonName = new JTextField(20);
	private JTextField tfOrganisationUnit = new JTextField(20);
	private JTextField tfOrganisationName = new JTextField(20);
	private JTextField tfLocalityName = new JTextField(20);
	private JTextField tfStateName = new JTextField(20);
	private JTextField tfCountry = new JTextField(3);
	private JTextField tfEmail = new JTextField(20);
	
	
	
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
		JLabel lblCommonName = new JLabel("CommonName:");
		JLabel lblOrganisationUnit = new JLabel("OrganisationUnit:");
		JLabel lblOrganisationName = new JLabel("OrganisationName:");
		JLabel lblLocalityName = new JLabel("LocalityName:");
		JLabel lblStateName= new JLabel("StateName:");
		JLabel lblCountry = new JLabel("Country:");
		JLabel lblEmail = new JLabel("Email:");
//
		dataPanel.add(lblValidity);
		dataPanel.add(tfValidity);
		dataPanel.add(lblCommonName);
		dataPanel.add(tfCommonName);
		dataPanel.add(lblOrganisationUnit);
		dataPanel.add(tfOrganisationUnit);
		dataPanel.add(lblLocalityName);
		dataPanel.add(tfLocalityName);
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




	public String getTfCommonName() {
		return tfCommonName.getText();
	}




	public String getTfOrganisationUnit() {
		return tfOrganisationUnit.getText();
	}



	public String getTfOrganisationName() {
		return tfOrganisationName.getText();
	}



	public String getTfLocalityName() {
		return tfLocalityName.getText();
	}


	public String getTfStateName() {
		return tfStateName.getText();
	}



	public String getTfCountry() {
		return tfCountry.getText();
	}







	public String getTfEmail() {
		return tfEmail.getText();
	}
	

}
