/**
 * 
 */
package presenter;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.List;

import model.bean.EventBean;

import org.controlsfx.dialog.Dialogs;

import view.type.Day;
import view.type.EventScreen;
import view.type.EventScreen.EventListener;
import view.type.Month;
import dbConnection.DBConnect;

/**
 * Presenter class for EventScreen. Controls interaction between this GUI
 * element and other elements.
 * 
 * @author elijahr
 * @author mattj
 */
public class EventScreenPresenter implements EventListener {

  private EventScreen eventScreen;
  private Month month;
  private Day day;

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


  public EventScreenPresenter(EventScreen eventScreen, Month month, Day day) {
    this.eventScreen = eventScreen;
    this.day = day;
    this.month = month;
    ;
  }


  @Override
  public void hide() {
    eventScreen.hideEventScreen();
  }


  @Override
  public void save(EventBean event) {
    // [MJ] save the event in the database

    EventBean newEvent = event; // get event from EventScreenView >
                                // listener.save

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
    }
    else {
      dbAllDay = 1;
    }

    // convert StartAM
    eSam = newEvent.getStartAMIndicator();
    if (!eSam) {
      dbSam = 0;
    }
    else {
      dbSam = 1;
    }

    // convert EndAM
    eEam = newEvent.getEndAMIndicator();
    if (!eEam) {
      dbEam = 0;
    }
    else {
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

    // =========================================================================
    // get event sequence next value
    // =========================================================================
    try {
      conn = DBConnect.connect();
      String sqlIdentifier = "select EVENT_SEQ.NEXTVAL FROM EVENT_T";
      prepStmt = conn.prepareStatement(sqlIdentifier);
      synchronized (this) {
        ResultSet rs = prepStmt.executeQuery();
        if (rs.next())
          eventSEQ = rs.getInt(1);
      }

      prepStmt.close();
      conn.close();

    } // end try block

    catch (NullPointerException ex) {
      Dialogs
          .create()
          .owner(null)
          .title("Error Updating Event")
          .message(
              "Cannot update event. Please try again. If problem persists,"
                  + " please contact tech support.").showError();
    }
    catch (SQLException ex) {
      Dialogs
          .create()
          .owner(null)
          .title("Error Updating Event")
          .message(
              "Cannot update event. Please try again. If problem persists,"
                  + " please contact tech support.").showError();
    } // end catch

    // fail-all close database resources
    finally {
      try {

        if (prepStmt != null)
          prepStmt.close();
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

    // ============================================================================
    // insert event to database
    // ============================================================================
    try {
      conn = DBConnect.connect();

      // if new record (which will = 0) do insert, if existing do update
      if (dbID == 0) {
        // add new event
        String SQL2 = "INSERT INTO EVENT_T " + "(ID, TITLE, CREATORID, SDATE, SHOUR, SMIN, "
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

      }
      else {
        // update record
        String SQL3 = "UPDATE EVENT_T SET " + "TITLE = ?, " + "CreatorID = ?, " + "SDATE = ?, "
            + "SHOUR = ?, " + "SMIN = ?, " + "EHOUR = ?, " + "EMIN = ?, " + "SAM = ?, "
            + "EAM = ?, " + "ALLDAY = ?," + "MESSAGE = ? " + "WHERE ID = ?";

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

    catch (NullPointerException ex) {
      Dialogs
          .create()
          .owner(null)
          .title("Cannot Update Event")
          .message(
              "Cannot update event. Please try again. If problem persists,"
                  + " please contact tech support.").showError();
    }
    catch (DateTimeParseException ex) {
      Dialogs
          .create()
          .owner(null)
          .title("Cannot Update Event")
          .message(
              "Cannot update event. Please try again. If problem persists,"
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

        if (prepStmt != null)
          prepStmt.close();
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

    // remove the event from the GUI and then add it if it belongs back on the
    // screen
    if (day != null) {
      day.removeEvent(event);
    }

    List<Day> days = month.getDays();
    for (int index = 0; index < days.size(); index++) {
      if (days.get(index).getLocalDate().equals(event.getDate())) {
        month.getDays().get(index).saveEvent(event);
        break;
      }
    }
  }


  @Override
  public void delete(EventBean event) {

    // ==============================================================================
    // delete the event from the database
    // ==============================================================================

    EventBean delEvent = event;

    // get ID of event
    int evID = delEvent.getId();

    try {
      conn = DBConnect.connect();
      stmt = conn.createStatement();
      SQL = "DELETE FROM EVENT_T WHERE ID = " + evID;
      stmt.executeUpdate(SQL);

      stmt.close();
      conn.close();

    } // end try block

    catch (NullPointerException ex) {
      Dialogs.create().owner(null).title("Error Deleting Event")
          .message("Event cannot be found. Please contact tech support.").showError();
    }
    catch (SQLException ex) {
      Dialogs
          .create()
          .owner(null)
          .title("Error Deleting Event")
          .message(
              "Cannot delete event. Please try again. If problem persists,"
                  + " please contact tech support.").showError();
    } // end catch

    // fail-all close database resources
    finally {
      try {

        if (stmt != null)
          stmt.close();
        if (conn != null)
          conn.close();
        day.removeEvent(event);
      }
      catch (SQLException ex) {
        Dialogs.create().owner(null).title("Error Closing Datasource")
            .message("There was an error closing the datasource. Please exit the application.")
            .showError();
      } // end catch
    } // end finally
      // =============================================================
  }
}
