
package org.example.Assignment;

import java.util.ArrayList;
import java.util.List;

public class SAP_BasedInvoiceSender {

    private final FilterInvoice filter;
    private final SAP sap;
    private List<Invoice> failedValueInvoices = new ArrayList<>();

    public SAP_BasedInvoiceSender(FilterInvoice filter, SAP sap) {
        this.filter = filter;
        this.sap = sap;
    }

    // Now returns a list of invoices that failed to send
    public List<Invoice> sendLowValuedInvoices() throws FailToSendSAPInvoiceException {
        List<Invoice> lowValuedInvoices = filter.lowValueInvoices();
        List<Invoice> failedValueInvoices = new ArrayList<>();
        for (Invoice invoice : lowValuedInvoices) {
            try {
                sap.send(invoice);
            } catch (FailToSendSAPInvoiceException exception) {
                failedValueInvoices.add(invoice);
                // System.out.println("SAP invoice failed");
                // Handle or log, but rethrow if the test needs to see it
                System.out.println("SAP invoice failed: " + exception.getMessage());
                throw exception; // Ensure this is here if you expect to catch this in your test
            }
        }
        return failedValueInvoices;
    }
}
