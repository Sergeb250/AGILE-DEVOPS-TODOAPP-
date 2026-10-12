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

    @Test
    void completeMarksTheTodoDone() {
        TodoList list = new TodoList();
        list.add("Buy milk");

        Todo done = list.complete(1);

        assertTrue(done.isCompleted());
        assertTrue(list.complete(1).isCompleted());
        assertEquals("1. [x] Buy milk", list.all().get(0).toString());
    }

    @Test
    void deleteRemovesTheTodo() {
        TodoList list = new TodoList();
        list.add("Buy milk");

        list.delete(1);

        assertTrue(list.all().isEmpty());
    }

    @Test
    void deleteRejectsAnUnknownId() {
        IllegalArgumentException missing = assertThrows(IllegalArgumentException.class, () -> new TodoList().delete(9));

        assertEquals("Todo not found", missing.getMessage());
    }

    @Test
    void completeRejectsAnUnknownId() {
        IllegalArgumentException missing = assertThrows(IllegalArgumentException.class, () -> new TodoList().complete(9));

        assertEquals("Todo not found", missing.getMessage());
    }
}
