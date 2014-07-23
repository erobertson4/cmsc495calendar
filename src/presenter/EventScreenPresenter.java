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
		  stmt = conn.createStatement();
		  
		  // if new record (which will = 0) do insert, if existing do update
		  if (dbID == 0){
			  // add new event

			  // if event is allDay
			  if (allDay != 1) {
				  // insert start and end times
				  SQL = "INSERT INTO EVENT_T "
				  		+ "(ID, TITLE, CREATORID, SDATE, SHOUR, SMIN, "
				  		+ "EHOUR, EMIN, SAM, EAM, ALLDAY, MESSAGE) "
				  		+ "VALUES(" + eventSEQ + ", '"
				  		+ dbTitle + "', " 
				  		+ dbCreatorID + ", TO_DATE('" + dbSDate + "', 'YYYY-MM-DD'), " 
				  		+ dbSHour + ", "  
				  		+ dbSMin + ", " 
				  		+ dbEHour + ", " 
				  		+ dbEMin + ", " 
				  		+ sAM + ", " 
				  		+ eAM + ", " 
				  		+ allDay + ", '" 
				  		+ dbMessage + "')"; 
			  } else {
				  // insert as all day event
				  System.out.println("This is uesr ID:" + dbCreatorID);
				  SQL = "INSERT INTO EVENT_T "
				  		+ "(ID, TITLE, CREATORID, SDATE, SHOUR, SMIN, "
				  		+ "EHOUR, EMIN, SAM, EAM, ALLDAY, MESSAGE) "
				  		+ "VALUES(" + eventSEQ + ", '" 
				  		+ dbTitle + "', " 
				  		+ dbCreatorID + ", TO_DATE('" + dbSDate + "', 'YYYY-MM-DD'), "
				  		+ "'', '', '', '', '', '', "  
				  		+ allDay + ", '" 
				  		+ dbMessage + "')"; 
			  } // end if: allDay
		  } else {
			  // update record
			  if (allDay != 1) {
				  
				  // update with start and end times
				  SQL = "UPDATE EVENT_T SET "
						+ "TITLE = '" + dbTitle + "', "
						+ "SDATE = TO_DATE('" + dbSDate + "', 'YYYY-MM-DD'),"
						+ "SHOUR = " + dbSHour + ", " 
						+ "SMIN = " + dbSMin + ", "
						+ "EHOUR = " + dbEHour + ", "
						+ "EMIN = " + dbEMin + ", "
						+ "SAM = " + sAM + ", "
						+ "eAM = " + eAM + ", "
						+ "ALLDAY = " + allDay + ", "
					    + "MESSAGE = '" + dbMessage + "' WHERE ID = " + dbID + " ";
			  }  else {
				  // update as all day event
				  SQL = "UPDATE EVENT_T SET "
						+ "TITLE = '" + dbTitle + "', "
						+ "SDATE = TO_DATE('" + dbSDate + "', 'YYYY-MM-DD'),"
						+ "SHOUR = '', " 
						+ "SMIN = '', " 
						+ "EHOUR = '', " 
						+ "EMIN = '', " 
						+ "SAM = '', " 
						+ "eAM = '', " 
						+ "ALLDAY = " + allDay + ", "
						+ "MESSAGE = '" + dbMessage + "' WHERE ID = " + dbID + " ";
			  } // end if: allDay
		  } // end else
		  
		  
		  stmt.executeUpdate(SQL);

		  stmt.close();
		  conn.close();
		// =======================================================================
		// DO NOT DELETE PREPAREDSTATEMENT CODE BELOW!!
		//========================================================================
			/*String sqlInsert = "INSERT INTO EVENT_T("
				+ "ID,"
		  		+ "TITLE,"
		  		+ "CREATORID,"
		  		+ "SDATE,"
		  		+ "SHOUR,"
		  		+ "SMIN,"
		  		+ "EHOUR,"
		  		+ "EMIN, "
		  		+ "SAM"
		  		+ "EAM,"
		  		+ "ALLDAY,"
		  		+ "MESSAGE)"
		  		+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)"; 
			
			prepStmt = conn.prepareStatement(sqlInsert);
		  
		  prepStmt.setInt(1, eventSEQ);
		  prepStmt.setString(2, newEvent.getTitle());
		  prepStmt.setInt(3, newEvent.getCreatorId());
		  prepStmt.setDate(4, TO_DATE(dbSDate , 'YYYY-MM-DD')); // could not get this line to work!!
		  prepStmt.setInt(5, newEvent.getStartHour());
		  prepStmt.setInt(6, newEvent.getStartMinute());
		  prepStmt.setInt(7, newEvent.getEndHour());
		  prepStmt.setInt(8, newEvent.getEndMinute());
		  prepStmt.setInt(9, dbSam);
		  prepStmt.setInt(10, dbEam);
		  prepStmt.setInt(11, dbAllDay);
		  prepStmt.setString(12, newEvent.getDescription());
		  prepStmt.executeUpdate();
		  
		  prepStmt.close(); */
		  
		  
		 
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
