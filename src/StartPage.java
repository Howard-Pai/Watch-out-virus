import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartPage extends JPanel {
	private JLabel title;
	private JButton shop;
	private JButton start;
	private JFrame f;
	private SetGetters setGetter ;

	//建立開始頁面，傳入frame
	public StartPage(JFrame frame) {
		this.f=frame;
		setLayout(null);
		createTitle();
		createShop();
		createStart();
	}

	//建立遊戲標題
	public void createTitle() {
		title = new JLabel("小心疫疫");
		title.setBounds(100, 72, 500, 200);
		title.setFont(new Font("Lucida Grande", Font.ITALIC, 99));
		add(title);
	}

	//建立前往商城JButton，連接到ShopPage
	public void createShop() {
		shop = new JButton("商店");
		shop.setBounds(150, 400, 300, 65);
		shop.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		add(shop);
		class shopActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				ShopPage shopPanel= new ShopPage(f);
				setVisible(false);
				f.add(shopPanel);
				f.setVisible(true);
			}
		}
		shop.addActionListener(new shopActionListener());
	}

	//建立開始遊戲JButton，連接到CharacterPage
	public void createStart() {
		start = new JButton("開始");
		start.setBounds(150, 600, 300, 65);
		start.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
		add(start);
		class startActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				CharacterPage characterPage = new CharacterPage(f);
				setVisible(false);
				f.add(characterPage);
				f.setVisible(true);
			}
		}
		start.addActionListener(new startActionListener());
	}
	
	
}
