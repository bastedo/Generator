package gui.standard.form;

import gui.main.form.MainFrame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;

import actions.standard.form.CancelAction;
import actions.standard.form.SaveAction;
import net.miginfocom.swing.MigLayout;
import rs.ac.uns.ftn.informatika.ib.security.CertificateGenerator;
import rs.ac.uns.ftn.informatika.ib.security.IssuerData;
import rs.ac.uns.ftn.informatika.ib.security.KeyStoreWriter;
import rs.ac.uns.ftn.informatika.ib.security.SubjectData;

public class KeyPairEntryPasswordForm extends JDialog{
	private static final long serialVersionUID = 1L;
	
	private KeyStore keyStore= null;
	private JButton btnSave, btnCancel ;
	private JPasswordField jpfKsPassword = new JPasswordField();
	private JPasswordField jpfConfirmKsPassword = new JPasswordField();
	private HashMap<String, String> dictionary = new HashMap<String, String>();
	private KeyPairEntryAliasForm parent;
	
	public KeyPairEntryPasswordForm(HashMap<String, String> dic, KeyPairEntryAliasForm parent1){
		this.parent = parent1 ;
		setLayout(new MigLayout("fill"));
		this.dictionary = dic ;
		setSize(new Dimension(300, 120));
		setTitle("Generate Self Signed Certificat ");
		setLocationRelativeTo(MainFrame.getInstance());
		setModal(true);
		
		initToolbar();
		
		initGui();
		
	}
	public KeyPairEntryPasswordForm(HashMap<String, String> dic, KeyPairEntryAliasForm parent1, KeyStore keyStore1){
		this.keyStore = keyStore1;
		this.parent = parent1 ;
		setLayout(new MigLayout("fill"));
		this.dictionary = dic ;
		setSize(new Dimension(300, 120));
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
		dataPanel.setLayout(new GridLayout(3,2));

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
	
	public void generateCertificate() throws ParseException, InvalidKeyException, CertificateException, NoSuchAlgorithmException, NoSuchProviderException, SignatureException, IOException, UnrecoverableKeyException, KeyStoreException {
		CertificateGenerator certi = new CertificateGenerator();
		
		//kreira se self signed sertifikat
		//par kljuceva
		KeyPair keyPair = certi.generateKeyPair();
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = iso8601Formater.parse(iso8601Formater.format(cal.getTime()));
		cal.add(Calendar.DATE, Integer.parseInt(dictionary.get("Validity")));
		Date endDate = iso8601Formater.parse(iso8601Formater.format(cal.getTime()));
		
		//podaci o vlasniku i izdavacu posto je self signed 
		//klasa X500NameBuilder pravi X500Name objekat koji nam treba
		X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
	    builder.addRDN(BCStyle.CN, dictionary.get("SurName")+dictionary.get("GivenName"));
	    builder.addRDN(BCStyle.SURNAME, dictionary.get("Surname"));
	    builder.addRDN(BCStyle.GIVENNAME, dictionary.get("GivenName"));
	    builder.addRDN(BCStyle.O, dictionary.get("OrganisationName"));
	    builder.addRDN(BCStyle.OU, dictionary.get("OrganisationUnit"));
	    builder.addRDN(BCStyle.C,dictionary.get("Country"));
	    builder.addRDN(BCStyle.E, dictionary.get("Email"));
	    //UID (USER ID) je ID korisnika
	    int uid = gen();
	    builder.addRDN(BCStyle.UID, dictionary.get("Alias")+uid );
	    
	    String sn=Integer.toString(uid);
	    IssuerData issuerData = null;
	    
		//kreiraju se podaci za issuer-a
	    if (keyStore==null){
	    	issuerData = new IssuerData(keyPair.getPrivate(), builder.build());
		}else {
			X509Certificate cert = null;
			ArrayList<String> aliases = null;
			try {
				aliases = Collections.list(keyStore.aliases());
			} catch (KeyStoreException e) {
				e.printStackTrace();
			}
			

			try {
				cert = (X509Certificate) keyStore.getCertificate(aliases.get(0));
			} catch (KeyStoreException e) {
				e.printStackTrace();
			}

			X500Name caX500Name = new X500Name(cert.getSubjectX500Principal().getName());
			
			
			System.out.println(dictionary.get("PasswordKey"));
			
			System.out.println(aliases.get(0));
			
			
			//menjamo issuarData podacima o sertifikatu koji ga izdaje, potpisujemo ga njegovim privatnim kljucem
			issuerData = new IssuerData((PrivateKey)keyStore.getKey(
					aliases.get(0),	dictionary.get("PasswordKey").toCharArray())
					, caX500Name);

		}
	    
		//kreiraju se podaci za vlasnika
		SubjectData subjectData = new SubjectData(keyPair.getPublic(), builder.build(), sn, startDate, endDate);
		
		//generise se sertifikat
		X509Certificate cert = certi.generateCertificate(issuerData, subjectData);
		
		System.out.println("ISSUER: " + cert.getIssuerX500Principal().getName());
		System.out.println("SUBJECT: " + cert.getSubjectX500Principal().getName());
		System.out.println("Sertifikat:");
		System.out.println("-------------------------------------------------------");
		System.out.println(cert);
		System.out.println("-------------------------------------------------------");
		//ako validacija nije uspesna desice se exception
		
//		//ovde bi trebalo da prodje
//		cert.verify(keyPair.getPublic());
//		System.out.println("VALIDACIJA USPESNA....");
		
		//ovde bi trebalo da se desi exception, jer validaciju vrsimo drugim kljucem
		//KeyPair anotherPair = generateKeyPair();
		//cert.verify(anotherPair.getPublic());
		
		
		JFileChooser saveDialog = new JFileChooser();
		saveDialog.setSelectedFile(new File(dictionary.get("Alias") + "." + "jks"));
		saveDialog.setFileFilter(new FileNameExtensionFilter( "KeyStore(*.jks)", "jks"));
		
		int saveDialogRetVal = saveDialog.showSaveDialog(null);
		
		if (saveDialogRetVal == JFileChooser.APPROVE_OPTION) {
			
		//kreira se keystore, ucitava ks fajl, dodaje kljuc i sertifikat i sacuvaju se izmene
		KeyStoreWriter keyStoreWriter = new KeyStoreWriter();
		
		File file = saveDialog.getSelectedFile();
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		keyStoreWriter.loadKeyStore(null, dictionary.get("KeyPairPassword").toCharArray());
		keyStoreWriter.write(dictionary.get("Alias"), keyPair.getPrivate(), dictionary.get("KeyPairPassword").toCharArray(), cert);
		keyStoreWriter.saveKeyStore(file.getPath(), dictionary.get("KeyPairPassword").toCharArray());

		String[] path = file.getAbsolutePath().split("jks");
		
		FileOutputStream fos = new FileOutputStream(path[0] + "cer");
		fos.write(cert.getEncoded());
		fos.flush();
		fos.close();
		

	}


	}



	public int gen() { //random petocifreni broj
	    Random r = new Random( System.currentTimeMillis() );
	    return (1 + r.nextInt(2)) * 10000 + r.nextInt(10000);
	}
	public void disposeCastum() {
		this.parent.disposeCastum();
		this.dispose();
	}



	public KeyPairEntryAliasForm getParent() {
		return parent;
	}



	public void setParent(KeyPairEntryAliasForm parent) {
		this.parent = parent;
	}
	public String getJpfKsConfirmPassword() {
		// TODO Auto-generated method stub
		return jpfConfirmKsPassword.getText();
	}
	


}
