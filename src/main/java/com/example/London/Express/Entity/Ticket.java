package com.example.London.Express.Entity;



public class Ticket {
    private static final double PRICE = 20.0;
    private static int idCounter = 0;

    private int id;
    private User user;
    private String from;
    private String to;
    private double price;
    private String seat;

    // Constructors, getters, and setters
    public Ticket(User user, String from, String to, String seat) {
        this.id = ++idCounter;
        this.user = user;
        System.out.println("==========\n"+this.user.getLastName());
        System.out.println(user.getFirstName());
        this.from = from;
        this.to = to;
        this.price = PRICE;
        this.seat = seat;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getPrice() {
        return price;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}