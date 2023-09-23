package com.example.csmSystem.model.entity;

import com.example.csmSystem.model.auditing.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class Test extends Auditable<String> {
    @Id
    @Builder.Default
    private UUID uuid = UUID.randomUUID();
    @Column
    private String name;
    @Column
    private LocalDateTime businessDate;
}
