

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class RegisterPage extends JPanel{
	
	private JLabel accountLabel;
	private JLabel passwordLabel;
	private JLabel confirmPasswordLabel;
	private JTextField accountField;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private JButton registerBtn;
	private JButton cancelBtn;
	private JFrame f;
	
	private RegisterPage registerPage = this;
	
	public RegisterPage(JFrame f) {
		
		this.f = f;
		
		setLayout(null);
		
		createAccount();
		createPassword();
		createConfirmPassword();
		createRegisterBtn();
		createCancelBtn();
	}
	
	public void createAccount() {
		accountLabel = new JLabel("建立新帳號");
		accountLabel.setBounds(30, 210, 200, 100);
		accountLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		add(accountLabel);
		
		accountField = new JTextField();
		accountField.setBounds(250, 225, 300, 80);
		accountField.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		add(accountField);
	}
	
	public void createPassword() {
		passwordLabel = new JLabel("輸入密碼");
		passwordLabel.setBounds(50, 320, 200, 100);
		passwordLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(250, 335, 300, 80);
		passwordField.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		add(passwordField);
	}
	
	public void createConfirmPassword() {
		confirmPasswordLabel = new JLabel("確認密碼");
		confirmPasswordLabel.setBounds(50, 430, 200, 100);
		confirmPasswordLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		add(confirmPasswordLabel);
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(250, 445, 300, 80);
		confirmPasswordField.setFont(new Font("Lucida Grande", Font.PLAIN, 40));
		add(confirmPasswordField);
	}
	
	public void createRegisterBtn() {
		registerBtn = new JButton("註冊");
		registerBtn.setBounds(200, 550, 180, 70);
		registerBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		add(registerBtn);
		
		class registerBtnListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				
			}
		}
	}
	
	public void createCancelBtn() {
		cancelBtn = new JButton("返回");
		cancelBtn.setBounds(200, 650, 180, 70);
		cancelBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		add(cancelBtn);
		
		class CancelBtnListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				LogInPage logInPage = new LogInPage(f);
				f.remove(registerPage);
				f.add(logInPage);
				f.setVisible(true);
			}
		}
		
		ActionListener cancelBtnListener = new CancelBtnListener();
		cancelBtn.addActionListener(cancelBtnListener);
	}
	
}
