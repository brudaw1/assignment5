// Brunel Dawood - Tested SAP

package org.example.Assignment;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.example.Assignment.Invoice;
import org.example.Assignment.SAP;
import org.example.Assignment.FilterInvoice;
import org.example.Assignment.SAP_BasedInvoiceSender;


public class SAP_BasedInvoiceSenderNoInvoicesTest {
    @Test
    public void testWhenNoInvoices() throws FailToSendSAPInvoiceException {
        // Mock the dependencies
        FilterInvoice filter = mock(FilterInvoice.class);
        SAP sap = mock(SAP.class);

        // Setup to return an empty list
        when(filter.lowValueInvoices()).thenReturn(Collections.emptyList());

        // Initialize the sender with mocks
        SAP_BasedInvoiceSender sender = new SAP_BasedInvoiceSender(filter, sap);

        // Execute the method under test
        sender.sendLowValuedInvoices();

        // Verify that sap.send() was never called
        verify(sap, never()).send(any(Invoice.class));
    }
}
