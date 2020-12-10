package ru.secondmemory.repository;

import ru.secondmemory.model.Card;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryCardRepository implements CardRepository {
    private final Map<Integer, Card> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

//    {
//        CardsUtil.cards.forEach(this::save);
//    }

    @Override
    public Card save(Card card) {
//        if (card.isNew()) {
//            card.setId(counter.incrementAndGet());
//            repository.put(card.getId(), card);
        return card;
//        }
//        // handle case: update, but not present in storage
//        return repository.computeIfPresent(card.getId(), (id, oldCard) -> card);
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public Card get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<Card> getAll() {
        return repository.values();
    }
}

