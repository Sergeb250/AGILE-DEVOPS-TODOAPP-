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
            out.println("3) Complete");
            out.println("4) Delete");
            out.println("5) Status");
            out.println("0) Exit");
            out.print("> ");
            switch (in.nextLine().trim()) {
                case "1" -> add();
                case "2" -> show();
                case "3" -> complete();
                case "4" -> delete();
                case "5" -> status();
                case "0" -> running = false;
                default -> {
                    out.println("Unknown option, try again");
                    log("error unknown option");
                }
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
            log("add id=" + todo.getId());
        } catch (IllegalArgumentException ex) {
            out.println(ex.getMessage());
            log("error " + ex.getMessage());
        }
    }

    private void show() {
        List<Todo> items = list.all();
        log("list count=" + items.size());
        if (items.isEmpty()) {
            out.println("No todos.");
            return;
        }
        for (Todo todo : items) {
            out.println(todo);
        }
    }

    private void complete() {
        out.print("Id: ");
        if (!in.hasNextLine()) {
            return;
        }
        try {
            Todo todo = list.complete(Integer.parseInt(in.nextLine().trim()));
            out.println("Completed " + todo);
            log("complete id=" + todo.getId());
        } catch (NumberFormatException ex) {
            out.println("Id must be a number");
            log("error Id must be a number");
        } catch (IllegalArgumentException ex) {
            out.println(ex.getMessage());
            log("error " + ex.getMessage());
        }
    }

    private void delete() {
        out.print("Id: ");
        if (!in.hasNextLine()) {
            return;
        }
        try {
            int id = Integer.parseInt(in.nextLine().trim());
            list.delete(id);
            out.println("Deleted " + id);
            log("delete id=" + id);
        } catch (NumberFormatException ex) {
            out.println("Id must be a number");
            log("error Id must be a number");
        } catch (IllegalArgumentException ex) {
            out.println(ex.getMessage());
            log("error " + ex.getMessage());
        }
    }

    private void status() {
        String line = "status=UP todos=" + list.size();
        out.println(line);
        log(line);
    }

    private void log(String message) {
        out.println("LOG " + message);
    }
}
