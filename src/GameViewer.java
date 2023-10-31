import javax.swing.JFrame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GameViewer {
	public static final int FRAME_WIDTH = 600;
	public static final int FRAME_HEIGHT = 800;
	private static final SetGetters setgetter = new SetGetters();


	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		setgetter.setMacList();
		setgetter.checkMacs();
		System.out.print(setgetter.getMacs());
		JFrame frame = new JFrame("小心疫疫");
		StartPage front = new StartPage(frame);
		frame.add(front);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		
	}
}
