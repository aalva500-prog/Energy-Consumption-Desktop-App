package Visuals;

import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controler.Consumption;
import Logical.Home;
import Utils.UserInterfaceSuport;
import java.awt.Toolkit;

public class AlteredHomes extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JScrollPane alteredHomesjScrollPane = null;

	private JTable jTable = null;

	private DefaultTableModel defaultTableModel = null;  //  @jve:decl-index=0:visual-constraint="694,45"

	private JButton jCloseButton = null;

	private JButton jSaveButton = null;

	/**
	 * This is the constructor of this visual class
	 * 
	 * @param owner
	 */
	public AlteredHomes(Frame owner) {
		super(owner);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AlteredHomes.class.getResource("/Img/ico_alpha_HomePage_32x32.png")));
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		initialize();
	}

	/**
	 * This method initializes the visual contents of this class
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(806, 374);
		this.setTitle("Houses with altered energy consumptions");
		this.setContentPane(getJContentPane());
		fillTable();
		UserInterfaceSuport.centerComponent(this);
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
			jContentPane.add(getAlteredHomesjScrollPane(), null);
			jContentPane.add(getJCloseButton(), null);
			jContentPane.add(getJSaveButton(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes alteredHomesjScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getAlteredHomesjScrollPane() {
		if (alteredHomesjScrollPane == null) {
			alteredHomesjScrollPane = new JScrollPane();
			alteredHomesjScrollPane.setBounds(new Rectangle(14, 8, 776, 281));
			alteredHomesjScrollPane.setViewportView(getJTable());
		}
		return alteredHomesjScrollPane;
	}

	/**
	 * This method initializes jTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getJTable() {
		if (jTable == null) {
			jTable = new JTable(getDefaultTableModel());
		}
		return jTable;
	}

	/**
	 * This method initializes defaultTableModel	
	 * 	
	 * @return javax.swing.table.DefaultTableModel	
	 */
	private DefaultTableModel getDefaultTableModel() {
		if (defaultTableModel == null) {
			defaultTableModel = new DefaultTableModel(new Object[]{"Number", "Address", 
					"Real Consumption", "Real Rate", "Collector Data", "Altered Rate"}, 0) {
				private static final long serialVersionUID = 1L;
				public boolean isCellEditable(int r, int c)
			     {
					return false;
			     }
			};
		}
		return defaultTableModel;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJCloseButton() {
		if (jCloseButton == null) {
			jCloseButton = new JButton();
			jCloseButton.setBounds(new Rectangle(526, 300, 136, 29));
			jCloseButton.setText("Close");
			jCloseButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return jCloseButton;
	}

	/**
	 * This method inserts a row into the table with information of a house
	 * @param h refers to a house object
	 */
	public  void addRow(Home h) {
		defaultTableModel.addRow(new Object[]{h.getNumber(), h.getAddress(), 
				Integer.toString(h.totalComsuption()) + " kw", 
				"$ " + Double.toString(h.realTotalPay()),
				Integer.toString(h.getCD().getCollectorConsumption()) + " kw",
				"$ " + Double.toString(h.alteredConsumption())});
	}
	
	/**
	 * This method fills the table with the information of the houses with altered consumption rates
	 */
	public void fillTable(){
		LinkedList<Home> houses = Consumption.getInstance().getloh().alteredHomes();
		for(Home hm: houses){
			addRow(hm);
		}
		if(Consumption.getC().getloh().alteredHomes().size() > 0){
			jSaveButton.setEnabled(true);
		}
	}	
	
	/**
	 * This method initializes BinaryjButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJSaveButton() {
		if (jSaveButton == null) {
			jSaveButton = new JButton();
			jSaveButton.setBounds(new Rectangle(140, 300, 246, 29));
			jSaveButton.setText("Save Info into Binary File");
			jSaveButton.setEnabled(false);
			jSaveButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						// Write info from table to binary file
						Consumption.getC().getloh().createBinaryFileAlteredConsumptions();
					} catch (IOException e1){
					}					
				}
			});
		}
		return jSaveButton;
	}
} 