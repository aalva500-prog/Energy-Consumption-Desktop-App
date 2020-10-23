package Utils;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

public class UserInterfaceSuport {

	public static void centerComponent(Window frame){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((screenSize.width - frame.getWidth()) / 2,(screenSize.height - frame.getHeight()) / 2);
	}	
	
	public static void clearComponents(JPanel panel){
		Component [] components = panel.getComponents();
		
		for (Component component : components) {
			if (component instanceof JTextComponent) {
				((JTextComponent)component).setText("");				
			}		
		}
	}
}
