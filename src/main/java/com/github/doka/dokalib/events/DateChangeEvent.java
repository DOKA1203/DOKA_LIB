package com.github.doka.dokalib.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DateChangeEvent extends Event {
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    public DateChangeEvent(){ }
    
    @Override
    public HandlerList getHandlers() {
        return HANDLERS_LIST;
    }
    
    public static HandlerList getHandlerList() {
        return HANDLERS_LIST;
    }
}