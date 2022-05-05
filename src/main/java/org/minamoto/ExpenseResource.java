package org.minamoto;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.debezium.outbox.quarkus.ExportedEvent;

@Path("/expenses")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExpenseResource {

    @Inject
    ExpenseService service;

    @PostConstruct
    @Transactional
    void initData() {
        ExpenseDTO expense1 = ExpenseDTO.of("test1", 100);
        ExpenseDTO expense2 = ExpenseDTO.of("test2", 200);
        ExpenseDTO expense3 = ExpenseDTO.of("test3", 300);
        this.create(expense1);
        this.create(expense2);
        this.create(expense3);
    }


    @GET
    public List<Expense> List() {
        return service.list();
    }

    @POST
    public Expense create(final ExpenseDTO expense) {
        Expense exp = Expense.of(expense.name, expense.amount);
        service.create(exp);
        return exp;
    }
    
}