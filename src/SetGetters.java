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

public class SetGetters {
	private String server = "jdbc:mysql://140.119.19.73:9306/";
	private String database = "TG09";
	private String config= "?useUnicode=true&characterEncoding=utf8";
	private String url = server + database + config;
	private String username = "TG09";
	private String password = "u73e9x";
	private Connection conn = null;
	private String macs = "error";
	private int highScore ;
	private int money ;
	private int maskAmount ;
	private int alcoholAmount ;
	private int clothesAmount ;
	private int tzuyu ;
	private int clock ;
	private int trump ;
	
	public void setMacList() throws Exception {
        java.util.Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
        StringBuilder sb = new StringBuilder();
        ArrayList<String> tmpMacList=new ArrayList<>();
        while(en.hasMoreElements()){
            NetworkInterface iface = en.nextElement();
            List<InterfaceAddress> addrs = iface.getInterfaceAddresses();
            for(InterfaceAddress addr : addrs) {
                InetAddress ip = addr.getAddress();
                NetworkInterface network = NetworkInterface.getByInetAddress(ip);
                if(network==null){continue;}
                byte[] mac = network.getHardwareAddress();
                if(mac==null){continue;}
                sb.delete( 0, sb.length() );
                for (int i = 0; i < mac.length; i++) {sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));}
                tmpMacList.add(sb.toString());
            }        }
        if(tmpMacList.size()<=0){}
        
        List<String> unique = tmpMacList.stream().distinct().collect(Collectors.toList());
        if(unique != null) {
        	this.macs = unique.toString();
        }
        else {
        	this.macs = tmpMacList.toString();
        }
        
    }
	
	public String getMacs() {
		return this.macs;
	}
	public void checkMacs() throws SQLException{
		Statement stat = null;
		
		  try {
			  setMacList();
			   conn = DriverManager.getConnection(url, username, password);
			   stat = conn.createStatement();
			   String query = "SELECT * FROM Watch_Out_Virus WHERE find_in_set('"+this.macs+"', User_Mac)";
			   ResultSet rs = stat.executeQuery(query); 			  
	           if(!rs.next()){
				   initializeData();
				
				  }
			  }catch(Exception e) {
				  
			  }
			  finally {
			   if (conn != null) {
			           conn.close();
			        }
			  }
		
	}
	

	public void initializeData() throws SQLException{
		highScore = 0;
		money = 0;
		maskAmount = 0;
		alcoholAmount = 0;
		clothesAmount = 0;
		tzuyu = 0;
		clock = 0;
		trump = 0;
		Statement stat = null;
		try {
			setMacList();
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
			String query = "INSERT INTO `Watch_Out_Virus` "
					+ "(`User_Mac`, `Mask_Amount`, `Alcohol_Amount`, `Clothes_Amount`, `Trump`, `Tzuyu`, `Clock`, `High_Score`, `Money`)"
					+ " VALUES ('"+this.macs+"', '"+this.maskAmount+"', '"+this.alcoholAmount+"', '"+this.clothesAmount+"',"
							+ " '"+this.trump+"', '"+this.tzuyu+"', '"+this.clock+"', '"+this.highScore+"', '"+this.money+"')";
			stat.executeUpdate(query);
			
		} catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stat!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }
	}
	
	public int getMaskAmount() throws SQLException{
		Statement stat = null;
		ResultSet result = null;
		PreparedStatement ps = null;
		
		  try {
			  setMacList();
			   conn = DriverManager.getConnection(url, username, password);
			   stat = conn.createStatement();
			   String query = "SELECT * FROM Watch_Out_Virus Where User_Mac='"+macs+"'";
			   ps=conn.prepareStatement(query); 
			   ResultSet rs=ps.executeQuery();
			   while (rs.next()){
			   this.maskAmount = rs.getInt(2);
			   }
			   
			  }catch(Exception e) {
				  
			  }
			  finally {
			   if (conn != null) {
			           conn.close();
			        }
			  }
		 return maskAmount; 
	}
	public int getAlcoholAmount() throws SQLException{
		Statement stat = null;
		ResultSet result = null;
		PreparedStatement ps = null;
		
		  try {
			  setMacList();
			   conn = DriverManager.getConnection(url, username, password);
			   stat = conn.createStatement();
			   String query = "SELECT * FROM Watch_Out_Virus Where User_Mac='"+macs+"'";
			   ps=conn.prepareStatement(query); 
			   ResultSet rs=ps.executeQuery();
			   while (rs.next()){
			   this.alcoholAmount = rs.getInt(3);
			   }
			  
			  }catch(Exception e) {
				  
			  }
			  finally {
			   if (conn != null) {
			           conn.close();
			        }
			  }
		 return alcoholAmount; 
	}
	public int getClothesAmount() throws SQLException{
		Statement stat = null;
		ResultSet result = null;
		PreparedStatement ps = null;
		
		  try {
			  setMacList();
			   conn = DriverManager.getConnection(url, username, password);
			   stat = conn.createStatement();
			   String query = "SELECT * FROM Watch_Out_Virus Where User_Mac='"+macs+"'";
			   ps=conn.prepareStatement(query); 
			   ResultSet rs=ps.executeQuery();
			   while (rs.next()){
			   this.clothesAmount = rs.getInt(4);
			   }
			   
			  }catch(Exception e) {
				  
			  }
			  finally {
			   if (conn != null) {
			           conn.close();
			        }
			  }
		 return clothesAmount; 
	}

	public void addMasks(int amount) throws SQLException{
		Statement stat = null;
		maskAmount = getMaskAmount() + amount;
		try {
			setMacList();
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
			String query = "UPDATE `Watch_Out_Virus` SET `Mask_Amount` = '"+maskAmount+"'  WHERE `User_Mac` = '"+macs+"' ";
			stat.executeUpdate(query);
			
		} catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stat!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }
	}
	public void addAlcohol(int amount)throws SQLException{
		Statement stat = null;
		alcoholAmount = getAlcoholAmount() + amount;
		try {
			setMacList();
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
			String query = "UPDATE `Watch_Out_Virus` SET `Alcohol_Amount` = '"+alcoholAmount+"'  WHERE `User_Mac` = '"+macs+"' ";
			stat.executeUpdate(query);
			
		} catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stat!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }
	}
	public void addClothes(int amount) throws SQLException{
		Statement stat = null;
		clothesAmount = getClothesAmount() + amount;
		try {
			setMacList();
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
			String query = "UPDATE `Watch_Out_Virus` SET `Clothes_Amount` = '"+clothesAmount+"'  WHERE `User_Mac` = '"+macs+"' ";
			stat.executeUpdate(query);
			
		} catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stat!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }
	}
	
	public void setTrump(int x) throws SQLException{
		Statement stat = null;
		trump = x;
		try {
			setMacList();
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
			String query = "UPDATE `Watch_Out_Virus` SET `Trump` = '"+trump+"'  WHERE `User_Mac` = '"+macs+"' ";
			stat.executeUpdate(query);
			
		} catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stat!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }
	}
	public int getTrump() throws SQLException{
		Statement stat = null;
		ResultSet result = null;
		PreparedStatement ps = null;
		
		  try {
			   setMacList();
			   conn = DriverManager.getConnection(url, username, password);
			   stat = conn.createStatement();
			   String query = "SELECT * FROM Watch_Out_Virus Where User_Mac='"+macs+"'";
			   ps=conn.prepareStatement(query); 
			   ResultSet rs=ps.executeQuery();
			   while (rs.next()){
			   this.trump = rs.getInt(5);
			   }
		  }
			  catch(Exception e) {
					  
				  }
	
			  finally {
			   if (conn != null) {
			           conn.close();
			        }
			  }
		 return trump; 
	}
	
	public void setTzuyu(int x) throws SQLException{
		Statement stat = null;
		tzuyu = x;
		try {
			setMacList();
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
			String query = "UPDATE `Watch_Out_Virus` SET `Tzuyu` = '"+tzuyu+"'  WHERE `User_Mac` = '"+macs+"' ";
			stat.executeUpdate(query);
			
		} catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stat!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }
	}
	public int getTzuyu() throws SQLException{
		Statement stat = null;
		ResultSet result = null;
		PreparedStatement ps = null;
		
		  try {
			   setMacList();
			   conn = DriverManager.getConnection(url, username, password);
			   stat = conn.createStatement();
			   String query = "SELECT * FROM Watch_Out_Virus Where User_Mac='"+macs+"'";
			   ps=conn.prepareStatement(query); 
			   ResultSet rs=ps.executeQuery();
			   while (rs.next()){
			   this.tzuyu = rs.getInt(6);
			   }
			  }catch(Exception e) {
				  
			  }
			  finally {
			   if (conn != null) {
			           conn.close();
			        }
			  }
		 return tzuyu; 
	}
	
	public void setClock(int x) throws SQLException{
		Statement stat = null;
		clock = x;
		try {
			setMacList();
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
			String query = "UPDATE `Watch_Out_Virus` SET `clock` = '"+clock+"'  WHERE `User_Mac` = '"+macs+"' ";
			stat.executeUpdate(query);
			
		} catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stat!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }
	}
	public int getClock() throws SQLException{
		Statement stat = null;
		ResultSet result = null;
		PreparedStatement ps = null;
		
		  try {
			   setMacList();
			   conn = DriverManager.getConnection(url, username, password);
			   stat = conn.createStatement();
			   String query = "SELECT * FROM Watch_Out_Virus Where User_Mac='"+macs+"'";
			   ps=conn.prepareStatement(query); 
			   ResultSet rs=ps.executeQuery();
			   while (rs.next()){
			   this.clock = rs.getInt(7);
			   }
			  
			  }catch(Exception e) {
				  
			  }
			  finally {
			   if (conn != null) {
			           conn.close();
			        }
			  }
		 return clock; 
	}	
	
	public void addMoney(int amount) throws SQLException{
		Statement stat = null;
		money = getMoney() + amount;
		try {
			setMacList();
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
			String query = "UPDATE `Watch_Out_Virus` SET `money` = '"+money+"'  WHERE `User_Mac` = '"+macs+"' ";
			stat.executeUpdate(query);
			
		} catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stat!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }
	}
	public int getMoney() throws SQLException{
		Statement stat = null;
		ResultSet result = null;
		PreparedStatement ps = null;
		
		  try {
			   setMacList();
			   conn = DriverManager.getConnection(url, username, password);
			   stat = conn.createStatement();
			   String query = "SELECT * FROM Watch_Out_Virus Where User_Mac='"+macs+"'";
			   ps=conn.prepareStatement(query); 
			   ResultSet rs=ps.executeQuery();
			   while (rs.next()){
			   this.money = rs.getInt(9);
			   }
			  
			  }catch(Exception e) {
				  
			  }
			  finally {
			   if (conn != null) {
			           conn.close();
			        }
			  }
		 return money; 
	}
	
	public void setHighScore(int score) throws SQLException{
		Statement stat = null;
		this.highScore = score;
		try {
			setMacList();
			conn = DriverManager.getConnection(url, username, password);
			stat = conn.createStatement();
			String query = "UPDATE `Watch_Out_Virus` SET `High_Score` = '"+highScore+"'  WHERE `User_Mac` = '"+macs+"' ";
			stat.executeUpdate(query);
			
		} catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stat!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }
	}
	public int getHighScore() throws SQLException{
		Statement stat = null;
		ResultSet result = null;
		PreparedStatement ps = null;
		
		  try {
			   setMacList();
			   conn = DriverManager.getConnection(url, username, password);
			   stat = conn.createStatement();
			   String query = "SELECT * FROM Watch_Out_Virus Where User_Mac='"+macs+"'";
			   ps=conn.prepareStatement(query); 
			   ResultSet rs=ps.executeQuery();
			   while (rs.next()){
			   this.highScore = rs.getInt(8);
			   }
			  
			  }catch(Exception e) {
				  
			  }
			  finally {
			   if (conn != null) {
			           conn.close();
			        }
			  }
		 return highScore; 
	}

	public void setAccount(String getAccountField) {
		// TODO Auto-generated method stub
		
	}

	public Object getUserPassWord() {
		// TODO Auto-generated method stub
		return null;
	}
	
	




}
	

