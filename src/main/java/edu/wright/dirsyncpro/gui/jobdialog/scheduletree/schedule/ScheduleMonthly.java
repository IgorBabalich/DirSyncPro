/*
 * ScheduleMonthly.java
 *
 * Copyright (C) 2010-2011 O. Givi (info@dirsyncpro.org)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.wright.dirsyncpro.gui.jobdialog.scheduletree.schedule;

import edu.wright.dirsyncpro.Const;
import edu.wright.dirsyncpro.Const.Months;
import edu.wright.dirsyncpro.job.Job;
import edu.wright.dirsyncpro.tools.Dates;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class ScheduleMonthly extends Schedule {

    private int day;
    private Date time;

    private final HashMap<Const.Months, Boolean> months;

    public ScheduleMonthly(Job j) {
        scheduleType = ScheduleType.Monthly;
        job = j;
        months = new HashMap<>();
        months.put(Months.January, false);
        months.put(Months.February, false);
        months.put(Months.March, false);
        months.put(Months.April, false);
        months.put(Months.May, false);
        months.put(Months.June, false);
        months.put(Months.July, false);
        months.put(Months.August, false);
        months.put(Months.September, false);
        months.put(Months.October, false);
        months.put(Months.November, false);
        months.put(Months.December, false);
    }

    private boolean isMonth(Months month) {
        return months.get(month);
    }

    public boolean isJanuary() {
        return isMonth(Months.January);
    }

    public void setJanuary(boolean january) {
        months.put(Months.January, january);
        nextEvent = null;
        scheduleNextEvent();
    }

    public boolean isFebruary() {
        return isMonth(Months.February);
    }

    public void setFebruary(boolean february) {
        months.put(Months.February, february);
        nextEvent = null;
        scheduleNextEvent();
    }

    public boolean isMarch() {
        return isMonth(Months.March);
    }

    public void setMarch(boolean march) {
        months.put(Months.March, march);
        nextEvent = null;
        scheduleNextEvent();
    }

    public boolean isApril() {
        return isMonth(Months.April);
    }

    public void setApril(boolean april) {
        months.put(Months.April, april);
        nextEvent = null;
        scheduleNextEvent();
    }

    public boolean isMay() {
        return isMonth(Months.May);
    }

    public void setMay(boolean may) {
        months.put(Months.May, may);
        nextEvent = null;
        scheduleNextEvent();
    }

    public boolean isJune() {
        return isMonth(Months.June);
    }

    public void setJune(boolean june) {
        months.put(Months.June, june);
        nextEvent = null;
        scheduleNextEvent();
    }

    public boolean isJuly() {
        return isMonth(Months.July);
    }

    public void setJuly(boolean july) {
        months.put(Months.July, july);
        nextEvent = null;
        scheduleNextEvent();
    }

    public boolean isAugust() {
        return isMonth(Months.August);
    }

    public void setAugust(boolean august) {
        months.put(Months.August, august);
        nextEvent = null;
        scheduleNextEvent();
    }

    public boolean isSeptember() {
        return isMonth(Months.September);
    }

    public void setSeptember(boolean september) {
        months.put(Months.September, september);
        nextEvent = null;
        scheduleNextEvent();
    }

    public boolean isOctober() {
        return isMonth(Months.October);
    }

    public void setOctober(boolean october) {
        months.put(Months.October, october);
        nextEvent = null;
        scheduleNextEvent();
    }

    public boolean isNovember() {
        return isMonth(Months.November);
    }

    public void setNovember(boolean november) {
        months.put(Months.November, november);
        nextEvent = null;
        scheduleNextEvent();
    }

    public boolean isDecember() {
        return isMonth(Months.December);
    }

    public void setDecember(boolean december) {
        months.put(Months.December, december);
        nextEvent = null;
        scheduleNextEvent();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
        nextEvent = null;
        scheduleNextEvent();
    }

    public int getDay() {
        return day;
    }

    public void setDay(int dayNumber) {
        this.day = dayNumber;
        nextEvent = null;
        scheduleNextEvent();
    }

    /**
     * Calculates and sets the next upcoming event date.
     */
    @Override
    public void scheduleNextEvent() {
        if (!isJanuary() && !isFebruary() && !isMarch() && !isApril() && !isMay() && !isJune() && !isJuly() && !isAugust() && !isSeptember() && !isOctober() && !isNovember() && !isDecember()) {
            // do nothing
        } else if (time != null && day > 0 && nextEvent_isAllowedToSchedule()) {
            Date candidNextEvent = null;
            if (nextEvent == null) {
                candidNextEvent = Dates.getNextDateAtThisTimeAndNumber(time, day, new Date());
                if (timeFrom_isAssigned() && candidNextEvent.compareTo(timeFrom) < 0) {
                    candidNextEvent = Dates.getNextDateAtThisTimeAndNumber(time, day, timeFrom);
                }
            } else {
                candidNextEvent = nextEvent;
            }
            Calendar cal = Calendar.getInstance();
            cal.setTime(candidNextEvent);

            while (!matches(cal.getTime())) {
                cal.add(Calendar.MONTH, 1);

            }
            candidNextEvent = cal.getTime();
            setNextEvent(candidNextEvent);
        }
    }

    private boolean matches(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return (cal.get(Calendar.MONTH) == Calendar.JANUARY && isJanuary())
                || (cal.get(Calendar.MONTH) == Calendar.FEBRUARY && isFebruary())
                || (cal.get(Calendar.MONTH) == Calendar.MARCH && isMarch())
                || (cal.get(Calendar.MONTH) == Calendar.APRIL && isApril())
                || (cal.get(Calendar.MONTH) == Calendar.MAY && isMay())
                || (cal.get(Calendar.MONTH) == Calendar.JUNE && isJune())
                || (cal.get(Calendar.MONTH) == Calendar.JULY && isJuly())
                || (cal.get(Calendar.MONTH) == Calendar.AUGUST && isAugust())
                || (cal.get(Calendar.MONTH) == Calendar.SEPTEMBER && isSeptember())
                || (cal.get(Calendar.MONTH) == Calendar.OCTOBER && isOctober())
                || (cal.get(Calendar.MONTH) == Calendar.NOVEMBER && isNovember())
                || (cal.get(Calendar.MONTH) == Calendar.DECEMBER && isDecember());
    }

    /**
     * returns a string presenting this schedule
     */
    @Override
    public String toString() {
        String str = "";
        if (isJanuary()) {
            str += ", January";
        }
        if (isFebruary()) {
            str += ", February";
        }
        if (isMarch()) {
            str += ", March";
        }
        if (isApril()) {
            str += ", April";
        }
        if (isMay()) {
            str += ", May";
        }
        if (isJune()) {
            str += ", June";
        }
        if (isJuly()) {
            str += ", July";
        }
        if (isAugust()) {
            str += ", August";
        }
        if (isSeptember()) {
            str += ", September";
        }
        if (isOctober()) {
            str += ", October";
        }
        if (isNovember()) {
            str += ", November";
        }
        if (isDecember()) {
            str += ", December";
        }

        if (str.startsWith(",")) {
            str = " on " + str.substring(2);
        }

        str += " ";

        str = "Runs on day " + day + " at " + (new SimpleDateFormat(Const.DefaultTimeFormat)).format(time) + str;
        str = str.trim();
        str += super.toString();
        str = str.trim();
        return str;
    }
}
