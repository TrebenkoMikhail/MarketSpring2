package ru.geekbrains.spring.market.api;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {super(message);}
}
