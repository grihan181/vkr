package ru.avanesyan.vkr.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.avanesyan.vkr.model.enums.DeliveryStatus;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int amount;
    private int bufer;

    @ManyToOne
    Product product;
    @ManyToOne
    Provider provider;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
    private LocalDate arrivalDate;
    private int lostInDays;
}
