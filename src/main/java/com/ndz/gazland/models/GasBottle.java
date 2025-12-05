package com.ndz.gazland.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gas_bottle")
public class GasBottle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "La marque de la bouteille de gaz est obligatoire")
    private String brand;

    @NotNull(message = "Veuillez indiquer la masse")
    private double mass;

    @NotNull(message = "Veuillez indiquer le prix")
    private int price;

    @NotNull(message = "Veuillez indique la quantite en stock")
    private int stock;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GasBottle gasBottle = (GasBottle) o;
        return id == gasBottle.id && Double.compare(gasBottle.mass, mass) == 0  && Objects.equals(brand, gasBottle.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, mass, price, stock);
    }
}
