package com.avasquez.vendingclient.dto;

import java.math.BigDecimal;

public class VendingMachineTotalDTO {
    private Long id;

    private String name;

    private BigDecimal total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VendingMachineTotalDTO)) {
            return false;
        }

        return id != null && id.equals(((VendingMachineTotalDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }


    @Override
    public String toString() {
        return "VendingMachineTotalDTO{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", total='" + getTotal() + "'" +
                "}";
    }
}
