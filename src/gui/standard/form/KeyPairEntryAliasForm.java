package gui.standard.form;

import gui.main.form.MainFrame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.security.KeyStore;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import actions.standard.form.CancelAction;
import actions.standard.form.SaveAction;
import net.miginfocom.swing.MigLayout;

public class KeyPairEntryAliasForm extends JDialog{
	private static final long serialVersionUID = 1L;
	
	private KeyStore keyStore= null;
	private JButton btnSave, btnCancel ;
	private GenerateSelfSignedForm parent;
	private JTextField tfEnterAlias = new JTextField(20);
	private HashMap<String, String> dictionary = new HashMap<String, String>();
	
	
	public KeyPairEntryAliasForm(HashMap<String, String> dic, GenerateSelfSignedForm parent1){
		
		this.parent = parent1;
		setLayout(new MigLayout("fill"));
		this.dictionary = dic ;
		setSize(new Dimension(300, 180));
		setTitle("Generate Self Signed Certificat ");
		setLocationRelativeTo(MainFrame.getInstance());
		setModal(true);
		
		initToolbar();
		
		initGui();
		
	}
public KeyPairEntryAliasForm(HashMap<String, String> dic, GenerateSelfSignedForm parent1, KeyStore keyStore1 ){
		this.keyStore = keyStore1;
		this.parent = parent1;
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
		dataPanel.setLayout(new GridLayout(0,2));

		JPanel buttonsPanel = new JPanel(new GridLayout(0,2));
		btnSave = new JButton(new SaveAction(this));
		btnCancel = new JButton(new CancelAction(this));
		btnSave.setText("Save");
		btnCancel.setText("Cancel");
//
//
		JLabel lblEnterAlias = new JLabel ("Enter Alias:");

//
		dataPanel.add(lblEnterAlias);
		dataPanel.add(tfEnterAlias);


		dataPanel.add(btnSave);
		dataPanel.add(btnCancel);
		add(dataPanel, "dock center");

		//add(buttonsPanel, "dock south");
	}



	public String getEnterAlias() {
		return tfEnterAlias.getText();
	}



	public HashMap<String, String> getDictionary() {
		return dictionary;
	}



	public void setDictionary(HashMap<String, String> dictionary) {
		this.dictionary = dictionary;
	}



	public void disposeCastum() {
		this.parent.dispose();
		this.dispose();
	}



	public GenerateSelfSignedForm getParent() {
		return parent;
	}



	public void setParent(GenerateSelfSignedForm parent) {
		this.parent = parent;
	}
	
}
