package com.jitterted.ebp.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fusesource.jansi.Ansi.ansi;

class CardTest {

  private static final Suit DUMMY_SUIT = Suit.HEARTS;
  private static final Rank DUMMY_RANK = Rank.TEN;

  @Test
  public void withNumberCardHasNumericValueOfTheNumber() throws Exception {
    Card card = new Card(DUMMY_SUIT, Rank.SEVEN);

    assertThat(card.rankValue())
        .isEqualTo(7);
  }

  @Test
  public void withValueOfQueenHasNumericValueOf10() throws Exception {
    Card card = new Card(DUMMY_SUIT, Rank.QUEEN);

    assertThat(card.rankValue())
        .isEqualTo(10);
  }

  @Test
  public void withAceHasNumericValueOf1() throws Exception {
    Card card = new Card(DUMMY_SUIT, Rank.ACE);

    assertThat(card.rankValue())
        .isEqualTo(1);
  }



}