package org.minamoto;

import java.time.Instant;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.debezium.outbox.quarkus.ExportedEvent;

public class ExpenseCreatedEvent implements ExportedEvent<String, JsonNode> {

    private static ObjectMapper mapper = new ObjectMapper();

    private final Long id;
    private final JsonNode expense;
    private final Instant timestamp;

    private ExpenseCreatedEvent(Long id, JsonNode expense) {
        this.id = id;
        this.expense = expense;
        this.timestamp = Instant.now();
    }

    public static ExpenseCreatedEvent of(Expense exp) {
        ObjectNode asJson = mapper.createObjectNode()
                .put("id", exp.id)  // Panache id
                .put("name", exp.name)
                .put("amount", exp.amount.toString());
        return new ExpenseCreatedEvent(exp.id, asJson);
    }

    @Override
    public String getAggregateId() {
        return String.valueOf(id);
    }

    @Override
    public String getAggregateType() {
        return "Expense";
    }

    @Override
    public JsonNode getPayload() {
        return expense;
    }

    @Override
    public String getType() {
        return "ExpenseCreated";
    }

    @Override
    public Instant getTimestamp() {
        return timestamp;
    }
}