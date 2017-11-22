package boebot.listeners;

import boebot.Command;

/**
 * Write a description of class RemoteListener here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface RemoteListener
{
    public Command onCommandUpdate(int lengths[]);
}
