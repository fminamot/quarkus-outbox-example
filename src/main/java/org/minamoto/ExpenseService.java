package org.minamoto;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.POST;

import io.debezium.outbox.quarkus.ExportedEvent;

@ApplicationScoped
public class ExpenseService {

    @Inject
    Event<ExportedEvent<?, ?>> event;

    public List<Expense> list() {
        return Expense.listAll();
    }

    @POST
    @Transactional
    public Expense create(Expense expense) {
        expense.persist();
        event.fire(ExpenseCreatedEvent.of(expense));
        return expense;
    }
}
