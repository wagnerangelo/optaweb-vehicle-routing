package org.optaweb.vehiclerouting.service.demo.dataset;

public class FlexiblePipeData implements FlexiblePipeDataInterface {

    private int diameter;

	private long length;

	private long invoiceNumber;

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

}