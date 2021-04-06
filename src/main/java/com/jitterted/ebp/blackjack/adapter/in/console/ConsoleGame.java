package com.jitterted.ebp.blackjack.adapter.in.console;

import org.fusesource.jansi.Ansi;

import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

/**
 * @author Daniel Hinojosa
 * @since 4/5/21 9:55 PM
 * url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 * email:
 * <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 * tel: 505.363.5832
 */
public class ConsoleGame {
    public static void resetScreen() {
        System.out.println(ansi().reset());
    }

    public static void displayWelcomeScreen() {
        System.out.println(ansi()
            .bgBright(Ansi.Color.WHITE)
            .eraseScreen()
            .cursor(1, 1)
            .fgGreen().a("Welcome to")
            .fgRed().a(" Jitterted's")
            .fgBlack().a(" BlackJack"));
    }

    public static String inputFromPlayer() {
        System.out.println("[H]it or [S]tand?");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
