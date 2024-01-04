package com.example.csmSystem.model.entity;

import com.example.csmSystem.model.auditing.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestClass extends Auditable<String> {
    @Id
    @Builder.Default
    private UUID uuid = UUID.randomUUID();
    @Column
    private String name;
    @Column
    private LocalDateTime businessDate;


    public TestClass(String name, LocalDateTime businessDate) {
        super();
        this.name = name;
        this.businessDate = businessDate;
    }
}
