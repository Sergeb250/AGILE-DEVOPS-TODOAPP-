package com.todo;

public class Todo {
    private final int id;
    private final String title;
    private boolean completed;

    public Todo(int id, String title) {
        this.id = id;
        this.title = title;
        this.completed = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        completed = true;
    }

    @Override
    public String toString() {
        return id + ". [" + (completed ? "x" : " ") + "] " + title;
    }
}
