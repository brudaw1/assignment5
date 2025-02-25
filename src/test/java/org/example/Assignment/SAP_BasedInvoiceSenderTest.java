// Brunel Dawood - Improved error handling and added exception testing

package org.example.Assignment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class SAP_BasedInvoiceSenderTest {

    @Test
    void testThrowExceptionWhenBadInvoice() throws FailToSendSAPInvoiceException {
        // Creating the mock objects for FilterInvoice and SAP
        FilterInvoice filter = mock(FilterInvoice.class);
        SAP sap = mock(SAP.class);

        // Creating a specific invoice that should cause the exception to be thrown
        Invoice badInvoice = new Invoice("Faulty Customer", 100);

        // Configuring the mock to throw an exception when sap.send() is called with badInvoice
        doThrow(new FailToSendSAPInvoiceException("Invoice processing failed due to error"))
                .when(sap).send(badInvoice);

        // Setting up the FilterInvoice to return a list containing the badInvoice
        when(filter.lowValueInvoices()).thenReturn(Arrays.asList(badInvoice));

        // Instance of the class under test
        SAP_BasedInvoiceSender sender = new SAP_BasedInvoiceSender(filter, sap);

        // Assertion to check that the exception is thrown when sendLowValuedInvoices() is called
        assertThrows(FailToSendSAPInvoiceException.class, sender::sendLowValuedInvoices,
                "The method should throw FailToSendSAPInvoiceException when processing badInvoice");

        // Verifying that sap.send() was indeed called with the badInvoice
        verify(sap).send(badInvoice);
    }
}
