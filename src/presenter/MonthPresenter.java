/**
 * 
 */
package presenter;

import java.util.GregorianCalendar;

import view.type.Month;
import view.type.Month.MonthListener;

/**
 * @author elijahr
 *
 */
public class MonthPresenter implements MonthListener {

  private Month month;
  
  public MonthPresenter(Month month) {
    this.month = month;
  }

  
  @Override
  public void getEvents(GregorianCalendar startDate, GregorianCalendar endDate) {
    // [MJ] query the DB to get all events between the two dates (inclusive)
  }

}
