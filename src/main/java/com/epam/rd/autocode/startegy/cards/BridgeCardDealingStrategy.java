package com.epam.rd.autocode.startegy.cards;

import java.util.*;

public class BridgeCardDealingStrategy implements CardDealingStrategy {
    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {
        Map<String, List<Card>> stacks = new TreeMap<>();

        for (int i = 1; i <= players; i++) {
            stacks.put("Player "+i, new ArrayList<>());
        }

        for (int i=0;i<13;i++){
            for (int player=1;player<=players;player++){
                stacks.get("Player "+player).add(deck.dealCard());
            }
        }

        if(deck.restCards().isEmpty()){
            return stacks;
        }

        stacks.put("Remainig",deck.restCards());
        return stacks;
    }
}
