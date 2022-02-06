package com.ggic.app;

import com.ggic.app.controller.StudentTerminalBasedController;
import com.ggic.app.enums.Action;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentTerminalBasedController studentTerminalBasedController = new StudentTerminalBasedController();
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter action you want to do with student");
                studentTerminalBasedController.process(Action.valueOf(scanner.next()));
            } catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());
            }
        }
    }
}
