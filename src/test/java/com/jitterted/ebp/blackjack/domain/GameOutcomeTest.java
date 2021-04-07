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

        assertThat(game.determineOutcome()).isEqualTo(GameOutcome.PLAYER_LOSES);
    }

    @Test
    void playerGetsBlackjack() {
        StubDeck stubDeck = new StubDeck(Rank.ACE, Rank.EIGHT, Rank.JACK,
            Rank.TEN);
        Game game = new Game(stubDeck);
        game.initialDeal();
        assertThat(game.determineOutcome()).isEqualTo(GameOutcome.PLAYER_WINS_BLACKJACK);
    }


}
