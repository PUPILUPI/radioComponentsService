package ru.belov.radioComponentsService.domain.apiFormat;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseMapper {
    public RadioComponentFormat toCommonFormat(ChipFindFormat req) {
        RadioComponentFormat res = new RadioComponentFormat();
        res.setName(req.getPart());
        if (Integer.parseInt(req.getInstock()) == 0) {
            res.setInStockFlag(false);
        } else {
            res.setInStockFlag(true);
        }
        try {
            res.setQuantity(Integer.parseInt(req.getStock()));
        } catch (NumberFormatException e) {
            res.setQuantity(0);
            res.setOrderDays(req.getStock() + "дней");
        }
        try {
            res.setMinQuantity(Integer.parseInt(req.getMin()));
        } catch (NumberFormatException e) {
            res.setMinQuantity(1);
        }
        res.setFirstPrice(Double.parseDouble(req.getP1()));
        try {
            res.setSecondPrice(Double.parseDouble(req.getP2()));
        } catch (NumberFormatException e) {
        }
        try {
            res.setThirdPrice(Double.parseDouble(req.getP3()));
        } catch (NumberFormatException e) {
        }
        res.setSecondQuantity(100);
        res.setThirdQuantity(200);
        return res;
    }

    public RadioComponentFormat toCommonFormat(FreeChipsFormat req) {
        RadioComponentFormat res = new RadioComponentFormat();
        res.setName(req.getName());
        if (req.getStockQuant() == 0) {
            res.setInStockFlag(false);
            res.setOrderDays(req.getOrderDays() + " дней");
        } else {
            res.setInStockFlag(true);
        }
        res.setQuantity(req.getStockQuant());
        res.setMinQuantity(req.getMinQuant());
        List<FreeChipsFormat.Price> prices = req.getPrices();
        res.setFirstPrice(prices.get(0).value);
        res.setSecondQuantity(prices.get(1).breakQty);
        res.setSecondPrice(prices.get(1).value);
        res.setSecondQuantity(prices.get(1).breakQty);
        res.setSecondPrice(prices.get(1).value);
        res.setThirdQuantity(prices.get(2).breakQty);
        res.setThirdPrice(prices.get(2).value);
        return res;
    }
}
