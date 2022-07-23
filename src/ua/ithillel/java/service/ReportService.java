package ua.ithillel.java.service;

import ua.ithillel.java.dto.*;
import ua.ithillel.java.model.Store;
import ua.ithillel.java.model.product.Buying;

import java.util.Arrays;
import java.util.Comparator;
public class ReportService {
    public static final String SUMMARY_REPORT = "Summary report";

    private Store[] stores;

    public ReportService(Store[] stores) {
        this.stores = stores;
    }

    public static int top = 5;

    public ReportTopFiveDto buildTopX() {
        StoreDto[] storeDtos = mapStores();
        BuyingDto[] buyingDtos = mergeBuyings(storeDtos);
        BuyingTopFiveDto[] buyingTopXDtoInterim = new BuyingTopFiveDto[buyingDtos.length];
        for (int i = 0; i < buyingTopXDtoInterim.length; i++) {
            String name = buyingDtos[i].getName();
            int count = buyingDtos[i].getCount();

            buyingTopXDtoInterim[i] = new BuyingTopFiveDto(name, count);
        }

        Arrays.sort(buyingTopXDtoInterim, Comparator.comparing(BuyingTopFiveDto::getCount).reversed());
        BuyingTopFiveDto[] buyingTop5Dto = new BuyingTopFiveDto[top];
        System.arraycopy(buyingTopXDtoInterim, 0, buyingTop5Dto, 0, top);
        return new ReportTopFiveDto(SUMMARY_REPORT, buyingTop5Dto);
    }

    public ReportDto build() {
        StoreDto[] storeDtos = mapStores();
        BuyingDto[] buyingDtos = mergeBuyings(storeDtos);
        double totalSum = 0;
        for (BuyingDto buyingDto : buyingDtos) {
            totalSum += buyingDto.getSum();
        }
        return new ReportDto(SUMMARY_REPORT, buyingDtos, totalSum);
    }

    private BuyingDto[] mergeBuyings(StoreDto[] storeDtos) {
        int count = getCount(storeDtos);
        BuyingDto[] totalArr = new BuyingDto[count];
        int i = 0;
        for (StoreDto storeDto : storeDtos) {
            for (BuyingDto sell : storeDto.getSells()) {
                totalArr[i++] = sell;
            }
        }
        return totalArr;
    }

    private int getCount(StoreDto[] storeDtos) {
        int count = 0;
        for (StoreDto storeDto : storeDtos) {
            count += storeDto.getSells().length;
        }
        return count;
    }

    private StoreDto[] mapStores() {
        StoreDto[] array = new StoreDto[stores.length];
        for (int i = 0; i < array.length; i++) {
            Store store = stores[i];
            String name = store.getName();
            BuyingDto[] buyingDtos = new BuyingDto[store.getSales().length];
            for (int j = 0; j < store.getSales().length; j++) {
                Buying sale = store.getSales()[j];
                double sum = sale.getProduct().getCost() * sale.getCount();
                buyingDtos[j] = new BuyingDto(sale.getProduct().getName(), sale.getCount(), sum);
            }
            array[i] = new StoreDto(name, buyingDtos);
        }
        return array;
    }
}

