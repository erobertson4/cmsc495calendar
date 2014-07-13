/**
 * 
 */
package model.bean;

import java.util.Date;

/**
 * @author elijahr
 *
 */
public class EventBean {
  
  private String name;
  private Date startDateTime;
  private Date endDateTime;
  private Boolean allDayIndicator;
//  private Type eventType;
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

  public Date getStartDateTime() {
    return startDateTime;
  }

  public void setStartDateTime(Date startDateTime) {
    this.startDateTime = startDateTime;
  }

  public Date getEndDateTime() {
    return endDateTime;
  }

  public void setEndDateTime(Date endDateTime) {
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
