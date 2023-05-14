package com.example.csc306_project.ui.models;

import java.io.Serializable;


public class Artefact implements Serializable {
    private long id;
    private String title;
    private String description;
    private String history;
    private String imagePath;

    public Artefact(long id, String title, String description, String history, String imagePath) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.history = history;
        this.imagePath = imagePath;
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

    /**
     * Gets image of the artefact.
     */
    public String getImagePath() {
        return imagePath;
    }
    /**
     * Sets image of the artefact.
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

}