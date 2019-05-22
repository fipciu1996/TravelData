package zad1;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    String user = "UTP";
    String password = "UTP";
    String url; // url = jdbc:postgresql://127.0.0.1:32706/UTP | Main()
    String createdDatabaseName; // TravelData | create()
    Logger log = Logger.getLogger(Database.class.getName());
	Database(String url, TravelData travelData){
		url = this.url;
		// Testing connection to database
	    try (Connection con = DriverManager.getConnection(url, user, password);
	            Statement st = con.createStatement();
	            ResultSet rs = st.executeQuery("SELECT VERSION()")) {
	    	con.close();
	    } catch (SQLException ex) {
	    
	        log.log(Level.SEVERE, ex.getMessage(), ex);
	    }
	}
	
	// Creating database TravelData
	public void create() {
	    try (Connection con = DriverManager.getConnection(url, user, password);
	    		Statement st = con.createStatement();) {
	    	String[] splitValue = url.trim().split(":");
			if (splitValue[1].equals("postgresql")) {
				try {
					st.execute("SET AUTOCOMMIT = ON");
				} catch (SQLException e) {
					log.log(Level.INFO, e.getMessage(), e);
				}
			}
			String createquery = "CREATE DATABASE TravelData";
			splitValue = createquery.trim().split(" ");
			createdDatabaseName = splitValue[splitValue.length-1].toLowerCase();
			try {
				st.execute(createquery);
			} catch (SQLException e) {
				log.log(Level.INFO, e.getMessage(), e);
			}
	    	con.close();
	    } catch (SQLException ex) {
	        log.log(Level.SEVERE, ex.getMessage(), ex);
	    } 
	}
	public void showGui() {
		
	}
	
	public String getNewURL() {
		// url = jdbc:postgresql://127.0.0.1:32706/UTP
		String[] splitValue = url.trim().split(":"); // [jdbc][postgresql][//127.0.0.1][32706/UTP]
		String[] anotherSplitValue = splitValue[splitValue.length-1].trim().split("/"); // [32706][UTP]
		anotherSplitValue[anotherSplitValue.length-1] = createdDatabaseName; // [32706][createdDatabaseName]
		//splitValue[0] = jdbc
		//splitValue[1] = postgresql
		//splitValue[2] = //127.0.0.1
		//anotherSplitValue[0] = 32706
		//anotherSplitValue[1] = createdDatabaseName
		url = splitValue[0] + ":" + splitValue[1] + ":" + splitValue[2] + ":" + anotherSplitValue[0] + "/" + anotherSplitValue[1];
		return url;
	}
	
	// Creating data structure in created database with another url 
	public void  createDb() {
		
	    connectToDatabase();
	}
	
	public void connectToDatabase() {
	    try (Connection con = DriverManager.getConnection(getNewURL(), user, password);
	            Statement st = con.createStatement();
	            ResultSet rs = st.executeQuery("SELECT VERSION()")) {
	    } catch (SQLException ex) {
	    
	        log.log(Level.SEVERE, ex.getMessage(), ex);
	    }
	}
	
	public void createTable() {

	}
}
