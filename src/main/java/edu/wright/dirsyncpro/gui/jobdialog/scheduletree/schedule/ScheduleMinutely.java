/*
 * ScheduleMinutely.java
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

import edu.wright.dirsyncpro.job.Job;
import edu.wright.dirsyncpro.tools.Dates;

import java.util.Calendar;
import java.util.Date;

public class ScheduleMinutely extends Schedule {

    private int interval = -1;

    public ScheduleMinutely(Job j) {
        scheduleType = ScheduleType.Minutely;
        job = j;
    }

    /**
     * @return the interval
     */
    public int getInterval() {
        return interval;
    }

    /**
     * @param value the interval to set
     */
    public void setInterval(int value) {
        this.interval = value;
        nextEvent = null;
        scheduleNextEvent();
    }

    /**
     * Calculates and sets the next upcoming event date.
     */
    @Override
    public void scheduleNextEvent() {
        if (interval > 0 && nextEvent_isAllowedToSchedule()) {
            Date candidNextEvent = null;
            if (nextEvent == null) {
                candidNextEvent = new Date();
                if (timeFrom_isAssigned() && candidNextEvent.compareTo(timeFrom) < 0) {
                    candidNextEvent = timeFrom;
                }
            } else {
                candidNextEvent = Dates.add(nextEvent, Calendar.MINUTE, interval);
            }
            setNextEvent(candidNextEvent);
        }
    }

    /**
     * returns a string presenting this schedule
     */
    @Override
    public String toString() {
        String str = "Runs every " + interval + " minute" + (interval > 1 ? "s" : "");
        str = str.trim();
        str += super.toString();
        str = str.trim();
        return str;
    }
}
