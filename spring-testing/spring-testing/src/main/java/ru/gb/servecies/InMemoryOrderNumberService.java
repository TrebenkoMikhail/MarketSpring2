package ru.gb.servecies;

import org.springframework.stereotype.Service;

@Service
public class InMemoryOrderNumberService implements OrderNumberService {

    private Long sequence;
    public InMemoryOrderNumberService() {
        sequence = 1L;
    }
    @Override
    public String next() {
        return "#" + sequence++;
    }
}
