package actions.standard.form;


import java.awt.event.ActionEvent;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.text.ParseException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import gui.standard.form.GenerateSelfSignedForm;
import gui.standard.form.KeyPairEntryAliasForm;
import gui.standard.form.KeyPairEntryPasswordForm;



public class SaveAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private JDialog standardForm;
	
	private String errMessage;
	
	public SaveAction(JDialog standardForm) {
		//putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/save.gif")));
		putValue(SHORT_DESCRIPTION, "Save");
		this.standardForm=standardForm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (standardForm instanceof GenerateSelfSignedForm){
			
			if(validateCertificateForm((GenerateSelfSignedForm)standardForm)){
			
				HashMap<String, String> dictionary =new HashMap<String, String>();
				dictionary.put("Validity" , ((GenerateSelfSignedForm)standardForm).getTfValidity());
				dictionary.put("Surname" , ((GenerateSelfSignedForm)standardForm).getTfSurName());
				dictionary.put("OrganisationUnit" , ((GenerateSelfSignedForm)standardForm).getTfOrganisationUnit());
				dictionary.put("OrganisationName" , ((GenerateSelfSignedForm)standardForm).getTfOrganisationName());
				dictionary.put("GivenName" , ((GenerateSelfSignedForm)standardForm).getTfGivenName());
				dictionary.put("StateName" , ((GenerateSelfSignedForm)standardForm).getTfStateName());
				dictionary.put("Country" , ((GenerateSelfSignedForm)standardForm).getTfCountry());
				dictionary.put("Email" , ((GenerateSelfSignedForm)standardForm).getTfEmail());
				((GenerateSelfSignedForm)standardForm).setVisible(false);
				if (((GenerateSelfSignedForm)standardForm).getKeyStore()==null){
					new KeyPairEntryAliasForm(dictionary, (GenerateSelfSignedForm)standardForm ).setVisible(true);
				}else{
					dictionary.put("PasswordKey" , ((GenerateSelfSignedForm)standardForm).getPasswordKey());
					new KeyPairEntryAliasForm(dictionary, (GenerateSelfSignedForm)standardForm,((GenerateSelfSignedForm)standardForm).getKeyStore()  ).setVisible(true);
				}
			}
			
			
		} else
			try {
				if (standardForm instanceof KeyPairEntryAliasForm){
					
					if(validateAliasForm((KeyPairEntryAliasForm)standardForm)){
					
						HashMap<String, String> dictionary = ((KeyPairEntryAliasForm)standardForm).getDictionary() ;
						dictionary.put("Alias" , ((KeyPairEntryAliasForm)standardForm).getEnterAlias());
						((KeyPairEntryAliasForm)standardForm).setVisible(false);
						if(((KeyPairEntryAliasForm)standardForm).getKeyStore()==null){
							new KeyPairEntryPasswordForm(dictionary, ((KeyPairEntryAliasForm)standardForm)).setVisible(true);
						}else {
							new KeyPairEntryPasswordForm(dictionary, ((KeyPairEntryAliasForm)standardForm),((KeyPairEntryAliasForm)standardForm).getKeyStore() ).setVisible(true);
						}
					}
					
				}else if (standardForm instanceof KeyPairEntryPasswordForm){
					
					if(validatePasword((KeyPairEntryPasswordForm)standardForm)){
					
						HashMap<String, String> dictionary = ((KeyPairEntryPasswordForm)standardForm).getDictionary() ;
						dictionary.put("KeyPairPassword" , ((KeyPairEntryPasswordForm)standardForm).getJpfKsPassword());
						try {
							((KeyPairEntryPasswordForm)standardForm).generateCertificate() ;
						} catch (InvalidKeyException | CertificateException | NoSuchAlgorithmException | NoSuchProviderException
								| SignatureException | ParseException | IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						((KeyPairEntryPasswordForm)standardForm).disposeCastum();
					}
				}
			} catch (UnrecoverableKeyException | KeyStoreException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		
	}

	private boolean validatePasword(KeyPairEntryPasswordForm standardForm2) {
		
		boolean isValid = true;
		errMessage = "";
		if(standardForm2.getJpfKsPassword().trim().isEmpty() || standardForm2.getJpfKsConfirmPassword().trim().isEmpty()){
			isValid = false;
			errMessage+="Popunite sva polja!\n";
		}
		
		if(!standardForm2.getJpfKsPassword().equals(standardForm2.getJpfKsConfirmPassword())){
			isValid = false;
			errMessage+="Lozinke se ne poklapaju!\n";
			
		}
		
		if(!isValid)
			JOptionPane.showMessageDialog(standardForm, errMessage, "Greška!",
					JOptionPane.ERROR_MESSAGE);
		
		return isValid;
	}

	private boolean validateAliasForm(KeyPairEntryAliasForm standardForm2) {

		boolean isValid = true;
		
		if(standardForm2.getEnterAlias().trim().isEmpty()){
			isValid = false;
			JOptionPane.showMessageDialog(standardForm, "Popunite polje!", "Greška!",
					JOptionPane.ERROR_MESSAGE);
		}
		
		return isValid;
		
	}

	private boolean validateCertificateForm(GenerateSelfSignedForm standardForm) {

		boolean isValid = true;
		
		errMessage = "";
		
		Integer validity = null;
		try{
			validity = Integer.parseInt(standardForm.getTfValidity());
		}catch(NumberFormatException e){
			
		}
		if(validity == null){
			isValid = false;
			errMessage+="Validity mora biti broj.\n";
			
		}
		
		if(!isValidEmailAddress(standardForm.getTfEmail())){
			
			isValid = false;
			errMessage+="E-mail nije validan. \n";
		}
		
		if(emptyFields(standardForm)){
			
			isValid = false;
			errMessage+="Popunite sva polja. \n";
		}
		
		if(!isValid)
			JOptionPane.showMessageDialog(standardForm, errMessage, "Greška!",
					JOptionPane.ERROR_MESSAGE);
		
		
		
		return isValid;
		
	}

	private boolean emptyFields(GenerateSelfSignedForm standardForm2) {

		boolean empty = false;
		
		
		
		if(standardForm2.getTfCountry().trim().isEmpty() || standardForm2.getTfEmail().trim().isEmpty() ||
				standardForm2.getTfGivenName().trim().isEmpty() ||
				standardForm2.getTfOrganisationUnit().trim().isEmpty() ||standardForm2.getTfStateName().trim().isEmpty() ||
				standardForm2.getTfSurName().trim().isEmpty() ||standardForm2.getTfValidity().trim().isEmpty()){
			
			empty = true;
			
		}
		
		
		
		return empty;
		
	}

	public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
 }
	
}

