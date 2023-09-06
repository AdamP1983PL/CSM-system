package com.example.csmSystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "TestDto Model Information"
)
public class TestDto {

    private long id;

    @Schema(
            description = "Test name"
    )
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    @Schema(
            description = "Business date"
    )
    @NotEmpty(message = "Local Date Time should not be null or empty")
    private LocalDateTime businessDate;
}
