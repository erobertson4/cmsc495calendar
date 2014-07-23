/**
 * 
 */
package presenter;

import dbConnection.DBConnect;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import model.bean.EventBean;
import model.bean.UserBean;
import view.type.Day;
import view.type.Day.DayListener;
import view.type.EventScreen;
import view.ui.EventScreenView;

/**
 * Presenter class for Day. Controls interaction between this GUI element and
 * other elements.
 * 
 * @author elijahr
 * @author mattj
 */
public class DayPresenter implements DayListener {

  private Day day;
  private UserBean userBean;
 
  
  // data connection variables
  private Connection conn;
  private String SQL;
  private ResultSet rset;
  private Statement stmt;
  
  // database value variables
  private int dbeventID;
  private String dbeventTitle;
  private int dbeventTypeID;
  private int dbeventCreatorUserID;
  private LocalDate dbeventStartDate;
  private LocalDateTime dbeventStartTime;
  private LocalDateTime dbeventEndTime;
  private Boolean dbeventAllDay;
  private String dbeventMessage;
  private String dbeventLocation;
  private String dbeventAddress;
  private String dbeventCity;
  private String dbeventState;
  private String dbeventZip;
  private Date dbCreatedDate;
  private Date dbLastUpdateDate;
  private EventBean newEvent;
  
  public DayPresenter(Day day) {
    this.day = day;
  }


  @Override
  public void getEvents() {
      
    GregorianCalendar gregorianCalendar = day.getGregorianCalendar();

    // ======================================================================
    // retrieve the list of events with the given GregorianCalendar
    // ======================================================================
    
    ArrayList<EventBean> eventsArray = new ArrayList<EventBean>() {};
    int i = 0; // used to loop through ArrayList in while statement below
    
    // convert gregorianCalendar day to LocalDate type
    LocalDate gCDay = gregorianCalendar.toZonedDateTime().toLocalDate();
    
    // convert LocalDate type to Date type for use in query where clause
     /*
     * +=========================================================================+ 
     *     everything works fine up to this point. the gregorianCalendar variable
     *     must match the format type in the databse in order to use it in the 
     *     where clause of the select statement. I have successfully converted
     *     it into a localDate type, now it must be converted to java.sql.date
     *     type and again, the format MUST! match the database date format which
     *     is yyyy-mm-dd, i.e. 2014-07-08. I will test after completing the Save
     *     functionality in EventScreenView page.
     * +=======================================================================+
     */

    try {
        conn = DBConnect.connect();        
        stmt = conn.createStatement();
        SQL = "select EVENTID, "
                + "EVENTTITLE, "
                + "EVENTTYPEID, "
                + "EVENTCREATORUSERID, "
                + "EVENTSTARTDATE, "
//                + "to_char(ESTIME, 'hh:mi AM'), "
//                + "to_char(EETIME, 'hh:mi AM'), "
                + "ESTIME, "
                + "EETIME, "
                + "EVENTALLDAY, "
                + "EVENTMESSAGE, "
                + "EVENTLOCATION, "
                + "EVENTADDRESS, "
                + "EVENTCITY, "
                + "EVENTSTATE, "
                + "EVENTZIP, "
                + "CREATEDDATE, "
                + "LASTUPDATEDATE  from event_t "
                + "order by eventid";
        rset = stmt.executeQuery(SQL);
        
       
        // add resultSet value to database variable
        while(rset.next()) {
            dbeventID = rset.getInt("EVENTID");
            dbeventTitle = rset.getString("EVENTTITLE");
            dbeventTypeID = rset.getInt("EVENTTYPEID");
            dbeventCreatorUserID = rset.getInt("EVENTCREATORUSERID");
            dbeventStartDate = rset.getDate("EVENTSTARTDATE").toLocalDate();
            // following lines converts db value to LocatDateTime type
            Date dbSTime = rset.getDate("ESTIME");
            Instant instST = Instant.ofEpochMilli(dbSTime.getTime());
            dbeventStartTime = LocalDateTime.ofInstant(instST, ZoneId.systemDefault());
            Date dbETime = rset.getDate("EETIME"); 
            Instant instET = Instant.ofEpochMilli(dbETime.getTime());
            dbeventEndTime = LocalDateTime.ofInstant(instET, ZoneId.systemDefault());
            dbeventAllDay = rset.getBoolean("EVENTALLDAY");
            dbeventMessage = rset.getString("EVENTMESSAGE");
            dbeventLocation = rset.getString("EVENTLOCATION");
            dbeventAddress = rset.getString("EVENTADDRESS");
            dbeventCity = rset.getString("EVENTCITY");
            dbeventState = rset.getString("EVENTSTATE");
            dbeventZip = rset.getString("EVENTZIP");
            dbCreatedDate = rset.getDate("CREATEDDATE");
            dbLastUpdateDate = rset.getDate("LASTUPDATEDATE");

        
         /*
            * create EventBean with values from
            * database, NOT with values inserted by user
            */
            newEvent = new EventBean();
            newEvent.setEventID(dbeventID);
            newEvent.setEventTitle(dbeventTitle);
            newEvent.setEventTypeID(dbeventTypeID);
            newEvent.setEventCreatorUserID(dbeventCreatorUserID);
            newEvent.setEventStartDate(dbeventStartDate);
            newEvent.setEventStartTime(dbeventStartTime);
            newEvent.setEventEndTime(dbeventEndTime);
            newEvent.setEventAllDay(dbeventAllDay);
            newEvent.setEventMessage(dbeventMessage);
            newEvent.setEventLocation(dbeventLocation);
            newEvent.setEventAddress(dbeventAddress);
            newEvent.setEventCity(dbeventCity);
            newEvent.setEventState(dbeventState);
            newEvent.setEventZip(dbeventZip);
            newEvent.setCreatedDate(dbCreatedDate);
            newEvent.setLastUpdateDate(dbLastUpdateDate);
            
            // add event bean to List
            eventsArray.add(i, newEvent); // add eventbean to List
            i++; // iterate events    

            newEvent = null; // clears newEvent bean for next record
         
        } // end while loop

            day.setEvents(eventsArray); // add each eventBean to List

        // close all db connections
        rset.close();
        conn.close();
        stmt.close();
        
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
            if(rset != null) rset.close();
            if(conn!=null) conn.close();
            if(stmt != null) stmt.close();
        }
        catch(SQLException ex) {
            System.out.println("Error: An error was detected while "
                    + "closing the data source!");
        } // end catch
    } // end finally =============================================================
    
    // Since listener methods should return null, we have to set the list of
    // events for the day rather than returning the list.
    
  }


  @Override
  public void showEventScreen(EventBean eventBean, UserBean userBean) {
    EventScreen eventScreen = new EventScreenView(eventBean, userBean);
    EventScreenPresenter eventScreenPresenter = new EventScreenPresenter(eventScreen);
    eventScreen.setEventListener(eventScreenPresenter);

    eventScreen.showEventScreen();
  }

}
