package com.example.csmSystem.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "TestDto Model Information"
)
public class TestDto {

    private UUID id;

    @Schema(
            description = "Test name"
    )
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    @NotEmpty(message = "Age should not be null or empty")
    private int age;  // only for testing sortByMultipleFields()

    @Schema(
            description = "Business date"
    )
    @NotEmpty(message = "Local Date Time should not be null or empty")
    private LocalDateTime businessDate;
}
