package com.avasquez.vendingclient.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineTransactionDTO implements Serializable {

    private Long id;

    private Integer itemQuantity;

    private BigDecimal paymentAmount;

    private BigDecimal cashInAmount;

    private BigDecimal cashChange;

    private String transactionDate;

    private PaymentType paymentType;

    private Long vendingMachineId;

    private Long itemId;

    private List<VendingMachineCashDTO> changeDetail = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public BigDecimal getCashInAmount() {
        return cashInAmount;
    }

    public void setCashInAmount(BigDecimal cashInAmount) {
        this.cashInAmount = cashInAmount;
    }

    public BigDecimal getCashChange() {
        return cashChange;
    }

    public void setCashChange(BigDecimal cashChange) {
        this.cashChange = cashChange;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Long getVendingMachineId() {
        return vendingMachineId;
    }

    public void setVendingMachineId(Long vendingMachineId) {
        this.vendingMachineId = vendingMachineId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public List<VendingMachineCashDTO> getChangeDetail() {
        return changeDetail;
    }

    public void setChangeDetail(List<VendingMachineCashDTO> changeDetail) {
        this.changeDetail = changeDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VendingMachineTransactionDTO)) {
            return false;
        }

        return id != null && id.equals(((VendingMachineTransactionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }


    @Override
    public String toString() {
        return "VendingMachineTransactionDTO{" +
            "id=" + getId() +
            ", itemQuantity=" + getItemQuantity() +
            ", paymentAmount=" + getPaymentAmount() +
            ", cashInAmount=" + getCashInAmount() +
            ", cashChange=" + getCashChange() +
            ", transactionDate='" + getTransactionDate() + "'" +
            ", paymentType='" + getPaymentType() + "'" +
            ", vendingMachineId=" + getVendingMachineId() +
            ", itemId=" + getItemId() +
            "}";
    }
}
