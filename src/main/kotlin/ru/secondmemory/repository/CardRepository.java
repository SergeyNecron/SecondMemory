package ru.secondmemory.repository;

import ru.secondmemory.model.Card;

import java.util.Collection;

public interface CardRepository {
    // null if not found, when updated
    Card save(Card curd);

    // false if not found
    boolean delete(int id);

    // null if not found
    Card get(int id);

    Collection<Card> getAll();
}
