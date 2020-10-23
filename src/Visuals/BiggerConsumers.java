package Visuals;

import java.awt.Frame;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controler.Consumption;
import Logical.Home;
import Utils.UserInterfaceSuport;
import java.awt.Toolkit;

public class BiggerConsumers extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JScrollPane biggerConsumerjScrollPane = null;

	private JTable biggerConsumerjTable = null;

	private DefaultTableModel biggerConsumerdefaultTableModel = null;

	private JButton closejButton = null;

	private JButton GenerarjButton = null;

	/**
	 * This one of the constructor of this Frame
	 * 
	 * @param owner
	 */
	public BiggerConsumers(Frame owner) {
		super(owner);
		initialize();
	}
	
	/**
	 * This one of the constructor of this Frame
	 * @wbp.parser.constructor
	 */
	public BiggerConsumers(Frame owner, boolean modal ) {
		super(owner,modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage(BiggerConsumers.class.getResource("/Img/ico_alpha_HomePage_32x32.png")));
		setResizable(false);
		setModal(true);
		initialize();
	}
	
	/**
	 * This method initializes all the visuals elements of this Frame
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(672, 356);
		this.setTitle("Top 10 houses with the highest energy consumption ");
		this.setContentPane(getJContentPane());
		UserInterfaceSuport.centerComponent(this);
		fillTable();
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
			jContentPane.add(getBiggerConsumerjScrollPane(), null);
			jContentPane.add(getClosejButton(), null);
			jContentPane.add(getGenerarjButton(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes biggerConsumerjScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getBiggerConsumerjScrollPane() {
		if (biggerConsumerjScrollPane == null) {
			biggerConsumerjScrollPane = new JScrollPane();
			biggerConsumerjScrollPane.setBounds(new Rectangle(11, 11, 647, 272));
			biggerConsumerjScrollPane.setViewportView(getBiggerConsumerjTable());
		}
		return biggerConsumerjScrollPane;
	}

	/**
	 * This method initializes biggerConsumerjTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getBiggerConsumerjTable() {
		if (biggerConsumerjTable == null) {
			biggerConsumerjTable = new JTable(getBiggerConsumerdefaultTableModel());
		}
		return biggerConsumerjTable;
	}

	/**
	 * This method initializes biggerConsumerdefaultTableModel	
	 * 	
	 * @return javax.swing.table.DefaultTableModel	
	 */
	private DefaultTableModel getBiggerConsumerdefaultTableModel() {
		if (biggerConsumerdefaultTableModel == null) {
			biggerConsumerdefaultTableModel = new DefaultTableModel(new Object[]{"Number", "Address", 
					"Consumption", "Persons"}, 0){
				private static final long serialVersionUID = 1L;
				public boolean isCellEditable(int r, int c)
			     {
					return false;
			     }
			};
		}
		return biggerConsumerdefaultTableModel;
	}

	/**
	 * This method initializes closejButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getClosejButton() {
		if (closejButton == null) {
			closejButton = new JButton();
			closejButton.setText("Close");
			closejButton.setBounds(new Rectangle(441, 294, 77, 26));
			closejButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
					System.out.println("actionPerformed()");
				}
			});
		}
		return closejButton;
	}
	
	/**
	 * This method adds a row to the table
	 * @param h refers to an object of type House
	 */
	public  void addRow(Home h) {
		biggerConsumerdefaultTableModel.addRow(new Object[]{h.getNumber(), h.getAddress(), 
				Integer.toString(h.totalComsuption()) + " kw", h.getQuantityOfInhabitants()});
	}
	
	/**
	 * This method fills the table with then information of the 10 houses with highest
	 * energy consumption and sorts the information by Number of Persons living in the house.
	 */
	public void fillTable(){
		try {
			LinkedList<Home> houses = Consumption.getInstance().getloh().sortByQuantityOfInhabitants();
			
			for(Home hm: houses){
				addRow(hm);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method initializes GenerarjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getGenerarjButton() {
		if (GenerarjButton == null) {
			GenerarjButton = new JButton();
			GenerarjButton.setBounds(new Rectangle(151, 294, 139, 25));
			GenerarjButton.setText("Create TXT File");
			GenerarjButton.addActionListener(new java.awt.event.ActionListener() {
				@SuppressWarnings("static-access")
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()");
					JFileChooser fileChooser = new JFileChooser();
					int value = fileChooser.showSaveDialog(BiggerConsumers.this);
					if(value == fileChooser.APPROVE_OPTION){
						File f = fileChooser.getSelectedFile();
						try {
							Consumption.getC().createTxtFileReport(f.getPath() + ".txt");
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			});
		}
		return GenerarjButton;
	}
}
