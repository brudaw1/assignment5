package org.example.Assignment;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class filterInvoiceStubbedTest {

    @Test
    void filterInvoiceStubbedTest() {
        Database db = mock(Database.class); // Stubbing the Database
        when(db.withSql(any())).thenReturn(Arrays.asList(new Invoice("Stubbed Invoice", 99)));
        FilterInvoice filter = new FilterInvoice(db);

        List<Invoice> results = filter.lowValueInvoices();
        assertEquals(1, results.size(), "Should retrieve exactly one invoice");
        assertEquals(99, results.get(0).getValue(), "The invoice value should be 99");
    }
}