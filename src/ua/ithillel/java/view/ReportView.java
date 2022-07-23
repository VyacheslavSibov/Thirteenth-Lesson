package ua.ithillel.java.view;

import ua.ithillel.java.dto.BuyingDto;
import ua.ithillel.java.dto.ReportDto;

public class ReportView {

    public static final String DESCRIPTION = "REPORT DATA: ";

    public void printDetails(ReportDto report) {
        int dash = 50;
        System.out.println("=".repeat(dash));
        System.out.println(DESCRIPTION + report.getName());
        System.out.println("=".repeat(dash));

        int labelWidth = 20;

        System.out.println("name" + " ".repeat(labelWidth - "name".length()) +
                "count" + " ".repeat(labelWidth - "count".length()) +
                "sum" + " ".repeat(labelWidth - "sum".length()));
        System.out.println("=".repeat(dash));

        for (BuyingDto buyingDto : report.getData()) {
            String cost = Math.round(buyingDto.getSum()) + " uah";
            System.out.println(buyingDto.getName() + " ".repeat(labelWidth - buyingDto.getName().length())
                    + buyingDto.getCount() + " ".repeat(labelWidth - Integer.toString(buyingDto.getCount()).length())
                    + cost);
            System.out.println("-".repeat(dash));
        }
        System.out.println("=".repeat(dash));
        System.out.println("Total sum: " + report.getTotalSum() + " uah");

    }
}