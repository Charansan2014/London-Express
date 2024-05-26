package com.example.London.Express.Controller;



import com.example.London.Express.Entity.Ticket;
import com.example.London.Express.Entity.User;
import com.example.London.Express.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/api")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/purchase")
    public Ticket purchaseTicket(@RequestBody User user) {
        return ticketService.purchaseTicket(user, "London", "France", allocateSeat());
    }

    @GetMapping("/receipt")
    public Optional<Ticket> getReceipt(@RequestParam String email) {
        return ticketService.getTicketByEmail(email);
    }

    @GetMapping("/section/{section}")
    public List<Ticket> getUsersBySection(@PathVariable String section) {
        return ticketService.getTicketsBySection(section);
    }

    @DeleteMapping("/remove")
    public boolean removeUser(@RequestParam String email) {
        return ticketService.removeUser(email);
    }

    @PutMapping("/modifySeat")
    public String modifyUserSeat(@RequestParam String email, @RequestParam String newSeat) {
        return ticketService.modifyUserSeat(email, newSeat);
    }

    private String allocateSeat() {
        int seatNumber = ticketService.getTicketsBySection("A").size() + 1;
        if (seatNumber <= 50) {
            return "A" + seatNumber;
        } else {
            seatNumber = ticketService.getTicketsBySection("B").size() + 1;
            return "B" + seatNumber;
        }
    }
}