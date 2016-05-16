package actions.main.form;


import gui.standard.form.GenerateSelfSignedForm;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.KeyStroke;


public class GenerateSelfSignedAction  extends AbstractAction{
	private static final long serialVersionUID = 1L;
	
	public GenerateSelfSignedAction() {
		KeyStroke ctrlDKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK);
		putValue(ACCELERATOR_KEY,ctrlDKeyStroke);
		putValue(SHORT_DESCRIPTION, "GenerateSelfSigned");
		putValue(NAME, "GenerateSelfSigned");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {	
		GenerateSelfSignedForm form = new GenerateSelfSignedForm();
		form.setVisible(true);
	}
}


