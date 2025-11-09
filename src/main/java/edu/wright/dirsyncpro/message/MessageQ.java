package edu.wright.dirsyncpro.message;

import edu.wright.dirsyncpro.Const.IconKey;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a queue of Messages.
 *
 * @author O. Givi (info@dirsyncpro.org)
 */
public class MessageQ {

    private final ArrayList<Message> messages;
    private ArrayList<Message> filteredMessages;
    private HashMap<IconKey, Boolean> filteredBy;
    // TODO: replace by the function?
    private boolean viewIsFiltered;

    public MessageQ() {
        messages = new ArrayList<>();
        initViewFilter();
    }

    /**
     * initializes the viewFilterMode to defaults
     */
    public void initViewFilter() {
        filteredBy = new HashMap<>();
        filteredBy.put(IconKey.Info, true);
        filteredBy.put(IconKey.Warning, true);
        filteredBy.put(IconKey.Error, true);
        filteredBy.put(IconKey.File, true);
        viewFilter();
    }

    public boolean add(Message m) {
        return messages.add(m);
    }

    public int viewSize() {
        if (viewIsFiltered) {
            return filteredMessages.size();
        } else {
            return messages.size();
        }
    }

    public Message get(int i) {
        if (viewIsFiltered) {
            return filteredMessages.get(i);
        } else {
            return messages.get(i);
        }
    }

    // TODO: looks like the flip/flop works incorrectly. review when working with UI
    public void viewFilter() {
        if (isFilteredBy(IconKey.Info)
                && isFilteredBy(IconKey.Warning)
                && isFilteredBy(IconKey.Error)
                && isFilteredBy(IconKey.File)) {
            viewIsFiltered = false;
        } else {
            viewIsFiltered = true;
            filteredMessages = new ArrayList<>();
            for (Message m : messages) {
                if (filteredBy.get(m.getIconKey().mapForMessageQ())) {
                    filteredMessages.add(m);
                }
            }
        }
    }

    public boolean isFilteredBy(IconKey ik) {
        return filteredBy.get(ik);
    }

    /**
     * @param ik the IconKey to set the value for
     * @param b boolean value to set
     */
    public void setFilteredBy(IconKey ik, boolean b) {
        filteredBy.put(ik, b);
    }

}
