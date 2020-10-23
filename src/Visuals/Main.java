package Visuals;

import java.awt.Component;
import java.awt.Event;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;
import Controler.Consumption;
import Utils.ExtensionFileFilter;
import Utils.JavaFileView;
import Utils.LabelAccessory;
import Utils.UserInterfaceSuport;
import javax.swing.JLabel;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JMenuBar mainMenuBar = null;

	private JMenu FileMenu = null;

	private JMenuItem InsertHouseMenuItem = null;

	private JMenuItem OpenFileMenuItem = null;

	private JMenuItem SaveMenuItem = null;

	private JMenu ReportsMenu = null;

	private JMenuItem biggerConnsumersjMenuItem = null;

	private JMenuItem AlteredConsumptionsjMenuItem = null;

	private JMenuItem SalirjMenuItem = null;
	
	private JMenu MaintenanceMenu;

	/**
	 * This is the default constructor
	 */
	public Main() {
		super();
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initialize();
		UserInterfaceSuport.centerComponent(this);
	}

	/**
	 * This method initializes the elements of this window
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(552, 359);		
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/ico_alpha_HomePage_32x32.png")));
		this.setEnabled(true);
		this.setJMenuBar(getMainMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("Energy Consumption Application");
		Style();
	}
	
	private void  Style() {
		UIManager.LookAndFeelInfo looks[] =
			UIManager.getInstalledLookAndFeels();
		try {
			UIManager.setLookAndFeel(looks[1].getClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
		}
	}
	
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			
			JLabel lblBackground = new JLabel("");
			lblBackground.setIcon(new ImageIcon(Main.class.getResource("/Img/energy.jpg")));
			lblBackground.setBounds(0, 0, 550, 312);
			jContentPane.add(lblBackground);
			}
		return jContentPane;
	}

	/**
	 * This method initializes mainMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getMainMenuBar() {
		if (mainMenuBar == null) {
			mainMenuBar = new JMenuBar();
			mainMenuBar.add(getFileMenu());
			mainMenuBar.add(getMaintenanceMenu());
			mainMenuBar.add(getReportsMenu());
		}
		return mainMenuBar;
	}

	/**
	 * This method initializes FileMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFileMenu() {
		if (FileMenu == null) {
			FileMenu = new JMenu();
			FileMenu.setText("File");
			FileMenu.add(getOpenFileMenuItem());
			FileMenu.add(getSaveMenuItem());
			FileMenu.add(getSalirjMenuItem());
		}
		return FileMenu;
	}

	/**
	 * This method initializes InsertHouseMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getInsertHouseMenuItem() {
		if (InsertHouseMenuItem == null) {
			InsertHouseMenuItem = new JMenuItem();
			InsertHouseMenuItem.setText("Houses");
			InsertHouseMenuItem.setIcon(new ImageIcon(getClass().getResource("/Img/newfile_wiz.gif")));
			InsertHouseMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new HouseMaintenanceForm(Main.this,true).setVisible(true);
					System.out.println("actionPerformed()");
				}
			});
		}
		return InsertHouseMenuItem;
	}
	
	/**
	 * This method initializes OpenFileMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getOpenFileMenuItem() {
		if (OpenFileMenuItem == null) {
			OpenFileMenuItem = new JMenuItem();
			OpenFileMenuItem.setText("Open File");
			OpenFileMenuItem.setIcon(new ImageIcon(getClass().getResource("/Img/ico_alpha_Folder_Opened_16x16.png")));
			OpenFileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
					Event.CTRL_MASK, true));
			OpenFileMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()");
					Component parent = (Component)e.getSource();
					JFileChooser fileChooser = new JFileChooser(".");
					fileChooser.setAccessory(new LabelAccessory(fileChooser));		            
					FileFilter filter1 = new ExtensionFileFilter(null, new String[]{"dat"});
					fileChooser.setFileFilter(filter1);
					FileView view = new JavaFileView();
					fileChooser.setFileView(view );
					int status = fileChooser.showOpenDialog(parent);
					if (status == JFileChooser.APPROVE_OPTION) {
						File selectedFile = fileChooser.getSelectedFile();
						String directoryLabel=selectedFile.getParent();
						String filenameLabel=selectedFile.getName();
						try {
							File f = new File(directoryLabel.concat("/").concat(filenameLabel));
							Consumption.getInstance().ReadFile(f);
						} catch (IOException e1) {

						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						}
						Main.this.ReportsMenu.setEnabled(true);
					} else if (status == JFileChooser.CANCEL_OPTION) {

					}
					System.out.println("actionPerformed()");
				}
			});
		}
		return OpenFileMenuItem;
	}

	/**
	 * This method initializes SaveMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getSaveMenuItem() {
		if (SaveMenuItem == null) {
			SaveMenuItem = new JMenuItem();
			SaveMenuItem.setText("Save Data to File");
			SaveMenuItem.setIcon(new ImageIcon(getClass().getResource("/Img/save_edit.gif")));
			SaveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
					Event.CTRL_MASK, true));
			SaveMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Component parent = (Component)e.getSource();
					JFileChooser fileChooser = new JFileChooser(".");
					fileChooser.setAccessory(new LabelAccessory(fileChooser));		            
					FileFilter filter1 = new ExtensionFileFilter(null, new String[]{"dat"});
					fileChooser.setFileFilter(filter1);

					fileChooser.setFileView(new JavaFileView());
					int status = fileChooser.showSaveDialog(parent);
					if (status == JFileChooser.APPROVE_OPTION) {
						File selectedFile = fileChooser.getSelectedFile();
						String directoryLabel=selectedFile.getParent();
						String filenameLabel=selectedFile.getName();
						try {
							try {
								Consumption.getInstance().writeFile(directoryLabel.concat("/").concat(filenameLabel));
							} catch (ClassNotFoundException e1) {								
								e1.printStackTrace();
							}
						} catch (IOException e1) {
							e1.printStackTrace();							
						}
					} else if (status == JFileChooser.CANCEL_OPTION) {
						
					}  
					System.out.println("actionPerformed()");
				}
			});
		}
		return SaveMenuItem;
	}

	/**
	 * This method initializes ReportsjMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	public JMenu getReportsMenu() {
		if (ReportsMenu == null) {
			ReportsMenu = new JMenu();
			ReportsMenu.setText(" Reports");
			ReportsMenu.add(getBiggerConnsumersjMenuItem());
			ReportsMenu.add(getAlteredConsumptionsjMenuItem());
			ReportsMenu.setEnabled(false);
			}
		return ReportsMenu;
	}

	/**
	 * This method initializes biggerConnsumersjMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getBiggerConnsumersjMenuItem() {
		if (biggerConnsumersjMenuItem == null) {
			biggerConnsumersjMenuItem = new JMenuItem();
			biggerConnsumersjMenuItem.setText("Houses with highest energy consumption");
			biggerConnsumersjMenuItem.setIcon(new ImageIcon(getClass().getResource("/Img/lens_in.png")));
			biggerConnsumersjMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new BiggerConsumers(Main.this,true).setVisible(true);
					System.out.println("actionPerformed()");
				}
			});
		}
		return biggerConnsumersjMenuItem;
	}

	/**
	 * This method initializes AlteredConsumptionsjMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getAlteredConsumptionsjMenuItem() {
		if (AlteredConsumptionsjMenuItem == null) {
			AlteredConsumptionsjMenuItem = new JMenuItem();
			AlteredConsumptionsjMenuItem.setText("Houses with altered consumption");
			AlteredConsumptionsjMenuItem.setIcon(new ImageIcon(getClass().getResource("/Img/lens_in.png")));
			AlteredConsumptionsjMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					new AlteredHomes(Main.this).setVisible(true);  
					System.out.println("actionPerformed()");
				}
			});
		}
		return AlteredConsumptionsjMenuItem;
	}
	
	/**
	 * This method initializes SalirjMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getSalirjMenuItem() {
		if (SalirjMenuItem == null) {
			SalirjMenuItem = new JMenuItem();
			SalirjMenuItem.setText("Exit");
			SalirjMenuItem.setIcon(new ImageIcon(getClass().getResource("/Img/ico_alpha_Delete_16x16.png")));
			SalirjMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return SalirjMenuItem;
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.setVisible(true);
	}
	
	/**
	 * This method initializes MaintenanceMenu
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenu getMaintenanceMenu() {
		if (MaintenanceMenu == null) {
			MaintenanceMenu = new JMenu("Maintenance");
			MaintenanceMenu.add(getInsertHouseMenuItem());
		}
		return MaintenanceMenu;
	}
}
