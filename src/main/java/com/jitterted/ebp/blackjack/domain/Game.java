package com.jitterted.ebp.blackjack.domain;

import com.jitterted.ebp.blackjack.adapter.in.console.ConsoleGame;

import static org.fusesource.jansi.Ansi.ansi;

public class Game {

    private final Deck deck;

    private final Hand dealerHand = new Hand();
    private final Hand playerHand = new Hand();

    public static void main(String[] args) {
        ConsoleGame.displayWelcomeScreen();
        playGame();
        ConsoleGame.resetScreen();
    }

    private static void playGame() {
        Game game = new Game();
        game.initialDeal();
        game.play();
    }

    public Game() {
        deck = new Deck();
    }

    protected Game(Deck deck) {
        this.deck = deck;
    }

    public void initialDeal() {
        dealRoundOfCards();
        dealRoundOfCards();
    }

    public void play() {
        playerTurn();

        dealerTurn();

        displayFinalGameState();

        determineOutcome();
    }

    private void dealRoundOfCards() {
        // why: players first because this is the rule
        playerHand.drawFrom(deck);
        dealerHand.drawFrom(deck);
    }

    public String determineOutcomeString() {
        if (playerHand.isBusted()) {
            return "You Busted, so you lose.  💸";
        } else if (dealerHand.isBusted()) {
            return "Dealer went BUST, Player wins! Yay for you!! 💵";
        } else if (playerHand.beats(dealerHand)) {
            return "You beat the Dealer! 💵";
        } else if (playerHand.pushes(dealerHand)) {
            return "Push: Nobody wins, we'll call it even.";
        } else {
            return "You lost to the Dealer. 💸";
        }
    }

    //GOAL: Deleted
    private void determineOutcome() {
        if (playerHand.isBusted()) {
            System.out.println("You Busted, so you lose.  💸");
        } else if (dealerHand.isBusted()) {
            System.out.println("Dealer went BUST, Player wins! Yay for you!! " +
                "💵");
        } else if (playerHand.beats(dealerHand)) {
            System.out.println("You beat the Dealer! 💵");
        } else if (playerHand.pushes(dealerHand)) {
            System.out.println("Push: Nobody wins, we'll call it even.");
        } else {
            System.out.println("You lost to the Dealer. 💸");
        }
    }

    private void dealerTurn() {
        // Dealer makes its choice automatically based on a simple heuristic
        // (<=16 must hit, =>17 must stand)
        if (!playerHand.isBusted()) {
            while (dealerHand.dealerMustDrawCard()) {
                dealerHand.drawFrom(deck);
            }
        }
    }

    private void playerTurn() {
        // get Player's decision: hit until they stand, then they're done (or
        // they go bust)

        while (!playerHand.isBusted()) {
            displayGameState(this);
            String playerChoice = ConsoleGame.inputFromPlayer().toLowerCase();
            if (playerChoice.startsWith("s")) {
                break;
            }
            if (playerChoice.startsWith("h")) {
                playerHits();
                if (playerHand.isBusted()) {
                    return;
                }
            } else {
                System.out.println("You need to [H]it or [S]tand");
            }
        }
    }

    public void playerHits() {
        playerHand.drawFrom(deck);
    }

    private static void displayGameState(Game game) {
        System.out.print(ansi().eraseScreen().cursor(1, 1));
        System.out.println("Dealer has: ");
        System.out.println(game.dealerHand().displayFirstCard()); // first card is
        // Face Up

        // second card is the hole card, which is hidden
        game.displayBackOfCard();

        System.out.println();
        System.out.println("Player has: ");
        game.playerHand().display();
        System.out.println(" (" + game.playerHand().displayValue() + ")");
    }

    public Hand playerHand() {
        return playerHand;
    }

    public Hand dealerHand() {
        return dealerHand;
    }

    private void displayBackOfCard() {
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

    private void displayFinalGameState() {
        System.out.print(ansi().eraseScreen().cursor(1, 1));
        System.out.println("Dealer has: ");
        dealerHand.display();
        System.out.println(" (" + dealerHand.displayValue() + ")");

        System.out.println();
        System.out.println("Player has: ");
        playerHand.display();
        System.out.println(" (" + playerHand.displayValue() + ")");
    }

}
