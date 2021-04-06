package com.jitterted.ebp.blackjack.adapter.in.console;

import com.jitterted.ebp.blackjack.domain.Game;
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

    public static void displayBackOfCard() {
        System.out.print(
            ansi()
                .cursorUp(7)
                .cursorRight(12)
                .a("┌─────────┐").cursorDown(1).cursorLeft(11)
                .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
                .a("│░ J I T ░│").cursorDown(1).cursorLeft(11)
                .a("│░ T E R ░│").cursorDown(1).cursorLeft(11)
                .a("│░ T E D ░│").cursorDown(1).cursorLeft(11)
                .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
                .a("└─────────┘"));
    }

    public static void displayFinalGameState(Game game) {
        System.out.print(ansi().eraseScreen().cursor(1, 1));
        System.out.println("Dealer has: ");
        game.dealerHand().display();
        System.out.println(" (" + game.dealerHand().stringValue() + ")");

        System.out.println();
        System.out.println("Player has: ");
        game.playerHand().display();
        System.out.println(" (" + game.playerHand().stringValue() + ")");
    }

    public static void displayGameState(Game game) {
        System.out.print(ansi().eraseScreen().cursor(1, 1));
        System.out.println("Dealer has: ");
        System.out.println(ConsoleHand.displayFirstCard(game.dealerHand())); // first card is
        // Face Up

        // second card is the hole card, which is hidden
        displayBackOfCard();

        System.out.println();
        System.out.println("Player has: ");
        game.playerHand().display();
        System.out.println(" (" + game.playerHand().stringValue() + ")");
    }

    public static void playerTurn(Game game) {
        // get Player's decision: hit until they stand, then they're done (or
        // they go bust)

        while (!game.playerHand().isBusted()) {
            displayGameState(game);
            String playerChoice = inputFromPlayer().toLowerCase();
            if (playerChoice.startsWith("s")) {
                break;
            }
            if (playerChoice.startsWith("h")) {
                game.playerHits();
                if (game.playerHand().isBusted()) {
                    return;
                }
            } else {
                System.out.println("You need to [H]it or [S]tand");
            }
        }
    }

    public static void main(String[] args) {
        displayWelcomeScreen();
        Game.playGame();
        resetScreen();
    }
}
