package com.narxoz.rpg.memento;

import com.narxoz.rpg.combatant.HeroMemento;
import java.util.ArrayDeque;
import java.util.Deque;

public class Caretaker {
    private final Deque<HeroMemento> history = new ArrayDeque<>();

    public void save(HeroMemento memento) {
        if (memento != null) history.push(memento);
    }

    public HeroMemento undo() {
        return history.isEmpty() ? null : history.pop();
    }

    public HeroMemento peek() {
        return history.peek();
    }

    public int size() {
        return history.size();
    }
}