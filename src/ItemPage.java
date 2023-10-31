import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ItemPage extends JPanel{
	
	private SetGetters SG = new SetGetters();
	//讀入圖片
	private ImageIcon mask = new ImageIcon("mask.png");
	private ImageIcon alcohol = new ImageIcon("alcohol.png");
	private ImageIcon clothes = new ImageIcon("clothes.png");
	
	//檢查是否選取道具
	private boolean maskBoolean = false;
	private boolean clothesBoolean = false;
	private boolean alcoholBoolean = false;
	
	//建立固定物件
	private JFrame frame;
	private ItemPage itemPage= this;
	private CharacterPage charPage;
	private JLabel titleLabel;
	private JComboBox<String> comboBox;
	private JTextArea textArea;
	private JButton chooseBtn;
	private JButton discardBtn;
	private JButton startBtn;
	/**
	 * Create the frame.
	 */
	public ItemPage(JFrame f, CharacterPage cPage) {
		this.frame = f;
		charPage = cPage;
		
		setSize(600,800);
		setForeground(Color.DARK_GRAY);
		setBackground(UIManager.getColor("CheckBox.light"));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		createTitle();
		createComboBox();
		createTextArea();
		createChooseBtn();
		createDiscardBtn();
		createStartBtn();
	}
	
	//建立標題
	public void createTitle() {
		titleLabel = new JLabel("道  具");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 72));
		titleLabel.setBounds(29, 10, 514, 97);
		add(titleLabel);
	}
	
	//建立道具選單
	public void createComboBox() {
		comboBox = new JComboBox<String>();
		comboBox.setBounds(140, 120, 300, 35);
		comboBox.addItem("口罩");
		comboBox.addItem("酒精");
		comboBox.addItem("防護衣");
		add(comboBox);
		JLabel iconLabel = new JLabel();
		iconLabel.setBounds(80, 150, 500, 300);
		add(iconLabel);
		iconLabel.setIcon(mask);
		class itemListener implements ItemListener{
			public void itemStateChanged(ItemEvent swi) {
				if(swi.getSource() == comboBox) {
					if(comboBox.getSelectedItem().equals("口罩")) {
						iconLabel.setIcon(mask);
						try {
							if(SG.getMaskAmount() > 0) {
								chooseBtn.setEnabled(true);
							}
							else {
								chooseBtn.setEnabled(false);
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else if(comboBox.getSelectedItem().equals("酒精")) {
						iconLabel.setIcon(alcohol);
						try {
							if(SG.getAlcoholAmount() > 0) {
								chooseBtn.setEnabled(true);
							}
							else {
								chooseBtn.setEnabled(false);
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else if(comboBox.getSelectedItem().equals("防護衣")) {
						iconLabel.setIcon(clothes);
						try {
							if(SG.getClothesAmount() > 0) {
								chooseBtn.setEnabled(true);
							}
							else {
								chooseBtn.setEnabled(false);
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
					
			}
		}
		ItemListener itemListener = new itemListener();
		comboBox.addItemListener(itemListener);
	}
	

	//建立選擇道具清單
	public void createTextArea() {
		textArea = new JTextArea();
		textArea.setBackground(UIManager.getColor("Button.light"));
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 24));
		textArea.setText("已穿戴物品：");
		textArea.setBounds(54, 520, 489, 164);
		textArea.setBorder(new TitledBorder(new EtchedBorder()));
		add(textArea);
	}
	
	//建立選擇鍵
	public void createChooseBtn() {
		chooseBtn = new JButton("選擇");
		chooseBtn.setEnabled(false);
		try {
			if(SG.getMaskAmount() > 0) {
				chooseBtn.setEnabled(true);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		chooseBtn.setBackground(Color.WHITE);
		chooseBtn.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 28));
		chooseBtn.setBounds(100, 450, 102, 36);
		add(chooseBtn);
		class selectBtnClicker implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem().equals("口罩") && maskBoolean==false) {
					textArea.append("\n口罩");
					try {
						SG.addMasks(-1);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					maskBoolean=true;
					discardBtn.setEnabled(true);
				}else if (comboBox.getSelectedItem().equals("酒精") && alcoholBoolean== false) {
					textArea.append("\n酒精");
					try {
						SG.addAlcohol(-1);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					alcoholBoolean=true;
					discardBtn.setEnabled(true);
				}else if (comboBox.getSelectedItem().equals("防護衣") && clothesBoolean== false) {
					textArea.append("\n防護衣");
					try {
						SG.addClothes(-1);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					clothesBoolean=true;
					discardBtn.setEnabled(true);
				}
			}
		}
		ActionListener selectListener = new selectBtnClicker();
		chooseBtn.addActionListener(selectListener);
	}
	
	//建立棄用鍵
	public void createDiscardBtn() {
		discardBtn = new JButton("棄用");
		discardBtn.setEnabled(false);
		discardBtn.setBackground(Color.WHITE);
		discardBtn.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 28));
		discardBtn.setBounds(400, 450, 102, 36);
		add(discardBtn);
		class discardClicker implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem().equals("口罩") && maskBoolean==true) {
					if(alcoholBoolean == true) {
						if(clothesBoolean == true) {
							textArea.setText("已穿戴物品：\n酒精\n防護衣");
						}else {
						textArea.setText("已穿戴物品：\n酒精");
						}
					}
					else if(clothesBoolean == true) {
						textArea.setText("已穿戴物品：\n防護衣");
					}
					else {
						textArea.setText("已穿戴物品：");
					}
					maskBoolean=false;
					try {
						SG.addMasks(1);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					discardBtn.setEnabled(false);
				}else if (comboBox.getSelectedItem().equals("酒精") && alcoholBoolean== true) {
					if(maskBoolean == true) {
						if(clothesBoolean == true) {
							textArea.setText("已穿戴物品：\n口罩\n防護衣");
						}else {
						textArea.setText("已穿戴物品：\n口罩");
						}
					}
					else if(clothesBoolean == true) {
						textArea.setText("已穿戴物品：\n防護衣");
					}
					else {
						textArea.setText("已穿戴物品：");
					}
					alcoholBoolean=false;
					try {
						SG.addAlcohol(1);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					discardBtn.setEnabled(false);
				}else if (comboBox.getSelectedItem().equals("防護衣") && clothesBoolean== true) {
					if(maskBoolean == true && alcoholBoolean == true) {
							textArea.setText("已穿戴物品：\n口罩\n酒精");
					}else if(maskBoolean == true && alcoholBoolean == false) {
						textArea.setText("已穿戴物品：\n口罩");
					}
					else if(alcoholBoolean == true && maskBoolean == false) {
						textArea.setText("已穿戴物品：\n酒精");
					}
					else {
						textArea.setText("已穿戴物品：");
					}
					clothesBoolean=false;
					try {
						SG.addClothes(1);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					discardBtn.setEnabled(false);
				}
			}
		}
		ActionListener discardClicker = new discardClicker();
		discardBtn.addActionListener(discardClicker);
	}
	
	//建立開始鍵
	public void createStartBtn() {
		startBtn = new JButton("開始遊戲");
		startBtn.setBackground(Color.WHITE);
		startBtn.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 24));
		startBtn.setBounds(207, 690, 165, 62);
		add(startBtn);
		class StartBtnListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				GamePage gamePage = new GamePage(frame, itemPage, charPage);
				setVisible(false);
				frame.add(gamePage);
				frame.setVisible(true);
			}
		}
		ActionListener startBtnListener = new StartBtnListener();
		startBtn.addActionListener(startBtnListener);
	}
	
	public boolean getMaskBoolean() {
		return maskBoolean;
	}
	
	public boolean getClothesBoolean() {
		return clothesBoolean;
	}
	
	public boolean getAlcoholBoolean() {
		return alcoholBoolean;
	}
}
