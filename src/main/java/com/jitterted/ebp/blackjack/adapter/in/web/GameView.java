package com.jitterted.ebp.blackjack.adapter.in.web;

import java.util.List;

public class GameView {
    private List<String> dealerCards;
    private List<String> playerCards;


    public List<String> getDealerCards() {
        return dealerCards;
    }

    public void setDealerCards(List<String> dealerCards) {
        this.dealerCards = dealerCards;
    }

    public List<String> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(List<String> playerCards) {
        this.playerCards = playerCards;
    }
}
