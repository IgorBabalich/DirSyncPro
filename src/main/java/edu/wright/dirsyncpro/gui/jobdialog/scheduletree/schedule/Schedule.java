/*
 * Schedule.java
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
import edu.wright.dirsyncpro.job.Job;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Schedule implements Comparable<Schedule> {

    protected ScheduleType scheduleType;
    protected Date timeFrom = Const.NonDate;
    protected Date timeTo = Const.NonDate;
    protected Date nextEvent;
    protected Date lastSynced;
    protected Job job;
    private Date modificationTime;

    /**
     * @return the type
     */
    public ScheduleType getType() {
        return scheduleType;
    }

    /**
     * @return the timeFrameFrom
     */
    public Date getTimeFrom() {
        return timeFrom;
    }

    /**
     * @param timeFrom the timeFrameFrom to set
     */
    public void setTimeFrom(Date timeFrom) {
        this.timeFrom = timeFrom;
    }

    /**
     * @return the timeFrameTo
     */
    public Date getTimeTo() {
        return timeTo;
    }

    /**
     * @param timeTo the timeFrameTo to set
     */
    public void setTimeTo(Date timeTo) {
        this.timeTo = timeTo;
    }

    /**
     * @return the upcoming event in the extended classes.
     */
    public Date getNextEvent() {
        return nextEvent;
    }

    protected void setNextEvent(Date date) {
        if (date != null && withinTimeFrame(date)) {
            nextEvent = date;
            modificationTime = new Date();
        } else {
            nextEvent = null;
        }

    }

    /**
     * Calculates and sets the next upcoming event date. method to be overriden
     * by extended classes.
     */
    public void scheduleNextEvent() {
        throw  new UnsupportedOperationException("Schedule class is not to be used directly but by child classes.");
    }

    protected boolean withinTimeFrame(Date date) {
        return (!timeFrom_isAssigned() || timeFrom.compareTo(date) <= 0)
                && (!timeTo_isAssigned() || timeTo.compareTo(date) >= 0);
    }

    public boolean timeFrom_isAssigned() {
        return (timeFrom.compareTo(Const.NonDate) != 0);
    }

    public boolean timeTo_isAssigned() {
        return (timeTo.compareTo(Const.NonDate) != 0);
    }

    @Override
    public String toString() {
        String str = "";
        if (timeFrom_isAssigned()) {
            str += "From: " + (new SimpleDateFormat(Const.DefaultDateFormat)).format(timeFrom);
        }
        if (timeTo_isAssigned()) {
            str += " To: " + (new SimpleDateFormat(Const.DefaultDateFormat)).format(timeTo);
        }
        if (nextEvent != null) {
            str += " Next event: " + (new SimpleDateFormat(Const.DefaultDateFormat)).format(nextEvent);
        } else {
            str += " (EXPIRED)";
        }
        return str.trim();
    }

    /**
     * @return the modificationTime
     */
    public Date getModificationTime() {
        return modificationTime;
    }

    @Override
    public int compareTo(Schedule s) {
        if (nextEvent.compareTo(s.getNextEvent()) == 0) {
            if (modificationTime.compareTo(s.getModificationTime()) == 0) {
                return scheduleType.compareTo(s.getType());
            } else {
                return modificationTime.compareTo(s.getModificationTime());
            }
        } else {
            return nextEvent.compareTo(s.getNextEvent());
        }
    }

    /**
     * @return the job
     */
    public Job getJob() {
        return job;
    }

    public void setSynced() {
        lastSynced = nextEvent;
    }

    protected boolean nextEvent_isAllowedToSchedule() {
        
        return (nextEvent == null || (nextEvent == lastSynced));
    }

    public enum ScheduleType {
        Once("/icons/once.png"),
        Minutely("/icons/minutely.png"),
        Hourly("/icons/hourly.png"),
        Daily("/icons/daily.png"),
        Weekly("/icons/weekly.png"),
        Monthly("/icons/monthly.png");

        private final Icon icon;

        ScheduleType(String iconFile) {
            this.icon = new ImageIcon(Const.class.getResource(iconFile));
        }

        public Icon getIcon() {
            return icon;
        }
    }
}
