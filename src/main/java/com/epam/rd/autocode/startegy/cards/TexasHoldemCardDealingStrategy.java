package com.epam.rd.autocode.startegy.cards;

import java.util.*;

public class TexasHoldemCardDealingStrategy implements CardDealingStrategy {
    @Override
    public Map<String, List<Card>> dealStacks(Deck deck, int players) {
        Map<String, List<Card>> stacks=new TreeMap<>();

        // Раздаем карты игрокам
        for(int i=1;i<=players;i++){
            stacks.put("Player "+i,new ArrayList<>());
        }
        for (int i=0;i<2;i++){
            for (int player=1;player<=players;player++){
                stacks.get("Player "+player).add(deck.dealCard());
            }
        }

        // Раздаем карты в стопку "Community"
        stacks.put("Community",new ArrayList<>());
        for(int i=0;i<5;i++){
            stacks.get("Community").add(deck.dealCard());
        }

        // Оставшиеся карты
        stacks.put("Remaining",deck.restCards());
        return stacks;
    }
}
