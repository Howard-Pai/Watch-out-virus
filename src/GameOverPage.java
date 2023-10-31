import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class GameOverPage extends JPanel {
	
	private SetGetters SG = new SetGetters();
	//建立圖片
	public static BufferedImage gameover;
	public static BufferedImage coin;
	
	//建立固定物件
	private JFrame f;
	private JLabel yourScoreLabel;
	private JLabel highestScoreLabel;
	private JLabel moneyLabel;
	private JButton exitBtn;
	
	//建立變數
	private int score;
	private int highestScore = 0;
	private int money;
	
	//讀入圖片
	static {
		try {
			gameover=ImageIO.read(new File("/Users/jeter0902/eclipse-workspace/Watch_Out_Virus_2/gameover.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//建立頁面，傳入frame和遊戲中的分數與金錢
	public GameOverPage(JFrame f, GamePage g) {
		this.f = f;
		money = g.getMoney();
		score = g.getScore();
		setLayout(null);
		createBtn();
		createYourScoreLabel();
		createHighestScoreLabel();
		createMoneyLabel();
	}
	
	//建立遊戲分數
	public void createYourScoreLabel() {
		yourScoreLabel = new JLabel();
		yourScoreLabel.setText("Your score: " + String.format("%09d", this.score));
		yourScoreLabel.setFont(new Font("Dialog", 1, 40));
		yourScoreLabel.setForeground(Color.white);
		yourScoreLabel.setBounds(80, 430, 500, 50);
		add(yourScoreLabel);
	}
	
	//建立最高分
	public void createHighestScoreLabel() {
		highestScoreLabel = new JLabel();
		try {
			if(this.score > SG.getHighScore()) {
				SG.setHighScore(this.score); 
				this.highestScore = this.score;
			}
			highestScoreLabel.setText("Highest Score: " + String.format("%09d", highestScore));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		highestScoreLabel.setFont(new Font("Dialog", 1, 40));
		highestScoreLabel.setForeground(Color.white);
		highestScoreLabel.setBounds(40, 490, 500, 50);
		add(highestScoreLabel);
	}
	
	//建立獲得金錢
	public void createMoneyLabel() {
		moneyLabel = new JLabel();
		moneyLabel.setText("+ $" + this.money);
		moneyLabel.setFont(new Font("Dialog", 1, 40));
		moneyLabel.setForeground(Color.white);
		moneyLabel.setBounds(250, 550, 500, 50);
		add(moneyLabel);
		try {
			SG.addMoney(money);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//建立返回鍵
	public void createBtn() {
		exitBtn = new JButton("返回");
		exitBtn.setBounds(240, 650, 136, 39);
		class ExitBtnListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				//返回主畫面
				setVisible(false);
				StartPage front = new StartPage(f);
				f.add(front);
				f.setVisible(true);
			}
		}
		ActionListener exitBtnListener = new ExitBtnListener();
		exitBtn.addActionListener(exitBtnListener);
		add(exitBtn);
	}
	
	//繪製背景
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(gameover, 0, 0, null);
	}
}