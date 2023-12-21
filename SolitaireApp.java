package com.qatelran.org.lessonnine.hw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class SolitaireApp {

    public static void main(String[] args) {
        // Создаем и перемешиваем колоду карт
        List<Card> deck = createDeck();
        shuffleDeck(deck);

        // Создаем стопку карт и раскладываем колоду в нее
        Stack<Card> pile = new Stack<>();
        pile.addAll(deck);

        // Пасьянс: убираем карты, если в стопке две карты одной масти ложатся друг на друга
        solitaire(pile);

        // Выводим результат и карты в стопке
        System.out.println("\nResult:");
        printPile(pile);
    }

    private static List<Card> createDeck() {
        List<Card> deck = new ArrayList<>();
        for (Suits suit : Suits.values()) {
            for (Rank rank : Rank.values()) {
                if (rank.ordinal() < 6) {
                    deck.add(new Card(rank, suit));
                }
            }
        }
        return deck;
    }

    private static void shuffleDeck(List<Card> deck) {
        Collections.shuffle(deck);
    }

    private static void solitaire(Stack<Card> pile) {
        Stack<Card> tempPile = new Stack<>();
        while (!pile.isEmpty()) {
            Card currentCard = pile.pop();
            if (!tempPile.isEmpty() && tempPile.peek().getSuit() == currentCard.getSuit()) {
                tempPile.pop();
            } else {
                tempPile.push(currentCard);
            }
        }
        pile.addAll(tempPile);
    }

    private static void printPile(Stack<Card> pile) {
        for (Card card : pile) {
            System.out.println(card);
        }
    }
}
