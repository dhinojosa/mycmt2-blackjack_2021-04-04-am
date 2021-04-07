package com.jitterted.ebp.blackjack.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//Stub
public class StubDeck extends Deck{
    private final Iterator<Card> iterator;

    public StubDeck(Rank... ranks) {
        List<Card> cards = new ArrayList<>();
        for (Rank rank : ranks) {
            cards.add(new Card(Suit.HEARTS, rank));
        }
        this.iterator = cards.iterator();
    }

    public StubDeck(List<Card> cards) {
        this.iterator = cards.iterator();
    }

    @Override
    public Card draw() {
        return iterator.next();
    }
}
