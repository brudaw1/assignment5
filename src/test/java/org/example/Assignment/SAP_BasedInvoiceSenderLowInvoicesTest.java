// Brunel Dawood - Tested SAP

package org.example.Assignment;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Fail.fail;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.example.Assignment.Invoice;
import org.example.Assignment.SAP;
import org.example.Assignment.FilterInvoice;
import org.example.Assignment.SAP_BasedInvoiceSender;


public class SAP_BasedInvoiceSenderLowInvoicesTest {
    @Test
    public void testWhenLowInvoicesSent() {
        // Setup your mocks
        FilterInvoice filter = mock(FilterInvoice.class);
        SAP sap = mock(SAP.class);
        List<Invoice> invoices = Arrays.asList(new Invoice("Customer1", 50));
        when(filter.lowValueInvoices()).thenReturn(invoices);

        // Try-catch to handle the exception
        try {
            SAP_BasedInvoiceSender sender = new SAP_BasedInvoiceSender(filter, sap);
            sender.sendLowValuedInvoices();
            for (Invoice invoice : invoices) {
                verify(sap).send(invoice);
            }
        } catch (FailToSendSAPInvoiceException e) {
            fail("Did not expect FailToSendSAPInvoiceException to be thrown");
        }
    }
}