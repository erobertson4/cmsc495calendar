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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import dbConnection.DBConnect;
import model.bean.EventBean;
import view.type.EventScreen;
import view.type.EventScreen.EventListener;

/**
 * Presenter class for EventScreen. Controls interaction between this GUI
 * element and other elements.
 * 
 * @author elijahr
 * @author mattj
 */
public class EventScreenPresenter implements EventListener {

  private EventScreen eventScreen;
  
  private int dbID;
  private String dbTitle;
  private int dbCreatorID;
  private Date dbSDate;
  private int dbSHour;
  private int dbSMin;
  private int dbEHour;
  private int dbEMin;
  private int dbSam;
  private int dbEam;
  private int dbAllDay;
  private Boolean eAD;
  private Boolean eSam;
  private int sAM;
  private int eAM;
  private Boolean eEam;
  private int allDay;
  private String dbMessage;

  // data connection variables
  private Connection conn;
  private String SQL;
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
		  dbAllDay = 0;
	  } else {
		  dbAllDay = 1;
	  }
	  
	  
	  // convert StartAM
	  eSam = newEvent.getStartAMIndicator();
	  if (!eSam) {
		  dbSam = 0;
	  } else {
		  dbSam = 1;
	  }
	  
	  
	  // convert EndAM
	  eEam = newEvent.getEndAMIndicator();
	  if (!eEam) {
		  dbEam = 0;
	  } else {
		  dbEam = 1;
	  }
	  
	  dbID = newEvent.getId();
	  dbTitle = newEvent.getTitle();
	  dbCreatorID = newEvent.getCreatorId(); 
	  dbSDate = sqlDate;
	  dbSHour = newEvent.getStartHour();
	  dbSMin = newEvent.getStartMinute();
	  dbEHour = newEvent.getEndHour();
	  dbEMin = newEvent.getEndMinute();
	  allDay = dbAllDay;
	  sAM = dbSam;
	  eAM = dbEam;
	  dbMessage = newEvent.getDescription();
	  
	  // variables for accessing event_seq.nextval
	  int eventSEQ = 0;
	  
	  System.out.println("This is eventID: " + dbID);

	  //=========================================================================
	  // get event sequence next value
	  //=========================================================================
	  try {
		  conn = DBConnect.connect();
		  String sqlIdentifier = "select EVENT_SEQ.NEXTVAL FROM EVENT_T";
		  prepStmt = conn.prepareStatement(sqlIdentifier);
		  synchronized( this ) {
			  ResultSet rs = prepStmt.executeQuery();
			  if(rs.next())
				  eventSEQ = rs.getInt(1);
		  }
		  
		  prepStmt.close();
		  conn.close();
		
	  } // end try block
	  
	  catch(NullPointerException ex) {
	        System.out.println("NullPointerException: " + ex.getMessage() + " " 
	                + ex.getCause() + "\nNo NextVal");
	  }
	  catch(SQLException ex) {
	      System.out.println("SQLException: " + ex.getMessage() 
	              + "\nError: Can not get NextVal");
	  } // end catch
	    
	  //fail-all close database resources
	  finally {    
	      try {
	        
	          if(prepStmt != null) prepStmt.close();
	          if(conn != null) conn.close();
	      }
	      catch(SQLException ex) {
		      System.out.println("Error: An error was detected while "
		                  + "closing the data source!");
		      } // end catch
	  } // end finally =============================================================
	  
	  
	  //============================================================================
	  // insert event to database
	  //============================================================================
	  try {
		  conn = DBConnect.connect();
		  
		  // if new record (which will = 0) do insert, if existing do update
		  if (dbID == 0){
			  // add new event
			  String SQL2 = "INSERT INTO EVENT_T "
					  + "(ID, TITLE, CREATORID, SDATE, SHOUR, SMIN, "
					  + "EHOUR, EMIN, SAM, EAM, ALLDAY, MESSAGE) "
					  + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

				  prepStmt = conn.prepareStatement(SQL2);
				  
				  prepStmt.clearParameters();
				  prepStmt.setInt(1, eventSEQ);
				  prepStmt.setString(2, dbTitle);
				  prepStmt.setInt(3, dbCreatorID);
				  prepStmt.setDate(4, dbSDate);
				  prepStmt.setInt(5, dbSHour);
				  prepStmt.setInt(6, dbSMin);
				  prepStmt.setInt(7, dbEHour);
				  prepStmt.setInt(8, dbEMin);
				  prepStmt.setInt(9, sAM);
				  prepStmt.setInt(10, eAM);
				  prepStmt.setInt(11, allDay);
				  prepStmt.setString(12, dbMessage);
				  prepStmt.executeUpdate();
				  
				  prepStmt.close();
				  conn.close();
				  
		  } else {
			  // update record
			 String SQL3 = "UPDATE EVENT_T SET "
			  		+ "TITLE = ?, "
			  		+ "CreatorID = ?, "
			  		+ "SDATE = ?, "
			  		+ "SHOUR = ?, "
			  		+ "SMIN = ?, "
			  		+ "EHOUR = ?, "
			  		+ "EMIN = ?, "
			  		+ "SAM = ?, "
			  		+ "EAM = ?, "
			  		+ "ALLDAY = ?,"
			  		+ "MESSAGE = ? "
			  		+ "WHERE ID = ?";
				  
				  prepStmt = conn.prepareStatement(SQL3);
				  
				  prepStmt.clearParameters();
				  prepStmt.setString(1, dbTitle);
				  prepStmt.setInt(2, dbCreatorID);
				  prepStmt.setDate(3, dbSDate);
				  prepStmt.setInt(4, dbSHour);
				  prepStmt.setInt(5, dbSMin);
				  prepStmt.setInt(6, dbEHour);
				  prepStmt.setInt(7, dbEMin);
				  prepStmt.setInt(8, sAM);
				  prepStmt.setInt(9, eAM);
				  prepStmt.setInt(10, allDay);
				  prepStmt.setString(11, dbMessage);
				  prepStmt.setInt(12, dbID);
				  prepStmt.executeUpdate();
				  
				  prepStmt.close();
				  conn.close();

		  } // end else
		 
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
	        
	          if(prepStmt != null) prepStmt.close();
	          if(conn != null) conn.close();
	      }
	  catch(SQLException ex) {
	      System.out.println("Error: An error was detected while "
	                  + "closing the data source!");
	      } // end catch
	  } // end finally =============================================================

  }


  @Override
  public void delete(EventBean event) {
	  
	  //==============================================================================  
	  // delete the event from the database
	  //==============================================================================
	  
	  EventBean delEvent = event;
	  
	  // get ID of event
	  int evID = delEvent.getId();
	  System.out.println(evID);
	  
	  try {
		  conn = DBConnect.connect();
		  stmt = conn.createStatement();
		  SQL = "DELETE FROM EVENT_T WHERE ID = " + evID ; 
		  stmt.executeUpdate(SQL);

		  
		  stmt.close();
		  conn.close();
		
	  } // end try block
	  
	  catch(NullPointerException ex) {
	        System.out.println("NullPointerException: " + ex.getMessage() + " " 
	                + ex.getCause() + "\nRecord Does Not Exist.");
	  }
	  catch(SQLException ex) {
	      System.out.println("SQLException: " + ex.getMessage() 
	              + "\nError: Can not access data.");
	  } // end catch
	    
	  //fail-all close database resources
	  finally {    
	      try {
	        
	          if(stmt != null) stmt.close();
	          if(conn != null) conn.close();
	      }
	      catch(SQLException ex) {
		      System.out.println("Error: An error was detected while "
		                  + "closing the data source!");
		      } // end catch
	  } // end finally =============================================================
  }
}
