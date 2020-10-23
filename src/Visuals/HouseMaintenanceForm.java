package Visuals;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import Controler.Consumption;
import Logical.Home;
import Utils.UserInterfaceSuport;
import Utils.Validate;
import java.awt.Toolkit;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class HouseMaintenanceForm extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JPanel DatesOfHousejPanel = null;

	private JLabel NumberjLabel = null;

	private JTextField NumberjTextField = null;

	private JLabel quantityOfInhabitantsjLabel = null;

	private JPanel tablejPanel = null;

	private JScrollPane listOfHousejScrollPane = null;

	private JTable listOfHousejTable = null;

	private DefaultTableModel listOfHousedefaultTableModel = null;

	private JButton ModifyjButton = null;

	private JButton DeletejButton = null;

	private JButton ConsumptionsjButton = null;

	private JScrollPane addressScrollPane = null;

	private JTextArea addressTextArea = null;

	private JLabel addressLabel = null;

	private JButton InsertjButton = null;
	
    private Home selectedHome=null;

	private JButton ClosejButton = null;

	private JCheckBox collectorDataCheckBox = null;

	private JTextField habitantsjTextField = null;

	/**
	 * This method is the constructor of this class
	 * 
	 * @param owner refers to the Frame of the parent Visual class
	 * @param modal refers to the modal way this visual class is going to be displayed
	 */
	public HouseMaintenanceForm(Frame owner, boolean modal) {
		super(owner, modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage(HouseMaintenanceForm.class.getResource("/Img/ico_alpha_HomePage_32x32.png")));
		setResizable(false);
		setModal(true);
		initialize();
	}

	/**
	 * This method initializes the elements of this visual class
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(716, 426);
		this.setTitle("Houses maintenance");
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
			jContentPane.add(getDatesOfHousejPanel(), null);
			jContentPane.add(getTablejPanel2(), null);
			jContentPane.add(getModifyjButton(), null);
			jContentPane.add(getDeletejButton(), null);
			jContentPane.add(getConsumptionsjButton(), null);
			jContentPane.add(getInsertjButton(), null);
			jContentPane.add(getClosejButton(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes DatesOfHousejPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDatesOfHousejPanel() {
		if (DatesOfHousejPanel == null) {
			
			addressLabel = new JLabel();
			addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			addressLabel.setBounds(new Rectangle(239, 23, 55, 29));
			addressLabel.setText(" Address:");
			quantityOfInhabitantsjLabel = new JLabel();
			quantityOfInhabitantsjLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			quantityOfInhabitantsjLabel.setBounds(new Rectangle(27, 72, 115, 29));
			quantityOfInhabitantsjLabel.setText("Number of Persons:");
			NumberjLabel = new JLabel();
			NumberjLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			NumberjLabel.setBounds(new Rectangle(38, 23, 104, 29));
			NumberjLabel.setText("House Number:");
			DatesOfHousejPanel = new JPanel();
			DatesOfHousejPanel.setLayout(null);
			DatesOfHousejPanel.setBounds(new Rectangle(10, 6, 693, 114));
			DatesOfHousejPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "House Info", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			DatesOfHousejPanel.add(NumberjLabel, null);
			DatesOfHousejPanel.add(getNumberjTextField(), null);
			DatesOfHousejPanel.add(quantityOfInhabitantsjLabel, null);
			DatesOfHousejPanel.add(getAddressScrollPane(), null);
			DatesOfHousejPanel.add(addressLabel, null);
			DatesOfHousejPanel.add(getCollectorDataCheckBox(), null);
			DatesOfHousejPanel.add(getHabitantsjTextField(), null);
		}
		return DatesOfHousejPanel;
	}

	/**
	 * This method initializes NumberjTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNumberjTextField() {
		if (NumberjTextField == null) {
			NumberjTextField = new JTextField();
			NumberjTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			NumberjTextField.setBounds(new Rectangle(142, 23, 64, 29));
			NumberjTextField.setText("");			
			NumberjTextField.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					if(NumberjTextField.getText().length() > 5){
						e.consume();
						getToolkit().beep();
					}
				}
			});
			Validate.validateDigit(NumberjTextField);
			}		
					
		return NumberjTextField;
	}

	/**
	 * This method initializes tablejPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getTablejPanel2() {
		if (tablejPanel == null) {
			tablejPanel = new JPanel();
			tablejPanel.setLayout(null);
			tablejPanel.setBounds(new Rectangle(10, 123, 693, 217));
			tablejPanel.add(getListOfHousejScrollPane(), null);
		}
		return tablejPanel;
	}

	/**
	 * This method initializes listOfHousejScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getListOfHousejScrollPane() {
		if (listOfHousejScrollPane == null) {
			listOfHousejScrollPane = new JScrollPane();
			listOfHousejScrollPane.setBounds(new Rectangle(6, 8, 680, 200));
			listOfHousejScrollPane.setViewportView(getListOfHousejTable());
		}
		return listOfHousejScrollPane;
	}

	/**
	 * This method initializes listOfHousejTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getListOfHousejTable() {
		if (listOfHousejTable == null) {
			listOfHousejTable = new JTable();
			listOfHousejTable.setModel(getListOfHousedefaultTableModel());
			listOfHousejTable.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					int number=	(Integer)listOfHousedefaultTableModel.getValueAt(listOfHousejTable.getSelectedRow(), 0);
					String address = (String)listOfHousedefaultTableModel.getValueAt(listOfHousejTable.getSelectedRow(), 1);
					selectedHome = Consumption.getInstance().getloh().findHome(address, number);
					if (collectorDataCheckBox.isSelected()) {
						new CollectorDataVisual(HouseMaintenanceForm.this, true, selectedHome).setVisible(true);
						ClearScreen();
					} else {
						fillComponent(selectedHome);
						ModifyjButton.setEnabled(true);
						DeletejButton.setEnabled(true);
						ConsumptionsjButton.setEnabled(true);
					}
				}
			});
		}
		return listOfHousejTable;
	}

	/**
	 * This method initializes listOfHousedefaultTableModel	
	 * 	
	 * @return javax.swing.table.DefaultTableModel	
	 */
	private DefaultTableModel getListOfHousedefaultTableModel() {
		if (listOfHousedefaultTableModel == null) {
			listOfHousedefaultTableModel = new DefaultTableModel(new Object[]{"House Number", "Address", 
					"Number of Persons"}, 0){				
				private static final long serialVersionUID = 1L;
				public boolean isCellEditable(int r, int c)
			     {			      
					return false;
			     }
			};
		}
		return listOfHousedefaultTableModel;
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
			ModifyjButton.setBounds(new Rectangle(166, 351, 98, 27));
			ModifyjButton.setText("Modify");
			ModifyjButton.setEnabled(false);
			ModifyjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(HouseMaintenanceForm.this, "Are you sure you want to modify this house " + selectedHome.getNumber() + "?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if (result == JOptionPane.YES_OPTION){
                            selectedHome.setAddress(addressTextArea.getText());
							selectedHome.setNumber(Integer.valueOf(NumberjTextField.getText()));
							selectedHome.setQuantityOfInhabitants( Integer.valueOf(habitantsjTextField.getText()));
						    listOfHousedefaultTableModel.removeRow(listOfHousejTable.getSelectedRow());						   
							addRow(selectedHome);
					}
					ClearScreen();
				};
				});
			}
		return ModifyjButton;
	}

	/**
	 * This method initializes DeletejButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDeletejButton() {
		if (DeletejButton == null) {
			DeletejButton = new JButton();
			DeletejButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
			DeletejButton.setBounds(new Rectangle(298, 351, 98, 27));
			DeletejButton.setText("Delete");
			DeletejButton.setEnabled(false);
			DeletejButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(HouseMaintenanceForm.this, "Are you sure you want to delete this house from the list? " + selectedHome.getNumber()+ "?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if (result == JOptionPane.YES_OPTION){
						Home home2= Consumption.getC().getloh().getHouses().get(listOfHousejTable.getSelectedRow());
						Consumption.getC().getloh().removeHome(home2);
						listOfHousedefaultTableModel.removeRow(listOfHousejTable.getSelectedRow());
						  addressTextArea.setText("");
						  NumberjTextField.setText("");
						  habitantsjTextField.setText("");
						  if(Consumption.getC().getloh().getHouses().size() == 0){
							  collectorDataCheckBox.setEnabled(false);
						  }
					}
					ClearScreen();
				}
			});	
		}
		return DeletejButton;
	}

	/**
	 * This method initializes HipervinculojButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getConsumptionsjButton() {
		if (ConsumptionsjButton == null) {
			ConsumptionsjButton = new JButton();
			ConsumptionsjButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
			ConsumptionsjButton.setBounds(new Rectangle(430, 351, 116, 27));
			ConsumptionsjButton.setText("Consumptions");
			ConsumptionsjButton.setToolTipText("Insert daily consumptions");
			ConsumptionsjButton.setEnabled(false);
			ConsumptionsjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ConsumptionsVisual consuptionVisual = new ConsumptionsVisual(HouseMaintenanceForm.this, true, selectedHome);
					consuptionVisual.setVisible(true);
					ClearScreen();
				}
			});
		}
		return ConsumptionsjButton;
	}

	/**
	 * This method initializes addressScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getAddressScrollPane() {
		if (addressScrollPane == null) {
			addressScrollPane = new JScrollPane();
			addressScrollPane.setBounds(new Rectangle(297, 23, 382, 40));
			addressScrollPane.setViewportView(getAddressTextArea());
		}
		return addressScrollPane;
	}

	/**
	 * This method initializes addressTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getAddressTextArea() {
		if (addressTextArea == null) {
			addressTextArea = new JTextArea();
			addressTextArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
			
		}
		return addressTextArea;
	}

	/**
	 * This method initializes InsertjButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getInsertjButton() {
		if (InsertjButton == null) {
			InsertjButton = new JButton();
			InsertjButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
			InsertjButton.setBounds(new Rectangle(34, 351, 98, 27));
			InsertjButton.setText("Insert");
			InsertjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(NumberjTextField.getText().isEmpty() || addressTextArea.getText().isEmpty() || habitantsjTextField.getText().isEmpty()){
						JOptionPane.showMessageDialog(getContentPane(),"You must insert all the corresponding information");
					}
					else{
					   int s = Integer.parseInt(NumberjTextField.getText());
					   String b = addressTextArea.getText();
					   int f = Integer.parseInt(habitantsjTextField.getText());
					   Home home = new Home(s, b, f, null, null);
					    try {
							Consumption.getC().getloh().AddHome(home);
							addRow(home);
						} catch (Exception e1) {
							   JOptionPane.showMessageDialog(getContentPane(),e1.getMessage());
						}
						ClearScreen();
					   }
					collectorDataCheckBox.setEnabled(true);
					((Main)HouseMaintenanceForm.this.getOwner()).getReportsMenu().setEnabled(true);					
					}
			});
		}	
		return InsertjButton;
	}

	/**
	 * This method initializes closejButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getClosejButton() {
		if (ClosejButton == null) {
			ClosejButton = new JButton();
			ClosejButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
			ClosejButton.setBounds(new Rectangle(580, 351, 98, 26));
			ClosejButton.setText("Close");
			ClosejButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return ClosejButton;
	}

	/**
	 * This method initializes collectorDataCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	public JCheckBox getCollectorDataCheckBox() {
		if (collectorDataCheckBox == null) {
			collectorDataCheckBox = new JCheckBox();
			collectorDataCheckBox.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent arg0) {
					if (collectorDataCheckBox.isSelected() == true) {
						collectorDataCheckBox.setText("Insert Collector Data (Select a house to insert Collector Data)");
					} else {
						collectorDataCheckBox.setText("Insert Collector Data");
					}
				}
			});
			collectorDataCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
			collectorDataCheckBox.setBounds(new Rectangle(239, 75, 440, 19));
			collectorDataCheckBox.setText("Insert Collector Data");
			collectorDataCheckBox.setEnabled(false);
		}
		return collectorDataCheckBox;
	}

	/**
	 * This method initializes habitantesjTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getHabitantsjTextField() {
		if (habitantsjTextField == null) {
			habitantsjTextField = new JTextField();
			habitantsjTextField.setFont(new Font("Tahoma", Font.PLAIN, 12));
			habitantsjTextField.setBounds(new Rectangle(142, 72, 64, 29));
			habitantsjTextField.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent e) {
				if(habitantsjTextField.getText().length() > 1){
					e.consume();
					getToolkit().beep();
				}
			}
		});
		Validate.validateDigit(habitantsjTextField);
		}
		return habitantsjTextField;
	}
	
	/**
	 * This method receives a home as parameter and fills the fields
	 * with its information
	 * @param h refers to a house selected in the table
	 */
	public void fillComponent(Home h){
		NumberjTextField.setText(String.valueOf(h.getNumber()));
		habitantsjTextField.setText(String.valueOf(h.getQuantityOfInhabitants()));
		addressTextArea.setText(h.getAddress());
	}
   
	/**
	 * This method sets the screen to its initial state
	 */
	public void ClearScreen(){		
	  ModifyjButton.setEnabled(false);
	  DeletejButton.setEnabled(false);
	  ConsumptionsjButton.setEnabled(false);
	  collectorDataCheckBox.setSelected(false);
	  addressTextArea.setText("");
	  UserInterfaceSuport.clearComponents(DatesOfHousejPanel);
	}
	
	/**
	 * This method adds a row to the table by passing an object of type Home
	 * @param h refers to a home
	 */
	public  void addRow(Home h) {
		listOfHousedefaultTableModel.addRow(new Object[]{h.getNumber(), h.getAddress(), h.getQuantityOfInhabitants()});
	}
	
	/**
	 * This method fills the table with the information of the houses
	 */
	public void fillTable(){
		LinkedList<Home> h = Consumption.getInstance().getloh().getHouses();
		
		for (Home item: h) {
			addRow(item);
		}
		
		if(Consumption.getInstance().getloh().getHouses().size() > 0){
			collectorDataCheckBox.setEnabled(true);
		}
	}
}
