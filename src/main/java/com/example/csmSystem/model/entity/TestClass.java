package com.example.csmSystem.model.entity;

import com.example.csmSystem.model.auditing.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "test_class")
public class TestClass extends Auditable<String> {
    @Id
    @Builder.Default
    @Column(name = "uuid")
    private UUID uuid = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    @Column(name = "business_date")
    private LocalDateTime businessDate;


    public TestClass(String name, LocalDateTime businessDate) {
        super();
        this.name = name;
        this.businessDate = businessDate;
    }
}
