/**
 * 
 */
package model.bean;

import java.time.LocalDateTime;

/**
 * Corresponding class to the Event table in the database. TODO add more
 * information
 * 
 * @author elijahr
 *
 */
public class EventBean {

  private String name;
  private LocalDateTime startDateTime;
  private LocalDateTime endDateTime;
  private Boolean allDayIndicator;
  // private Type eventType;
  private UserBean owner;
  private String description;


  public EventBean() {
  }


  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public LocalDateTime getStartDateTime() {
    return startDateTime;
  }


  public void setStartDateTime(LocalDateTime startDateTime) {
    this.startDateTime = startDateTime;
  }


  public LocalDateTime getEndDateTime() {
    return endDateTime;
  }


  public void setEndDateTime(LocalDateTime endDateTime) {
    this.endDateTime = endDateTime;
  }


  public Boolean getAllDayIndicator() {
    return allDayIndicator;
  }


  public void setAllDayIndicator(Boolean allDayIndicator) {
    this.allDayIndicator = allDayIndicator;
  }


  public UserBean getOwner() {
    return owner;
  }


  public void setOwner(UserBean owner) {
    this.owner = owner;
  }


  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }

}
