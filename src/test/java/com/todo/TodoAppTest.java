package com.todo;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TodoAppTest {

    @Test
    void userCanAddAndList() {
        String output = run("""
                1
                Buy milk
                2
                0
                """);

        assertTrue(output.contains("Added 1. [ ] Buy milk"));
        assertTrue(output.contains("1. [ ] Buy milk"));
    }

    @Test
    void blankTitleIsRejected() {
        String output = run("""
                1

                2
                0
                """);

        assertTrue(output.contains("Title is required"));
        assertTrue(output.contains("No todos."));
        assertFalse(output.contains("Added"));
    }

    private static String run(String input) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        TodoApp app = new TodoApp(
                new TodoList(),
                new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)),
                new PrintStream(buffer, true, StandardCharsets.UTF_8));
        app.run();
        return buffer.toString(StandardCharsets.UTF_8);
    }
}
