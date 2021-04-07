package com.jitterted.ebp.blackjack.adapter.in.web;

import com.jitterted.ebp.blackjack.domain.Card;
import com.jitterted.ebp.blackjack.domain.Deck;
import com.jitterted.ebp.blackjack.domain.Game;
import com.jitterted.ebp.blackjack.domain.StubDeck;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.List;

import static com.jitterted.ebp.blackjack.domain.Rank.*;
import static com.jitterted.ebp.blackjack.domain.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BlackjackControllerTest {

    @Test
    void testGameThenInitialCardsAreDealt() {
        Game game = new Game();
        BlackjackController blackjackController = new BlackjackController(game);

        blackjackController.startGame();

        assertThat(game.playerHand().cards()).hasSize(2);
    }

    @Test
    void gameViewPopulatesViewModel() {
        Deck deck = new StubDeck(List.of(new Card(DIAMONDS, TEN),
            new Card(HEARTS, TWO),
            new Card(DIAMONDS, KING),
            new Card(CLUBS, THREE)));

        Game game = new Game(deck);
        BlackjackController blackjackController = new BlackjackController(game);

        blackjackController.startGame();
        Model model = new ConcurrentModel();
        blackjackController.viewGame(model);
        GameView gameView = (GameView) model.getAttribute("gameView");

        assertThat(gameView.getDealerCards()).containsExactly("2♥", "3♣");
        assertThat(gameView.getPlayerCards()).containsExactly("10♦", "K♦");
    }
}
