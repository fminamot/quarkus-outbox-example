package org.minamoto;

import javax.json.bind.annotation.JsonbCreator;

public class ExpenseDTO {
    public String name;
    public Integer amount;

    public ExpenseDTO() {};

    public ExpenseDTO(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }

    @JsonbCreator
    public static ExpenseDTO of(String name, Integer amount) {
        return new ExpenseDTO(name, amount);
    }
}