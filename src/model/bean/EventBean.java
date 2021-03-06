/**
 * 
 */
package model.bean;

import java.time.LocalDate;



/**
 * Corresponding class to the Event table in the database. 
 * information
 * 
 * @author elijahr
 * @author mattj
 *
 */
public class EventBean {

  private int id;
  private String title;
  private int creatorId;
  private LocalDate date; 
  private int startHour;
  private int startMinute;
  private Boolean startAMIndicator;
  private int endHour;
  private int endMinute;
  private Boolean endAMIndicator;
  private Boolean allDayIndicator;
  private String description;


  public EventBean() {
  }


  public int getId() {
    return id;
  }


  public void setId(int id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }


  public void setTitle(String title) {
    this.title = title;
  }


  public int getCreatorId() {
    return creatorId;
  }


  public void setCreatorId(int creatorId) {
    this.creatorId = creatorId;
  }


  public LocalDate getDate() {
    return date;
  }


  public void setDate(LocalDate date) {
    this.date = date;
  }


  public int getStartHour() {
    return startHour;
  }


  public void setStartHour(int startHour) {
    this.startHour = startHour;
  }


  public int getStartMinute() {
    return startMinute;
  }


  public void setStartMinute(int startMinute) {
    this.startMinute = startMinute;
  }


  public Boolean getStartAMIndicator() {
    return startAMIndicator;
  }


  public void setStartAMIndicator(Boolean startAMIndicator) {
    this.startAMIndicator = startAMIndicator;
  }


  public int getEndHour() {
    return endHour;
  }


  public void setEndHour(int endHour) {
    this.endHour = endHour;
  }


  public int getEndMinute() {
    return endMinute;
  }


  public void setEndMinute(int endMinute) {
    this.endMinute = endMinute;
  }


  public Boolean getEndAMIndicator() {
    return endAMIndicator;
  }


  public void setEndAMIndicator(Boolean endAMIndicator) {
    this.endAMIndicator = endAMIndicator;
  }


  public Boolean getAllDayIndicator() {
    return allDayIndicator;
  }


  public void setAllDayIndicator(Boolean allDayIndicator) {
    this.allDayIndicator = allDayIndicator;
  }


  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }

 


} // end method: eventBean
