/**
 * 
 */
package presenter;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Time;
import java.time.*;
import java.time.format.DateTimeParseException;

import dbConnection.DBConnect;
import model.bean.EventBean;
import view.type.EventScreen;
import view.type.EventScreen.EventListener;

/**
 * Presenter class for EventScreen. Controls interaction between this GUI
 * element and other elements.
 * 
 * @author elijahr
 *
 */
public class EventScreenPresenter implements EventListener {

  private EventScreen eventScreen;
  
  private int dbeventID;
  private String dbeventTitle;
  private int dbeventCreatorUserID;
  private Date dbeventStartDate;
  private int dbeventAllDay;
  private Boolean eAD;
  private int allDay;
  private String dbeventMessage;

  // data connection variables
  private Connection conn;
  private String SQL;
  private ResultSet rset;
  private PreparedStatement prepStmt = null;
  private Statement stmt;
  
  private EventBean newEvent;  
  
  public EventScreenPresenter(EventScreen eventScreen) {
    this.eventScreen = eventScreen;
  }


  @Override
  public void hide() {
    eventScreen.hideEventScreen();
  }


  @Override
  public void save(EventBean event) {
    // [MJ] save the event in the database
	  
	  EventBean newEvent = event; // get event from EventScreenView > listener.save
	  
	  // get start Date
	  LocalDate eSDate = newEvent.getDate();
	  // convert LocalDate to util.Date
	  Instant inst = eSDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
	  java.util.Date sDate = Date.from(inst);
	  // convert util.date to sql.Date
	  java.sql.Date sqlDate = new java.sql.Date(sDate.getTime());
	  
	  
	  // convert eventAllDay
	  eAD = newEvent.getAllDayIndicator();
	  if (!eAD) {
		  dbeventAllDay = 0;
	  } else {
		  dbeventAllDay = 1;
	  }
	  
	  dbeventTitle = newEvent.getTitle();
	  dbeventCreatorUserID = 1; // not null value in DB, using value of 1 as default for testing
	  dbeventStartDate = sqlDate;
	  allDay = dbeventAllDay;
	  dbeventMessage = newEvent.getDescription();
	  String nextVal = "EVENT_SEQ.NEXTVAL";


	 	  
	  try {
		  conn = DBConnect.connect();
		  stmt = conn.createStatement();
		  SQL = "INSERT INTO EVENT_T (EVENTID, EVENTTITLE, EVENTCREATORUSERID, EVENTSTARTDATE, ESTIME, EETIME, EVENTALLDAY, EVENTMESSAGE) VALUES(" + nextVal + ", '" + dbeventTitle + "', " + dbeventCreatorUserID + ", TO_DATE(" + dbeventStartDate + ", 'YYYYMMDD'), " + allDay + ", '" + dbeventMessage + ")"; 
		  
		  stmt.executeUpdate(SQL);
		  
	/*	  prepStmt = conn.prepareStatement("INSERT INTO EVENT_T("
				+ "EVENTID,"
		  		+ "EVENTTITLE,"
				+ "EVENTTYPEID,"
		  		+ "EVENTCREATORUSERID,"
		  		+ "EVENTSTARTDATE,"
		  		+ "ESTIME,"
		  		+ "EETIME,"
		  		+ "EVENTALLDAY,"
		  		+ "EVENTMESSAGE, "
		  		+ "EVENTLOCATION"
		  		+ "EVENTADDRESS,"
		  		+ "EVENTCITY,"
		  		+ "EVENTSTATE,"
		  		+ "EVENTZIP, "
		  		+ "CREATEDDATE,"
		  		+ "LASTUPDATEDATE)"
		  		+ "VALUES('EVENTID_SEQ.NEXTVAL',?,?,?,?,?,?,?,?,?,?,?,?,?, SYSDATE, SYSDATE)");
		  
		  //prepStmt.setInt(1, EVENTID_SEQ.NEXTVAL);
		  prepStmt.setString(1, newEvent.getEventTitle());
		  prepStmt.setInt(2, 5); // not null value in DB, using value of 5 as default for testing
		  prepStmt.setInt(3, 1); // not null value in DB, using value of 1 as default for testing
		  prepStmt.setDate(4, sqlDate);
		  prepStmt.setDate(5, sqlSTime);
		  prepStmt.setDate(6, sqlETime);
		  prepStmt.setBoolean(7, newEvent.getEventAllDay());
		  prepStmt.setString(8, newEvent.getEventMessage());
		  prepStmt.setString(9, "Central Park");
		  prepStmt.setString(10, "1525 F Street");
		  prepStmt.setString(11, "New York");
		  prepStmt.setString(12, "NY");
		  prepStmt.setString(13, "20601");
		  prepStmt.executeUpdate();
		  
		  //prepStmt.close(); */
		  
		  stmt.close();
		  conn.close();
		 
	  } // end try block
	  
	  catch(NullPointerException ex) {
	        System.out.println("NullPointerException: " + ex.getMessage() + " " 
	                + ex.getCause() + "\nError: No record exists");
	  }
	  catch(DateTimeParseException ex) {
	      System.out.println("DateTimeParseException: Error: " + ex.getMessage());
	  }
	  catch(SQLException ex) {
	      System.out.println("SQLException: " + ex.getMessage() 
	              + "\nError: can not connect to database");
	      //loginScreen.showLoginScreen();
	  } // end catch
	    
	  //fail-all close database resources
	  finally {    
	      try {
	        
	          if(stmt != null) stmt.close();
	          if(conn!=null) conn.close();
	      }
	  catch(SQLException ex) {
	      System.out.println("Error: An error was detected while "
	                  + "closing the data source!");
	      } // end catch
	  } // end finally =============================================================
	  
	  
	  
      // [TESTING ONLY - get event values and print to output        
//         System.out.println( "\n"
//         + newEvent.getEventTitle()  + "\n"
//         + newEvent.getEventCreatorUserID() + "\n"
//         + newEvent.getEventStartDate() + "\n"
//         + newEvent.getEventStartTime() + "\n"
//         + newEvent.getEventEndTime() + "\n"
//         + newEvent.getEventAllDay() + "\n"
//         + newEvent.getEventMessage() + "\n\n");
  }


  @Override
  public void delete(EventBean event) {
    // [MJ] delete the event from the database
  }
}
