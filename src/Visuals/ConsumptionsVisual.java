package Visuals;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import Logical.DailyConsumption;
import Logical.Home;
import Utils.UserInterfaceSuport;
import Utils.Validate;
import java.awt.Toolkit;

public class ConsumptionsVisual extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel StartRegistrationjLabel = null;

	private JLabel finalRegistrationjLabel = null;

	private JTextField startRegistrationjTextField = null;

	private JTextField FinalRegistrationjTextField = null;

	private DefaultTableModel consuptionsdefaultTableModel = null;  //  @jve:decl-index=0:visual-constraint="601,79"

	private JButton insertjButton = null;

	private JButton ModifyjButton = null;

	private JPanel consuptionjPanel = null;

	private JScrollPane consuptionsjScrollPane = null;

	private JTable consuptionsjTable = null;

	private Home home=null;

	private JLabel DatejLabel = null;

	private JSpinner DatejSpinner = null;

	private SpinnerDateModel DatespinnerDateModel = null;  //  @jve:decl-index=0:visual-constraint="608,123"

	private JLabel numberHomejLabel = null;

	private DailyConsumption selectedCon = null;

	private JButton closejButton = null;

	/**
	 * This method is the constructor of this visual class
	 * 
	 * @param owner
	 */
	public ConsumptionsVisual(Frame owner) {
		super(owner);
		initialize();
	}
	
	/**
	 * This method is the constructor of this visual class
	 * 
	 * @wbp.parser.constructor
	 */
	public ConsumptionsVisual(JDialog dialog, boolean modal, Home home) {
		super(dialog,modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ConsumptionsVisual.class.getResource("/Img/ico_alpha_ClipboardEdit_24x24.png")));
		this.home = home;
		initialize();
		UserInterfaceSuport.centerComponent(this);
	}
	
	/**
	 * This method initializes all the components of this visual class
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(571, 383);
		this.setTitle("Consumption Information");
		this.setContentPane(getJContentPane());
		fillTable();
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			finalRegistrationjLabel = new JLabel();
			finalRegistrationjLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			finalRegistrationjLabel.setText("Final registry:");
			finalRegistrationjLabel.setBounds(new Rectangle(197, 70, 75, 30));
			StartRegistrationjLabel = new JLabel();
			StartRegistrationjLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			StartRegistrationjLabel.setText("Intitial registry:");
			StartRegistrationjLabel.setBounds(new Rectangle(10, 70, 87, 30));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setName("Consumption");
			jContentPane.add(getInsertjButton(), null);
			jContentPane.add(getModifyjButton(), null);
			jContentPane.add(getConsuptionjPanel(), null);
			jContentPane.add(getConsuptionsjScrollPane(), null);
			jContentPane.add(getClosejButton(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes startRegistrationjTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getStartRegistrationjTextField() {
		if (startRegistrationjTextField == null) {
			startRegistrationjTextField = new JTextField();
			startRegistrationjTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			startRegistrationjTextField.setBounds(new Rectangle(95, 71, 63, 30));
			Validate.validateDigit2(startRegistrationjTextField, 5);
		}
		return startRegistrationjTextField;
	}

	/**
	 * This method initializes FinalRegistrationjTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getFinalRegistrationjTextField() {
		if (FinalRegistrationjTextField == null) {
			FinalRegistrationjTextField = new JTextField();
			FinalRegistrationjTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			FinalRegistrationjTextField.setBounds(new Rectangle(272, 71, 63, 30));
			Validate.validateDigit2(FinalRegistrationjTextField,5);
		}
		return FinalRegistrationjTextField;
	}	

	/**
	 * This method initializes insertjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getInsertjButton() {
		if (insertjButton == null) {
			insertjButton = new JButton();
			insertjButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
			insertjButton.setBounds(new Rectangle(62, 301, 102, 25));
			insertjButton.setText("Insert");
			insertjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(startRegistrationjTextField.getText().isEmpty() || FinalRegistrationjTextField.getText().isEmpty()){
						JOptionPane.showMessageDialog(getContentPane(),"You must insert all the corresponding info!");
					} else {
					   Date date = (Date)getDatejSpinner().getValue();
					   int s = Integer.parseInt(startRegistrationjTextField.getText());
					   int f = Integer.parseInt(FinalRegistrationjTextField.getText());
					   if(f >= s){
						   DailyConsumption Dc = new DailyConsumption(date, s, f);
						   try {
							   home.AddDailyConsuption(Dc);
							   addRow(Dc);
						   } catch (Exception e1) {
							   JOptionPane.showMessageDialog(getContentPane(), e1.getMessage());
						   }
						  
					   } else {
						   JOptionPane.showMessageDialog(getContentPane(),"The Final Registry must be greater than the Initial Registry");   
					   }
					}
					ModifyjButton.setEnabled(false);
					UserInterfaceSuport.clearComponents(consuptionjPanel);
				}
			});
		}	
		return insertjButton;
	}

	/**
	 * This method initializes ModifyjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getModifyjButton() {
		if (ModifyjButton == null) {
			ModifyjButton = new JButton();
			ModifyjButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
			ModifyjButton.setBounds(new Rectangle(226, 301, 102, 25));
			ModifyjButton.setText("Modify");
			ModifyjButton.setEnabled(false);
			ModifyjButton.addActionListener(new ActionListener() {				
				public void actionPerformed(ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(ConsumptionsVisual.this, "Are you you want to modify this registry?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
						if(startRegistrationjTextField.getText().isEmpty() || FinalRegistrationjTextField.getText().isEmpty()){
							JOptionPane.showMessageDialog(getContentPane(), "You must insert all the corresponding info!");
						} else {	
						   Date date = (Date)getDatejSpinner().getValue();
						   int s = Integer.parseInt(startRegistrationjTextField.getText());
						   int f = Integer.parseInt(FinalRegistrationjTextField.getText());
						   if(f >= s){ 						
							   try {
								   home.find(date).setStartRegistration(s);
								   home.find(date).setFinalRegistration(f);
								   consuptionsdefaultTableModel.setRowCount(0);
								   fillTable();								   
							   } catch (Exception e1) {
								   JOptionPane.showMessageDialog(getContentPane(), e1.getMessage());
							   }							   
						   } else {
							   JOptionPane.showMessageDialog(getContentPane(),"The Final Registry must be greater than the Initial Registry");   
						   }						
						}
					}
					UserInterfaceSuport.clearComponents(consuptionjPanel);
					ModifyjButton.setEnabled(false);
				}		
			});
		}
		return ModifyjButton;
	}

	/**
	 * This method initializes consuptionjPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getConsuptionjPanel() {
		if (consuptionjPanel == null) {
			numberHomejLabel = new JLabel();
			numberHomejLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			numberHomejLabel.setBounds(new Rectangle(10, 21, 134, 29));
			numberHomejLabel.setText("House Number: "+  home.getNumber());
			DatejLabel = new JLabel();
			DatejLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			DatejLabel.setBounds(new Rectangle(377, 70, 33, 30));
			DatejLabel.setText("Date:");
			TitledBorder titledBorder = BorderFactory.createTitledBorder(null, "Daily Consumption", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51));
			titledBorder.setTitle("Consumption Info");
			consuptionjPanel = new JPanel();
			consuptionjPanel.setLayout(null);
			consuptionjPanel.setBounds(new Rectangle(5, 6, 544, 112));
			consuptionjPanel.setBorder(new TitledBorder(null, "Daily Consumption", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			consuptionjPanel.add(StartRegistrationjLabel, null);
			consuptionjPanel.add(getStartRegistrationjTextField(), null);
			consuptionjPanel.add(finalRegistrationjLabel, null);
			consuptionjPanel.add(getFinalRegistrationjTextField(), null);
			consuptionjPanel.add(DatejLabel, null);
			consuptionjPanel.add(getDatejSpinner(), null);
			consuptionjPanel.add(numberHomejLabel, null);			
			JLabel lblAddress = new JLabel("Address: " + home.getAddress());
			lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblAddress.setBounds(154, 21, 379, 29);
			consuptionjPanel.add(lblAddress);
		}
		return consuptionjPanel;
	}

	/**
	 * This method initializes consuptionsjScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getConsuptionsjScrollPane() {
		if (consuptionsjScrollPane == null) {
			consuptionsjScrollPane = new JScrollPane();
			consuptionsjScrollPane.setBounds(new Rectangle(5, 129, 545, 160));
			consuptionsjScrollPane.setViewportView(getConsuptionsjTable());
		}
		return consuptionsjScrollPane;
	}	
	
	/**
	 * This method initializes DatejSpinner	
	 * 	
	 * @return javax.swing.JSpinner	
	 */
	private JSpinner getDatejSpinner() {
		if (DatejSpinner == null) {
			DatejSpinner = new JSpinner( getDatespinnerDateModel() );
			DatejSpinner.setFont(new Font("Tahoma", Font.PLAIN, 12));
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			DatejSpinner.setEditor(new JSpinner.DateEditor(DatejSpinner,dateFormat.toPattern()));
			DatejSpinner.setBounds(new Rectangle(411, 70, 122, 30));
		}
		return DatejSpinner;
	}
	
	/**
	 * This method initializes DatespinnerDateModel	
	 * 	
	 * @return javax.swing.SpinnerDateModel	
	 */
	private SpinnerDateModel getDatespinnerDateModel() {
		if (DatespinnerDateModel == null) {
			DatespinnerDateModel = new SpinnerDateModel();
		}
		return DatespinnerDateModel;
	}
	
	/**
	 * This method initializes closejButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getClosejButton() {
		if (closejButton == null) {
			closejButton = new JButton();
			closejButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
			closejButton.setBounds(new Rectangle(390, 302, 102, 24));
			closejButton.setText("Close");
			closejButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return closejButton;
	}
	
	/**
	 * This method initializes consuptionsdefaultTableModel	
	 * 	
	 * @return javax.swing.table.DefaultTableModel	
	 */
	private DefaultTableModel getConsuptionsdefaultTableModel() {
		if (consuptionsdefaultTableModel == null) {
			consuptionsdefaultTableModel = new DefaultTableModel(new Object[]{"Date", "Energy Consumption"}, 0){				
				private static final long serialVersionUID = 1L;
				public boolean isCellEditable(int r, int c)
			     {			      
					return false;
			     }
			};
		}
		return consuptionsdefaultTableModel;
	}

	/**
	 * This method initializes consuptionsjTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getConsuptionsjTable() {
		if (consuptionsjTable == null) {
			consuptionsjTable = new JTable();
			consuptionsjTable.setModel(getConsuptionsdefaultTableModel());
			consuptionsjTable.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					Date date =	(Date)consuptionsdefaultTableModel.getValueAt(consuptionsjTable.getSelectedRow(), 0);
					DatespinnerDateModel.setValue(date);
					selectedCon = home.find(date);
					fillComponent(selectedCon);
					ModifyjButton.setEnabled(true);
				}
			});
		}
		return consuptionsjTable;
	}
	
	/**
	 * This method adds a row the consumptions table
	 * 
	 * @param Dc refers to a Daily Consumption
	 */
	public  void addRow(DailyConsumption Dc) {	
		consuptionsdefaultTableModel.addRow(new Object[]{ Dc.getDate(), Integer.toString(Dc.dailyCons()) + " kw"});
	}
   
	/**
	 * This method fills the table with the Daily consumptions of a house
	 */
	public void fillTable(){
		LinkedList<DailyConsumption> dc = home.getDc();
		for (DailyConsumption item : dc) {
			addRow(item);
		}
	}
	
	/**
	 * Thos method fills the Text fields with the information of the start and final consumption registration
	 * @param dc refers to a Daily Consumption
	 */
	public void fillComponent(DailyConsumption dc) {
		startRegistrationjTextField.setText(String.valueOf(dc.getStartRegistration()));
		FinalRegistrationjTextField.setText(String.valueOf(dc.getFinalRegistration()));
	}	
}
