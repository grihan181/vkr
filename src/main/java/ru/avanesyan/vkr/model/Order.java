package ru.avanesyan.vkr.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int amount;

    Product product;
    Provider provider;

    @Enumerated(EnumType.STRING)
    private Status status;
    private Date arrivalDate;
    private int lostInDays;
}
