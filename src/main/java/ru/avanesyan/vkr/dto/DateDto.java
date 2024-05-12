package ru.avanesyan.vkr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.avanesyan.vkr.model.Product;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateDto {
    private LocalDate startDate;
    private LocalDate endDate;

    private Product product;
}
