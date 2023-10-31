import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
public class LogInPage extends JPanel{
	
	private JLabel accountLabel;
	private JLabel passwordLabel;
	private JTextField accountField;
	private JTextField passwordField;
	private JButton logInBtn;
	private JButton registerBtn; 
	private JFrame f;
	private String server = "jdbc:mysql://140.119.19.73:9306/";
	private String database = "TG09";
	private String config= "?useUnicode=true&characterEncoding=utf8";
	private String url = server + database + config;
	private String username = "TG09";
	private String password = "u73e9x";
	private Connection conn = null;
	private String userAccount;
	private SetGetters SG = new SetGetters();
	
	
	private LogInPage logInPage = this;
	
	private static final int TEXT_WIDTH = 100; 
	
	public LogInPage() {
		
	}
	public LogInPage(JFrame f) {
		
		this.f = f;
		setLayout(null);
		
		createAccount();
		createPassword();
		createLogInBtn();
		createRegisterBtn();
	}
	
	public void createAccount() {
		accountLabel = new JLabel("帳號");
		accountLabel.setBounds(50, 210, 200, 100);
		accountLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		add(accountLabel);
		
		accountField = new JTextField();
		accountField.setBounds(200, 225, 300, 80);
		accountField.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		add(accountField);
	}
	
	public void createPassword() {
		passwordLabel = new JLabel("密碼");
		passwordLabel.setBounds(50, 340, 200, 100);
		passwordLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		add(passwordLabel);
		
		passwordField = new JTextField();
		passwordField.setBounds(200, 355, 300, 80);
		passwordField.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		add(passwordField);
	}
	
	public void createLogInBtn() {
		logInBtn = new JButton("登入");
		logInBtn.setBounds(200, 480, 180, 70);
		logInBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		add(logInBtn);
		
		class LogInBtnListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				String getAccountField = accountField.getText();
				String getPasswordField = passwordField.getText();
				Statement stat = null;
				ResultSet result = null;
				PreparedStatement ps = null;
				
				  try {
					   conn = DriverManager.getConnection(url, username, password);
					   stat = conn.createStatement();
					   String query = "SELECT * FROM Watch_Out_Virus Where User_Account='"+getAccountField+"'";
					   ps=conn.prepareStatement(query); 
					   ResultSet rs=ps.executeQuery();
					   if(rs.next()){
						   SG.setAccount(getAccountField);
						   if(getPasswordField.equals(SG.getUserPassWord())){
							   LogInPage.this.userAccount = getAccountField;
							   //前往下一頁
						   }
						   else {
							   JFrame frame = new JFrame();
							   frame.setSize(100, 50);
							   JPanel panel = new JPanel();
							   JLabel label = new JLabel("密碼錯誤");
							   panel.add(label);
							   frame.add(panel);
							   frame.setVisible(true);
							   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						   }
						   
					   }
					   else {
						   JFrame frame = new JFrame();
						   frame.setSize(100, 50);
						   JPanel panel = new JPanel();
						   JLabel label = new JLabel("無此帳號");
						   panel.add(label);
						   frame.add(panel);
						   frame.setVisible(true);
						   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						   System.out.print("666");
					   }
					   
					  }catch(Exception exc) {
						  System.out.print(exc);
					  }
					  finally {
					   if (conn != null) {
					           try {
								conn.close();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					        }
					  }
				  
			}
		}
		LogInBtnListener LogInBtnListener = new LogInBtnListener();
		logInBtn.addActionListener(LogInBtnListener);
	}
	
	public void createRegisterBtn() {
		registerBtn = new JButton("註冊新帳號");
		registerBtn.setBounds(200, 600, 180, 70);
		registerBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		add(registerBtn);
		
		class RegisterBtnListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				RegisterPage registerPage = new RegisterPage(f);
				f.remove(logInPage);
				f.add(registerPage);
				f.setVisible(true);
			}
		}
		
		ActionListener registerBtnListener = new RegisterBtnListener();
		registerBtn.addActionListener(registerBtnListener);
	}
	public String getUserAccount() {
		return this.userAccount;
	}
	
	public SetGetters getSG() {
		return this.SG;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JPanel p = new LogInPage(frame);
		frame.add(p);
		frame.setSize(600, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
