package com.example.csc306_project.ui.models;

import java.io.Serializable;

/**
 * A class that represents an Artefact.
 */
public class Artefact implements Serializable {
    private String title;
    private String description;
    private String history;

    /**
     * Constructor for an Artefact.
     *
     * @param title       the title of the artefact
     * @param description the description of the artefact
     * @param history     the history of the artefact
     */
    public Artefact(String title, String description, String history) {
        this.title = title;
        this.description = description;
        this.history = history;
    }

    /**
     * Gets the title of the artefact.
     *
     * @return the title of the artefact
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the artefact.
     *
     * @param title the new title of the artefact
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the artefact.
     *
     * @return the description of the artefact
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the artefact.
     *
     * @param description the new description of the artefact
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the history of the artefact.
     *
     * @return the history of the artefact
     */
    public String getHistory() {
        return history;
    }

    /**
     * Sets the history of the artefact.
     *
     * @param history the new history of the artefact
     */
    public void setHistory(String history) {
        this.history = history;
    }
}