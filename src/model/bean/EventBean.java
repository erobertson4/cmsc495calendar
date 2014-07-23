/**
 * 
 */
package model.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * Corresponding class to the Event table in the database. 
 * information
 * 
 * @author elijahr
 * @author mattj
 *
 */
public class EventBean {

  private int eventID;
  private String eventTitle;
  private int eventTypeID;
  private int eventCreatorUserID;
  private LocalDate eventStartDate; // need to change 
  private LocalDateTime eventStartTime;
  private LocalDateTime eventEndTime;
  private Boolean eventAllDay;
  private String eventMessage;
  private String eventLocation;
  private String eventAddress;
  private String eventCity;
  private String eventState;
  private String eventZip;
  private Date createdDate;
  private Date lastUpdateDate;
  private UserBean eventUserBean;



  public EventBean() {
  }

  // getters
  public int getEventID() {
    return eventID;
  }
  public String getEventTitle() {
      return eventTitle;
  }
  public int getEventTypeID() {
      return eventTypeID;
  }
  public int getEventCreatorUserID() {
      return eventCreatorUserID;
  }
  public LocalDate getEventStartDate() {
      return eventStartDate;
  }
  public LocalDateTime getEventStartTime() {
      return eventStartTime;
  }
  public LocalDateTime getEventEndTime() {
      return eventEndTime;
  }
  public Boolean getEventAllDay() {
      return eventAllDay;
  }
  public String getEventMessage() {
      return eventMessage;
  }
  public String getEventLocation() {
      return eventLocation;
  }
  public String getEventAddress() {
      return eventAddress;
  }
  public String getEventCity() {
      return eventCity;
  }
  public String getEventState() {
      return eventState;
  }
  public String getEventZip() {
      return eventZip;
  }
  public Date getCreatedDate() {
      return createdDate;
  }
  public Date getLastUpdateDate() {
      return lastUpdateDate;
  }
  public UserBean getEventUserBean() {
      return eventUserBean;
  }
  
  
  // setters 
  public void setEventID(int eEventID) {
      this.eventID = eEventID;
  }
  public void setEventTitle(String eTitle) {
      this.eventTitle = eTitle;
  }
  public void setEventTypeID(int eTypeID) {
      this.eventTypeID = eTypeID;
  }
  public void setEventCreatorUserID(int eCreatorUserID) {
      this.eventCreatorUserID = eCreatorUserID;
  }
  public void setEventStartDate(LocalDate eStartDate) {
      this.eventStartDate = eStartDate;
  }
  public void setEventStartTime(LocalDateTime eStartTime) {
      this.eventStartTime = eStartTime;
  }
  public void setEventEndTime(LocalDateTime eEndTime) {
      this.eventEndTime = eEndTime;
  }
  public void setEventAllDay(Boolean eAllDay) {
      this.eventAllDay = eAllDay;
  }
  public void setEventMessage(String eMessage) {
      this.eventMessage = eMessage;
  }
  public void setEventLocation(String eLocation) {
      this.eventLocation = eLocation;
  }
  public void setEventAddress(String eAddress) {
      this.eventLocation = eAddress;
  }
  public void setEventCity(String eCity) {
      this.eventCity = eCity;
  }
  public void setEventState(String eState) {
      this.eventState = eState;
  }
  public void setEventZip(String eZip) {
      this.eventZip = eZip;
  }
  public void setCreatedDate(Date eCreatedDate) {
      this.createdDate = eCreatedDate;
  }
  public void setLastUpdateDate(Date eLastUpdateDate) {
      this.lastUpdateDate = eLastUpdateDate;
  }
  public void setEventUserBean(UserBean eUserBean) {
      this.eventUserBean = eUserBean;
  }

} // end method: eventBean
