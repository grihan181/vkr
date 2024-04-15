package ru.avanesyan.vkr.modelBuyer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(schema = "buyer")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ProductBuyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;
    private String name;
}
