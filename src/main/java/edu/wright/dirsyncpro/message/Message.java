/*
 * Message.java
 *
 * Represents a file pair to synchronize.
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
package edu.wright.dirsyncpro.message;

import edu.wright.dirsyncpro.Const.IconKey;
import edu.wright.dirsyncpro.Const.LogLevel;

import javax.swing.Icon;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a file pair to synchronize.
 *
 * @author O. Givi (info@dirsyncpro.org)
 */
public class Message {
    private final Date dateTime;
    private final String message;
    private Icon icon;
    private final IconKey iconKey;
    private final LogLevel loglevel;

    public Message(String m, IconKey ik, LogLevel l) {
        dateTime = new Date();
        message = m;
        iconKey = ik;
        if (iconKey != null) {
            icon = ik.getIcon();
        }
        loglevel = l;
    }

    /**
     * @return the date
     */
    public String getDateStr() {
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormatter.format(dateTime);
    }

    /**
     * @return the message
     */
    @Override
    public String toString() {
        return message;
    }

    /**
     * @return the icon
     */
    public Icon getIcon() {
        return icon;
    }

    /**
     * @return the iconkey
     */
    public IconKey getIconKey() {
        return iconKey;
    }

    /**
     * @return the loglevel
     */
    public LogLevel getLoglevel() {
        return loglevel;
    }
}
