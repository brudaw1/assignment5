
package org.example.Assignment;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class FilterInvoice {
    QueryInvoicesDAO dao;

    // Constructor refactored to accept Database dependency from outside
    public FilterInvoice(Database db) {
        this.dao = new QueryInvoicesDAO(db);
    }

    public List<Invoice> lowValueInvoices() {
        List<Invoice> all = dao.all();
        return all.stream()
                .filter(invoice -> invoice.getValue() < 100)
                .collect(toList());
    }
}
