package ua.ithillel.java.controller;


import ua.ithillel.java.dto.ReportDto;
import ua.ithillel.java.dto.ReportTopFiveDto;
import ua.ithillel.java.model.Store;
import ua.ithillel.java.service.ReportService;
import ua.ithillel.java.view.ReportTopFive;
import ua.ithillel.java.view.ReportView;

public class ReportController {

    public void execute(Store[] stores) {
        ReportService reportService = new ReportService(stores);
        ReportDto report = reportService.build();
        ReportView view = new ReportView();
        view.printDetails(report);
        ReportTopFiveDto reportTop5 = reportService.buildTopX();
        ReportTopFive viewTop5 = new ReportTopFive();
        viewTop5.printDetails(reportTop5);

    }
}

