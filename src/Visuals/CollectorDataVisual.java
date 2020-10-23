package Visuals;

import java.awt.Frame;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Logical.CollectorData;
import Logical.Home;
import Utils.UserInterfaceSuport;
import Utils.Validate;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import java.awt.Toolkit;

public class CollectorDataVisual extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JLabel jLabel = null;

	private JPanel jPanel = null;

	private JLabel jLabel1 = null;

	private JTextField jTextField = null;

	private JButton jButton = null;
	
	private Home home = null;
	
	private JLabel lblNewLabel;

	/**
	 * This is one of the constructors
	 * 
	 * @param owner
	 */
	public CollectorDataVisual(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This is one of the constructors
	 * 
	 * @wbp.parser.constructor
	 */
	public  CollectorDataVisual(JDialog dialog, boolean modal, Home home) {
		super(dialog,modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage(CollectorDataVisual.class.getResource("/Img/Files-text.png")));
		setResizable(false);
		setModal(true);
		this.home = home;
		initialize();
		UserInterfaceSuport.centerComponent(this);
	}
	
	/**
	 * This method initializes all the visual components of this visual class
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(372, 215);
		this.setTitle("Collector Screen");
		this.setContentPane(getJContentPane());
		setData();
	}
	
	private void setData(){
		if(home.getCD() != null){
			jTextField.setText(String.valueOf(home.getCD().getCollectorConsumption()));
		}
	}
	
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel = new JLabel();
			jLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			jLabel.setBounds(new Rectangle(10, 11, 102, 24));
			jLabel.setText("House No.: " + home.getNumber() );
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(getJPanel(), null);
			jContentPane.add(getLblNewLabel());
		}
		return jContentPane;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel1 = new JLabel();
			jLabel1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			jLabel1.setBounds(new Rectangle(41, 23, 142, 26));
			jLabel1.setText("Collector Consumption:");
			jPanel = new JPanel();
			jPanel.setBorder(new TitledBorder(null, "Insert Collector Data", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(10, 58, 350, 121));
			jPanel.add(jLabel1, null);
			jPanel.add(getJTextField(), null);
			jPanel.add(getJButton());
		}
		return jPanel;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(183, 24, 87, 26));
			jTextField.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent e) {
				if(jTextField.getText().length() > 4){
					e.consume();
					getToolkit().beep();
				}
				Validate.validateDigit(jTextField);
			}
		});
		}
		return jTextField;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(129, 75, 91, 24);
			jButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
			jButton.setText("Insert");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(jTextField.getText().isEmpty()){
						JOptionPane.showMessageDialog(jContentPane, "Please insert the corresponding information!");
					}else{
					CollectorData colD = new CollectorData();
					colD.setCollectorConsumption(Integer.parseInt(jTextField.getText()));
					home.setCD(colD);
					dispose();
				}}
			});
		}
		return jButton;
	}
	
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Address: " + home.getAddress());
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel.setBounds(122, 11, 238, 24);
		}
		return lblNewLabel;
	}
}
