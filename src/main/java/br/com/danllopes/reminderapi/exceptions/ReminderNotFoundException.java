package br.com.danllopes.reminderapi.exceptions;

public class ReminderNotFoundException extends RuntimeException {

    public ReminderNotFoundException(){
        super("Reminder not found.");
    }

    public ReminderNotFoundException(String message){
        super(message);
    }
}
