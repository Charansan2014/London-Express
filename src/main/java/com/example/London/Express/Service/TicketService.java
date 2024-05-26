package com.example.London.Express.Service;

import com.example.London.Express.Entity.User;
import com.example.London.Express.Entity.Ticket;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {
    private List<Ticket> tickets = new ArrayList<>();

    public Ticket purchaseTicket(User user, String from, String to, String seat) {
        Ticket ticket = new Ticket(user, from, to, seat);
        System.out.println(ticket.getUser().getFirstName());
        System.out.println(ticket.getUser().getLastName());
        tickets.add(ticket);
        return ticket;
    }

    public Optional<Ticket> getTicketByEmail(String email) {
        return tickets.stream()
                .filter(ticket -> ticket.getUser().getEmail().equals(email))
                .findFirst();
    }

    public List<Ticket> getTicketsBySection(String section) {
        return tickets.stream()
                .filter(ticket -> ticket.getSeat().startsWith(section))
                .collect(Collectors.toList());
    }

    public boolean removeUser(String email) {
        return tickets.removeIf(ticket -> ticket.getUser().getEmail().equals(email));
    }

    public String modifyUserSeat(String email, String newSeat) {
        Optional<Ticket> optionalTicket = getTicketByEmail(email);
        if (optionalTicket.isPresent() && !isSeatAllocated(newSeat)) {
            optionalTicket.get().setSeat(newSeat);
            return "Allocated new seat";
        }
        if(!optionalTicket.isPresent()){
            return "receipt not generated";
        }
        if(isSeatAllocated(newSeat)){
            return "seat unavailable";
        }
        return "";
    }

    public boolean isSeatAllocated(String seat) {
        return tickets.stream().anyMatch(ticket -> ticket.getSeat().equals(seat));
    }
}