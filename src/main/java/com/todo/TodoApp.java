package com.todo;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class TodoApp {
    private final TodoList list;
    private final Scanner in;
    private final PrintStream out;

    public TodoApp(TodoList list, InputStream in, PrintStream out) {
        this.list = list;
        this.in = new Scanner(in);
        this.out = out;
    }

    public static void main(String[] args) {
        new TodoApp(new TodoList(), System.in, System.out).run();
    }

    public void run() {
        boolean running = true;
        while (running && in.hasNextLine()) {
            out.println("1) Add");
            out.println("2) List");
            out.println("0) Exit");
            out.print("> ");
            switch (in.nextLine().trim()) {
                case "1" -> add();
                case "2" -> show();
                case "0" -> running = false;
                default -> out.println("Unknown option");
            }
        }
    }

    private void add() {
        out.print("Title: ");
        if (!in.hasNextLine()) {
            return;
        }
        try {
            Todo todo = list.add(in.nextLine());
            out.println("Added " + todo);
        } catch (IllegalArgumentException ex) {
            out.println(ex.getMessage());
        }
    }

    private void show() {
        List<Todo> items = list.all();
        if (items.isEmpty()) {
            out.println("No todos.");
            return;
        }
        for (Todo todo : items) {
            out.println(todo);
        }
    }
}
