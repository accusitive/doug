package net.dougteam.doug.events;

import com.darkmagician6.eventapi.events.Event;

public class MouseEvent implements Event{
    public int button;
    public int action;
    public MouseEvent(int button, int action) {
        this.button = button;
        this.action = action;
    }
}
