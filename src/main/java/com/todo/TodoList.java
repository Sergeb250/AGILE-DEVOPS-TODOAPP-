package com.todo;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private final List<Todo> items = new ArrayList<>();
    private int nextId = 1;

    public Todo add(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        Todo todo = new Todo(nextId++, title.trim());
        items.add(todo);
        return todo;
    }

    public List<Todo> all() {
        return List.copyOf(items);
    }

    public Todo complete(int id) {
        Todo todo = find(id);
        todo.markCompleted();
        return todo;
    }

    public void delete(int id) {
        items.remove(find(id));
    }

    public int size() {
        return items.size();
    }

    private Todo find(int id) {
        for (Todo todo : items) {
            if (todo.getId() == id) {
                return todo;
            }
        }
        throw new IllegalArgumentException("Todo not found");
    }
}
