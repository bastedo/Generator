package actions.standard.form;


import java.awt.event.ActionEvent;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import gui.standard.form.GenerateSelfSignedForm;
import gui.standard.form.KeyPairEntryAliasForm;
import gui.standard.form.KeyPairEntryPasswordForm;



public class SaveAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private JDialog standardForm;
	
	public SaveAction(JDialog standardForm) {
		//putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/save.gif")));
		putValue(SHORT_DESCRIPTION, "Save");
		this.standardForm=standardForm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (standardForm instanceof GenerateSelfSignedForm){
			HashMap<String, String> dictionary =new HashMap<String, String>();
			dictionary.put("Validity" , ((GenerateSelfSignedForm)standardForm).getTfValidity());
			dictionary.put("CommonName" , ((GenerateSelfSignedForm)standardForm).getTfCommonName());
			dictionary.put("OrganisationUnit" , ((GenerateSelfSignedForm)standardForm).getTfOrganisationUnit());
			dictionary.put("OrganisationName" , ((GenerateSelfSignedForm)standardForm).getTfOrganisationName());
			dictionary.put("LocalityName" , ((GenerateSelfSignedForm)standardForm).getTfLocalityName());
			dictionary.put("StateName" , ((GenerateSelfSignedForm)standardForm).getTfStateName());
			dictionary.put("Country" , ((GenerateSelfSignedForm)standardForm).getTfCountry());
			dictionary.put("Email" , ((GenerateSelfSignedForm)standardForm).getTfEmail());
			new KeyPairEntryAliasForm(dictionary).setVisible(true); 
			
		}else if (standardForm instanceof KeyPairEntryAliasForm){
			HashMap<String, String> dictionary = ((KeyPairEntryAliasForm)standardForm).getDictionary() ;
			dictionary.put("Alias" , ((KeyPairEntryAliasForm)standardForm).getEnterAlias());
			new KeyPairEntryPasswordForm(dictionary).setVisible(true);
		}else if (standardForm instanceof KeyPairEntryPasswordForm){
			HashMap<String, String> dictionary = ((KeyPairEntryPasswordForm)standardForm).getDictionary() ;
			dictionary.put("KeyPairPassword" , ((KeyPairEntryPasswordForm)standardForm).getJpfKsPassword());
			
		}
		
		
	}
}

