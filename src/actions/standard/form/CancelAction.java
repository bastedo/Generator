
package actions.standard.form;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.text.ParseException;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

import gui.standard.form.GenerateSelfSignedForm;
import gui.standard.form.KeyPairEntryAliasForm;
import gui.standard.form.KeyPairEntryPasswordForm;

public class CancelAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	private JDialog standardForm;

	public CancelAction(JDialog standardForm) {
		//putValue(SMALL_ICON, new ImageIcon(getClass().getResource("/img/remove.gif")));
		putValue(SHORT_DESCRIPTION, "Poništi");
		this.standardForm=standardForm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (standardForm instanceof GenerateSelfSignedForm){
			standardForm.dispose();

		}else if (standardForm instanceof KeyPairEntryAliasForm){
			((KeyPairEntryPasswordForm)standardForm).getParent().setVisible(true);
			standardForm.dispose();

		}else if (standardForm instanceof KeyPairEntryPasswordForm){
			((KeyPairEntryPasswordForm)standardForm).getParent().setVisible(true);
			standardForm.dispose();
		}
		
	}
}
