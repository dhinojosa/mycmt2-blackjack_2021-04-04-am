package com.jitterted.ebp.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameOutcomeTest {

    @Test
    void playerBustsAndLoses() {
        StubDeck stubDeck = new StubDeck(Rank.JACK, Rank.TWO, Rank.QUEEN,
            Rank.THREE, Rank.SIX);
        Game game = new Game(stubDeck);
        game.initialDeal();
        game.playerHits();

        assertThat(game.determineOutcome()).isEqualTo("You Busted, so you lose.  💸");
    }
}
