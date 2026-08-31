package project.practice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.practice.dto.TicketDto;
import project.practice.service.TicketService;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<Page<TicketDto>> getAllTickets(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ticketService.getAllTickets(page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable UUID id) {
        return ticketService.getTicketById(id);
    }

    @PostMapping
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto) {
        return ticketService.createTicket(ticketDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable UUID id, @RequestBody TicketDto ticketDto) {
        return ticketService.updateTicket(id, ticketDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable UUID id) {
        return ticketService.deleteTicket(id);
    }
}
