package project.practice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name ="ticket")
public class Ticket{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String eventname;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
