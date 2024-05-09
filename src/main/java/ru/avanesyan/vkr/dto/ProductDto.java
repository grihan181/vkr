package ru.avanesyan.vkr.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.avanesyan.vkr.model.Provider;
import ru.avanesyan.vkr.modelBuyer.ProductBuyer;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ProductDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private String name;

    private Double speed; // об/час
    private int bufer; // час
    private int minValue;
    private int maxValue;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    //@OrderBy("priority ASC")
    private Set<Provider> providers;

    private ProductBuyer productBuyer;
    private int totalPages;
}
