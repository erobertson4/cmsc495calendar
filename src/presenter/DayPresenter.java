/**
 * 
 */
package presenter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import model.bean.EventBean;
import model.bean.UserBean;
import view.type.Day;
import view.type.Day.DayListener;
import view.type.EventScreen;
import view.ui.EventScreenView;
import dbConnection.DBConnect;

/**
 * Presenter class for Day. Controls interaction between this GUI element and
 * other elements.
 * 
 * @author elijahr
 * @author mattj
 */
public class DayPresenter implements DayListener {

  private Day day;
  
  // data connection variables
  private Connection conn;
  private String SQL;
  private ResultSet rset;
  private Statement stmt;
  
  // database value variables
  private int dbId;
  private String dbTitle;
  private int dbCreatorId;
  private LocalDate dbDate;
  private Boolean dbAllDayIndicator;
  private int dbStartHour;
  private int dbStartMinute;
  private Boolean dbStartAMIndicator;
  private int dbEndHour;
  private int dbEndMinute;
  private Boolean dbEndAMIndicator;
  private String dbDescription;
  
  private EventBean newEvent;
  
  public DayPresenter(Day day) {
    this.day = day;
  }


  @Override
  public void getEvents() {
      
    // ======================================================================
    // retrieve the list of events with the given GregorianCalendar
    // ======================================================================
    
    ArrayList<EventBean> eventsArray = new ArrayList<EventBean>();
    int i = 0; // used to loop through ArrayList in while statement below
    
    // convert LocalDate type to Date type for use in query where clause
     /*
     * +=========================================================================+ 
     *     everything works fine up to this point. the gregorianCalendar variable
     *     must match the format type in the database in order to use it in the 
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
        SQL = "select ID, "
                + "TITLE, "
                + "CREATORID, "
                + "SDATE, "
                + "SHOUR, "
                + "SMIN, "
                + "EHOUR, "
                + "EMIN, "
                + "SAM, "
                + "EAM, "
                + "ALLDAY, "
                + "MESSAGE  from event_t "
                + "order by id";
        rset = stmt.executeQuery(SQL);
        
       
        // add resultSet value to database variable
        while(rset.next()) {
            dbId = rset.getInt("ID");
            dbTitle = rset.getString("TITLE");
            dbCreatorId = rset.getInt("CREATORID");
            dbDate = rset.getDate("SDATE").toLocalDate();
            dbStartHour = rset.getInt("SHOUR");
            dbStartMinute = rset.getInt("SMIN");
            dbEndHour = rset.getInt("EHOUR");
            dbEndMinute = rset.getInt("EMIN");
            dbStartAMIndicator = rset.getBoolean("SAM");
            dbEndAMIndicator = rset.getBoolean("EAM");
            dbAllDayIndicator = rset.getBoolean("ALLDAY");
            dbDescription = rset.getString("MESSAGE");

        
         /*
            * create EventBean with values from
            * database, NOT with values inserted by user
            */
            newEvent = new EventBean();
            newEvent.setId(dbId);
            newEvent.setTitle(dbTitle);
            newEvent.setCreatorId(dbCreatorId);
            newEvent.setDate(dbDate);
            newEvent.setStartHour(dbStartHour);
            newEvent.setStartMinute(dbStartMinute);
            newEvent.setEndHour(dbEndHour);
            newEvent.setEndMinute(dbEndMinute);
            newEvent.setEndAMIndicator(dbEndAMIndicator);
            newEvent.setStartAMIndicator(dbStartAMIndicator);
            newEvent.setAllDayIndicator(dbAllDayIndicator);
            newEvent.setDescription(dbDescription);
            
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
