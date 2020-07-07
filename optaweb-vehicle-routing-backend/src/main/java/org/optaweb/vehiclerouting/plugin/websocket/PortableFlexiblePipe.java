package org.optaweb.vehiclerouting.plugin.websocket;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.optaweb.vehiclerouting.domain.FlexiblePipe;

public class PortableFlexiblePipe {
    @JsonProperty(value = "diameter")
    private final int diameter;
    @JsonProperty(value = "length")
	private final long length;
    @JsonProperty(value = "invoiceNumber")
    private final long invoiceNumber;

    @JsonCreator
    public PortableFlexiblePipe(int diameter, long length, long invoiceNumber) {
        this.diameter = diameter;
        this.length = length;
        this.invoiceNumber = invoiceNumber;
    }

    static PortableFlexiblePipe createFrom(FlexiblePipe flexiblePipe) {
        return new PortableFlexiblePipe(flexiblePipe.getDiameter(), flexiblePipe.getLength(), flexiblePipe.getInvoiceNumber());
    }
    public int getDiameter() {
        return diameter;
    }

    public long getLength() {
        return length;
    }

    public long getInvoiceNumber() {
        return invoiceNumber;
    }
}