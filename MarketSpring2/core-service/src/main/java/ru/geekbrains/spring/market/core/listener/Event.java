package ru.geekbrains.spring.market.core.listener;

import ru.geekbrains.spring.market.core.entities.Order;

public class Event {
    private Order data;

    public Event(Order data) {
        this.data = data;
    }

    public Order getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Event{" +
                "data=" + data +
                '}';
    }
}
