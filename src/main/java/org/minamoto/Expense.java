package org.minamoto;

import javax.json.bind.annotation.JsonbCreator;
import javax.persistence.Entity;
import javax.persistence.Id;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Expense extends PanacheEntity {
    public String name;
    public Integer amount;

    public Expense() {};

    public Expense(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }

    @JsonbCreator
    public static Expense of(String name, Integer amount) {
        return new Expense(name, amount);
    }
}
