// Brunel Dawood - Integration Testing

package org.example.Assignment;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterInvoiceTest {

    @Test
    void filterInvoiceTest() {
        Database db = new Database(); // Real database used here
        FilterInvoice filter = new FilterInvoice(db);
        db.resetDatabase(); // Resetting to ensure a clean state
        db.withSql(() -> {
            db.getConnection().prepareStatement("INSERT INTO invoice (name, value) VALUES ('Test Invoice', 50)").executeUpdate();
            return null;
        });

        List<Invoice> results = filter.lowValueInvoices();
        assertTrue(results.stream().allMatch(invoice -> invoice.getValue() < 100), "All invoices should be of low value");
    }
}