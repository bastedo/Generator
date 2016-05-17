package gui.main.form;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import actions.main.form.GenerateSelfSignedAction;
import actions.main.form.GenerateSignedAction;


public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public static MainFrame instance;
	private JMenuBar menuBar;

	public MainFrame(){

		setSize(new Dimension(800,600));
		setLocationRelativeTo(null);
		setTitle("Sertifikat generator");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setUpMenu();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (JOptionPane.showConfirmDialog(MainFrame.getInstance(),
						"Da li ste sigurni?", "Pitanje",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					/*
					 * Zatvori konekciju
					 */
					//DBConnection.close();
					System.exit(0);
				}
			}
		});
		
		setJMenuBar(menuBar);

	}

	private void setUpMenu(){
		menuBar = new JMenuBar();

		JMenu orgSemaMenu = new JMenu("Edit");
		orgSemaMenu.setMnemonic(KeyEvent.VK_O);
		JMenuItem GenerateSelfSignedActionMI = new JMenuItem(new GenerateSelfSignedAction());
		orgSemaMenu.add(GenerateSelfSignedActionMI);
		JMenuItem GenerateSignedActionMI = new JMenuItem(new GenerateSignedAction());
		orgSemaMenu.add(GenerateSignedActionMI);


		menuBar.add(orgSemaMenu);
		
		
	}



	public static MainFrame getInstance(){
		if (instance==null)
			instance=new MainFrame();
		return instance;

	}

}