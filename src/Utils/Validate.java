package Utils;

import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Validate {
	protected static Toolkit getToolkit() {
		return Toolkit.getDefaultToolkit();
	}
	
	public static void validateDigit(JTextField src) {
		src.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
	}

	public static void validateDigitAndComma(JTextField textField) {
		textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == '.') 
						|| (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
	}
	
	public static void validateLetter(JTextArea textField) {
		textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isLetter(c) || (c == KeyEvent.VK_BACK_SPACE) 
						|| (c == KeyEvent.VK_DELETE) || (Character.isWhitespace(c))))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
	}
	
	public static void validateDigit2(final JTextField src,  final int capacity) {
		src.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) 
						|| (c == KeyEvent.VK_DELETE))) || (src.getText().length() == capacity)) {
					e.consume();
				}
			}
		});
	}
}
