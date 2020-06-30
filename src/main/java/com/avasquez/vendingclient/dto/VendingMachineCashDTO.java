package com.avasquez.vendingclient.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class VendingMachineCashDTO implements Serializable {

    private Long id;

    private Integer coinQuantity;

    private Integer billQuantity;

    private Long vendingMachineId;

    private Long coinTypeId;

    private String coinTypeName;

    private BigDecimal coinTypeValue;

    private Long billTypeId;

    private String billTypeName;

    private BigDecimal billTypeValue;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCoinQuantity() {
        return coinQuantity;
    }

    public void setCoinQuantity(Integer coinQuantity) {
        this.coinQuantity = coinQuantity;
    }

    public Integer getBillQuantity() {
        return billQuantity;
    }

    public void setBillQuantity(Integer billQuantity) {
        this.billQuantity = billQuantity;
    }

    public Long getVendingMachineId() {
        return vendingMachineId;
    }

    public void setVendingMachineId(Long vendingMachineId) {
        this.vendingMachineId = vendingMachineId;
    }

    public Long getCoinTypeId() {
        return coinTypeId;
    }

    public void setCoinTypeId(Long coinTypeId) {
        this.coinTypeId = coinTypeId;
    }

    public Long getBillTypeId() {
        return billTypeId;
    }

    public void setBillTypeId(Long billTypeId) {
        this.billTypeId = billTypeId;
    }

    public String getCoinTypeName() {
        return coinTypeName;
    }

    public void setCoinTypeName(String coinTypeName) {
        this.coinTypeName = coinTypeName;
    }

    public BigDecimal getCoinTypeValue() {
        return coinTypeValue;
    }

    public void setCoinTypeValue(BigDecimal coinTypeValue) {
        this.coinTypeValue = coinTypeValue;
    }

    public String getBillTypeName() {
        return billTypeName;
    }

    public void setBillTypeName(String billTypeName) {
        this.billTypeName = billTypeName;
    }

    public BigDecimal getBillTypeValue() {
        return billTypeValue;
    }

    public void setBillTypeValue(BigDecimal billTypeValue) {
        this.billTypeValue = billTypeValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VendingMachineCashDTO)) {
            return false;
        }

        return id != null && id.equals(((VendingMachineCashDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }


    @Override
    public String toString() {
        return "VendingMachineCashDTO{" +
            "id=" + getId() +
            ", coinQuantity=" + getCoinQuantity() +
            ", billQuantity=" + getBillQuantity() +
            ", vendingMachineId=" + getVendingMachineId() +
            ", coinTypeId=" + getCoinTypeId() +
            ", billTypeId=" + getBillTypeId() +
            "}";
    }
}
