package com.example.csc306_project.ui.models;

import java.io.Serializable;


public class Artefact implements Serializable {
    private long id;
    private String title;
    private String description;
    private String history;


    public Artefact(long id, String title, String description, String history) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.history = history;
    }
    /**
     * Gets the id of the artefact.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id of the artefact.
     */
    public void setId(long id) {
        this.id = id;
    }
    /**
     * Gets the title of the artefact.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the artefact.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the artefact.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the artefact.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the history of the artefact.
     */
    public String getHistory() {
        return history;
    }

    /**
     * Sets the history of the artefact.
     */
    public void setHistory(String history) {
        this.history = history;
    }
}