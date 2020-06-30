package com.avasquez.vendingclient.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class VendingMachineTransactionSaleDTO {

    private Long id;

    @NotNull
    @Min(value = 0)
    private Integer itemQuantity;

    @NotNull
    private PaymentType paymentType;

    @NotNull
    private Long vendingMachineId;

    @NotNull
    private Long itemId;

    private String itemCode;

    private List<VendingMachineCashDTO> vendingMachineCashs;

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

    public List<VendingMachineCashDTO> getVendingMachineCashs() {
        return vendingMachineCashs;
    }

    public void setVendingMachineCashs(List<VendingMachineCashDTO> vendingMachineCashs) {
        this.vendingMachineCashs = vendingMachineCashs;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VendingMachineTransactionSaleDTO)) {
            return false;
        }

        return id != null && id.equals(((VendingMachineTransactionSaleDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }


    @Override
    public String toString() {
        return "VendingMachineTransactionSaleDTO{" +
                "id=" + getId() +
                ", itemQuantity=" + getItemQuantity() +
                ", paymentType='" + getPaymentType() + "'" +
                ", vendingMachineId=" + getVendingMachineId() +
                ", itemId=" + getItemId() +
                "}";
    }
}
