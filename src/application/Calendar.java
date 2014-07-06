/*
 Calendar.java
 The Calendar class will build the calendar initially and after the user changes the month / year.
 
 Sources:
 http://javahungry.blogspot.com/2013/06/calendar-implementation-gui-based.html
 */

package application;

import java.awt.FlowLayout;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class Calendar {

    //Declare variables (initialize some)
    JFrame frmCalendar;
    JComboBox cmbMonth, cmbYear;
    JButton btnForward, btnBack;
    DefaultTableModel calendarModel;
    JTable tblCalendar = new JTable(calendarModel);
    JScrollPane	pane = new JScrollPane(tblCalendar);
    int currentDay, currentMonth, currentYear, selectedMonth, selectedYear, nbrOfDays, firstDay;
    String daysOfTheWeek[] = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    public Calendar() {

        super();

        //Get current day, month, year
        GregorianCalendar cal = new GregorianCalendar();
        currentDay = cal.get(GregorianCalendar.DAY_OF_MONTH);
        currentMonth = cal.get(GregorianCalendar.MONTH);
        currentYear = cal.get(GregorianCalendar.YEAR);
        selectedMonth = currentMonth;
        selectedYear = currentYear;

        //Create frame to house calendar
        frmCalendar = new JFrame("Group 1's Awesome Calendar");
        frmCalendar.setSize(800, 500);
        frmCalendar.setResizable(true);
        frmCalendar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create buttons to move forward and back one month
        btnForward = new JButton("=>");
        btnBack = new JButton("<=");
        
        //Create and set comboboxes
        cmbMonth = new JComboBox();
        for (String month : months) {
            cmbMonth.addItem(month);
        }
        cmbMonth.setSelectedItem(months[cal.get(GregorianCalendar.MONTH)]);
        cmbYear = new JComboBox();
        for (int i = (currentYear - 5); i <= (currentYear + 5); i++) {
            cmbYear.addItem(i);
        }
        cmbYear.setSelectedItem(currentYear);

        //Set layout, display contents, set actions
        frmCalendar.setLayout(new FlowLayout());
        frmCalendar.add(btnBack);
        frmCalendar.add(cmbMonth);
        frmCalendar.add(pane);
        frmCalendar.add(cmbYear);
        frmCalendar.add(btnForward);

        //Make frame visible once everything is initially built
        frmCalendar.setVisible(true);

        //Setup calendar (table)
        calendarModel.setColumnCount(7);
        calendarModel.setRowCount(6);
        pane.add(tblCalendar);
        
        //Column headers (getting NPE error here)
        for (int i = 0; i < 7; i++) {
            calendarModel.addColumn(daysOfTheWeek[i]);
        }

        //Build initial calendar
        buildCalendar(selectedMonth, selectedYear);

    }

    
    
    //Builds calendar in pane (not working because of NPE error)
    void buildCalendar(int month, int year) {

        //Clear current calendar from scroll pane
        pane.removeAll();

        //Get the number of days in the month and the first day
        GregorianCalendar cal = new GregorianCalendar(selectedYear, selectedMonth, 1);
        //This should get leap years (haven't tested yet)
        nbrOfDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        firstDay = cal.get(GregorianCalendar.DAY_OF_WEEK);

        //Build calendar
        for (int i = 1; i < nbrOfDays; i++) {
            int row = new Integer((i + firstDay - 2) / 7);
            int column = (i + firstDay - 2) % 7;
            calendarModel.setValueAt(1, row, column);
        }
    }

}
