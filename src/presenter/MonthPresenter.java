/**
 * 
 */
package presenter;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.bean.EventBean;

import org.controlsfx.dialog.Dialogs;

import view.type.Month;
import view.type.Month.MonthListener;
import dbConnection.DBConnect;

/**
 * @author elijahr
 * @author mattj
 */
public class MonthPresenter implements MonthListener {

  private Month month;

  // data connection variables
  private Connection conn;
  private String SQL;
  private ResultSet rset;
  private Statement stmt;

  // database value variables
  private int dbId;
  private String dbTitle;
  private int dbCreatorId;
  private LocalDate dbSDate;
  private Boolean dbAllDay;
  private int dbSHour;
  private int dbSMin;
  private Boolean dbSam;
  private int dbEHour;
  private int dbEMin;
  private Boolean dbEam;
  private String dbMessage;

  private EventBean dayEvent;


  public MonthPresenter(Month month) {
    this.month = month;
  }


  @Override
  public void getEvents(LocalDate startDate, LocalDate endDate) {
    List<EventBean> resultArray = new ArrayList<EventBean>();
    int i = 0;

    // convert startDate of LocalDate type to java.sql.Date type
    LocalDate eSDate = startDate;
    // convert LocalDate to util.Date
    Instant instSD = eSDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
    java.util.Date sDate = Date.from(instSD);
    // convert util.date to sql.Date
    java.sql.Date sqlSDate = new java.sql.Date(sDate.getTime());

    // convert endDate of LocalDate type to java.sql.Date type
    LocalDate eEDate = endDate;
    // convert LocalDate to util.Date
    Instant instED = eEDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
    java.util.Date eDate = Date.from(instED);
    // convert util.date to sql.Date
    java.sql.Date sqlEDate = new java.sql.Date(eDate.getTime());

    // =======================================================================
    // query the DB to get all events between the two dates (inclusive)
    // =======================================================================

    try {
      conn = DBConnect.connect();
      stmt = conn.createStatement();
      SQL = "select * "
          + "FROM EVENT_T " + "WHERE SDATE > TO_DATE('" + sqlSDate + "', 'YYYY-MM-DD') "
          + "AND SDATE < TO_DATE('" + sqlEDate + "', 'YYYY-MM-DD') ORDER BY SDATE ";

      rset = stmt.executeQuery(SQL);

      // add resultSet value to database variable
      while (rset.next()) {
        dbId = rset.getInt("ID");
        dbTitle = rset.getString("TITLE");
        dbCreatorId = rset.getInt("CREATORID");
        dbSDate = rset.getDate("SDATE").toLocalDate();
        dbSHour = rset.getInt("SHOUR");
        dbSMin = rset.getInt("SMIN");
        dbEHour = rset.getInt("EHOUR");
        dbEMin = rset.getInt("EMIN");
        dbSam = rset.getBoolean("SAM");
        dbEam = rset.getBoolean("EAM");
        dbAllDay = rset.getBoolean("ALLDAY");
        dbMessage = rset.getString("MESSAGE");

        // create EventBean with values from database
        dayEvent = new EventBean();
        dayEvent.setId(dbId);
        dayEvent.setTitle(dbTitle);
        dayEvent.setCreatorId(dbCreatorId);
        dayEvent.setDate(dbSDate);
        dayEvent.setStartHour(dbSHour);
        dayEvent.setStartMinute(dbSMin);
        dayEvent.setEndHour(dbEHour);
        dayEvent.setEndMinute(dbEMin);
        dayEvent.setEndAMIndicator(dbEam);
        dayEvent.setStartAMIndicator(dbSam);
        dayEvent.setAllDayIndicator(dbAllDay);
        dayEvent.setDescription(dbMessage);

        // add event bean to List
        resultArray.add(i, dayEvent); // add eventbean to List
        i++; // iterate events

        dayEvent = null; // clears dayEvent bean for next record

      } // end while loop

      // close all db connections
      rset.close();
      stmt.close();
      conn.close();

    } // end try block

    catch (NullPointerException ex) {
      Dialogs
          .create()
          .owner(null)
          .title("Cannot Retrieve Events")
          .message(
              "Cannot retrieve events. Please try again. If problem persists,"
                  + " please contact tech support.").showError();
    }
    catch (SQLException ex) {
      Dialogs
          .create()
          .owner(null)
          .title("Cannot Connect to Database")
          .message(
              "Cannot connect to database. Please try again. If problem persists,"
                  + " please contact tech support.").showError();
      // loginScreen.showLoginScreen();
    } // end catch

    // fail-all close database resources
    finally {
      try {
        if (rset != null)
          rset.close();
        if (stmt != null)
          stmt.close();
        if (conn != null)
          conn.close();
      }
      catch (SQLException ex) {
        Dialogs.create().owner(null).title("Error Closing Datasource")
            .message("There was an error closing the datasource. Please exit the application.")
            .showError();
      } // end catch
    } // end finally
      // =============================================================

    Map<LocalDate, List<EventBean>> events = new HashMap<LocalDate, List<EventBean>>();
    for (EventBean event : resultArray) {
      List<EventBean> eventsForDay = events.get(event.getDate());
      if (eventsForDay == null) {
        eventsForDay = new ArrayList<EventBean>();
      }
      eventsForDay.add(event);
      events.put(event.getDate(), eventsForDay);
    }

    month.setEvents(events);
  }

}
