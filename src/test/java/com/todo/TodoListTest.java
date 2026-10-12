package com.todo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TodoListTest {

    @Test
    void addStoresAnOpenTodo() {
        TodoList list = new TodoList();

        Todo todo = list.add("Buy milk");

        assertEquals(1, todo.getId());
        assertEquals("Buy milk", todo.getTitle());
        assertFalse(todo.isCompleted());
        assertEquals("1. [ ] Buy milk", todo.toString());
    }

    @Test
    void addTrimsTheTitle() {
        Todo todo = new TodoList().add("  Walk the dog  ");

        assertEquals("Walk the dog", todo.getTitle());
    }

    @Test
    void addRejectsABlankTitle() {
        TodoList list = new TodoList();

        IllegalArgumentException blank = assertThrows(IllegalArgumentException.class, () -> list.add("   "));
        assertThrows(IllegalArgumentException.class, () -> list.add(null));

        assertEquals("Title is required", blank.getMessage());
        assertTrue(list.all().isEmpty());
    }

    @Test
    void listKeepsCreationOrder() {
        TodoList list = new TodoList();
        list.add("One");
        list.add("Two");

        List<String> titles = list.all().stream().map(Todo::getTitle).toList();

        assertEquals(List.of("One", "Two"), titles);
    }
}
