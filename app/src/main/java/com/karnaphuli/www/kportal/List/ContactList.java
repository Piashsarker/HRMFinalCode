package com.karnaphuli.www.kportal.List;

import com.karnaphuli.www.kportal.model.Contact;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by pt on 9/13/16.
 */
public class ContactList {
    @SerializedName("Contacts")
    @Expose
    private ArrayList<Contact> contacts = new ArrayList<Contact>();

    /**
     *
     * @return
     * The contacts
     */
    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    /**
     *
     * @param contacts
     * The Contacts
     */
    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }
}
