package com.avasquez.vendingclient;

import com.avasquez.vendingclient.dto.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ShellComponent
public class VendingMachineCommand extends RestCommand{

    @Value("${vendingMachine.id}")
    private Long vendingMachineId;


    @ShellMethod("Get the list of items for the vending machine.")
    public void items() throws Exception {
        String endpoint = "/vending-machines/{vendingMachineId}/items";
        List<VendingMachineItemDTO> items = executeGetPagedContent(endpoint, VendingMachineItemDTO.class, vendingMachineId);
        System.out.println("== Items for Vending Machine ==");
        items.forEach(i -> {
            System.out.println("Item Id:" + i.getItemId());
            System.out.println("\tName:" + i.getItemName());
            System.out.println("\tPrice:" + i.getItemPrice());
        });
    }

    @ShellMethod("Get the amount of cash in the machine.")
    public void cashTotal() throws Exception {
        String endpoint = "/vending-machines/{vendingMachineId}/cash/total";
        VendingMachineTotalDTO cashTotal = executeGet(endpoint, VendingMachineTotalDTO.class, vendingMachineId);
        System.out.println("== Total Cash In Vending Machine ==");
        System.out.println(cashTotal.getTotal());
    }

    @ShellMethod("Get the amount of profit from the machine.")
    public void profit() throws Exception {
        String endpoint = "/vending-machines/{vendingMachineId}/transactions/profit";
        VendingMachineTotalDTO total = executeGet(endpoint, VendingMachineTotalDTO.class, vendingMachineId);
        System.out.println("== Total Profit From Vending Machine ==");
        System.out.println(total.getTotal());
    }

    @ShellMethod("Get the amount of transactions per day for the machine.")
    public void transactionsPerDay(String transactionDate) throws Exception {
        String endpoint = "/vending-machines/{vendingMachineId}/transactions/count/{transactionDate}";
        VendingMachineTotalDTO total = executeGet(endpoint, VendingMachineTotalDTO.class, vendingMachineId, transactionDate);
        System.out.println("== Total Transactions for Vending Machine ==");
        System.out.println(total.getTotal());
    }

    @ShellMethod("Opens the machine and gets all the cash inside.")
    public void open(String unlockCode) throws Exception {
        String endpoint = "/vending-machines/{vendingMachineId}/cash/open";
        VendingMachineCashDTO[] cashList = executePost(endpoint, unlockCode, VendingMachineCashDTO[].class, vendingMachineId);
        if(cashList != null) {
            System.out.println("== Cash Received from Machine ==");
            for (VendingMachineCashDTO d : cashList) {
                if (d.getCoinQuantity() != null) {
                    System.out.println("Coin Value:" + d.getCoinTypeValue());
                    System.out.println("Coin Quantity: " + d.getCoinQuantity() * -1);
                } else {
                    System.out.println("Bill Value:" + d.getBillTypeValue());
                    System.out.println("Bill Quantity: " + d.getBillQuantity() * -1);
                }
            }
        }
    }

    @ShellMethod("Posts a sale")
    public void sale(String itemCode, Integer itemQuantity, String paymentType,
                     @ShellOption(defaultValue="") BigDecimal[] bills, @ShellOption(defaultValue="") BigDecimal[] coins) throws Exception {
        VendingMachineTransactionSaleDTO sale = new VendingMachineTransactionSaleDTO();
        sale.setVendingMachineId(vendingMachineId);
        sale.setItemCode(itemCode);
        sale.setItemQuantity(itemQuantity);
        sale.setPaymentType(PaymentType.valueOf(paymentType));
        Map<BigDecimal, Integer> bc = new HashMap<>();
        for(BigDecimal b: bills) {
            if (bc.containsKey(b)) {
                bc.put(b, bc.get(b) + 1);
            } else {
                bc.put(b, 1);
            }
        }
        Map<BigDecimal, Integer> cc = new HashMap<>();
        for(BigDecimal c: coins) {
            if (cc.containsKey(c)) {
                cc.put(c, bc.get(c) + 1);
            } else {
                cc.put(c, 1);
            }
        }
        List<VendingMachineCashDTO> cash = new ArrayList<>();
        for(BigDecimal b : bc.keySet()) {
            VendingMachineCashDTO vm = new VendingMachineCashDTO();
            vm.setBillTypeValue(b);
            vm.setBillQuantity(bc.get(b));
            cash.add(vm);
        }
        for(BigDecimal b : cc.keySet()) {
            VendingMachineCashDTO vm = new VendingMachineCashDTO();
            vm.setBillTypeValue(b);
            vm.setBillQuantity(cc.get(b));
            cash.add(vm);
        }
        sale.setVendingMachineCashs(cash);

        String endpoint = "/vending-machines/transactions/sale";
        VendingMachineTransactionDTO result = executePost(endpoint, sale, VendingMachineTransactionDTO.class);
        if(result != null) {
            if(result.getPaymentType() == PaymentType.CASH) {
                System.out.println("== Sale registered ==");
                System.out.println("Cash Received: " + result.getCashInAmount());
                System.out.println("Cash Change: " + result.getCashChange());
            } else {
                System.out.println("== Sale Receipt ==");
                System.out.println("Item:           " + result.getItemId());
                System.out.println("Quantity:       " + result.getItemQuantity());
                System.out.println("Date:           " + result.getTransactionDate());
                System.out.println("Payment Type:   " + result.getPaymentType());
                System.out.println("Payment Amount: " + result.getPaymentAmount());
            }
        }
    }
}
