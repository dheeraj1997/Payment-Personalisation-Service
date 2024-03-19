package com.paymentrecommendation.service.impl;

import com.paymentrecommendation.enums.PaymentInstrumentType;
import com.paymentrecommendation.factories.LOBFactory;
import com.paymentrecommendation.helper.CustomSort;
import com.paymentrecommendation.models.Cart;
import com.paymentrecommendation.models.PaymentInstrument;
import com.paymentrecommendation.models.User;
import com.paymentrecommendation.service.LineOfBusinessService;
import com.paymentrecommendation.service.PaymentRecommender;

import java.util.*;

public class PaymentRecommenderImpl implements PaymentRecommender {

  @Override
  public List<PaymentInstrument> recommendPaymentInstruments(User user, Cart cart) {

    LineOfBusinessService lineOfBusinessService =
        LOBFactory.getLOB(cart.getLineOfBusiness().name());
    Map<String, Long> paymentModeLimits = lineOfBusinessService.getPaymentModeLimit();
    List<String> paymentModeOrder = lineOfBusinessService.getPaymentModeOrder();

    Map<String, List<PaymentInstrument>> allowedModeInstrumentsMap = new HashMap<>();

    user.getUserPaymentInstrument()
        .getPaymentInstruments()
        .forEach(
            paymentInstrument -> {
              String type = paymentInstrument.getPaymentInstrumentType().name();
              if (paymentModeLimits.containsKey(type)) {
                boolean flag = true;
                if (type == PaymentInstrumentType.UPI.name()
                    && !user.getUserContext().getDeviceContext().isUpiEnabled()) {
                  flag = false;
                }
                if (flag) {
                  // UPI -> [instrument1, ...]
                  if (allowedModeInstrumentsMap.containsKey(type)) {
                    allowedModeInstrumentsMap.get(type).add(paymentInstrument);
                  } else {
                    allowedModeInstrumentsMap.put(
                        type, new ArrayList<>(Arrays.asList(paymentInstrument)));
                  }
                }
              }
            });

    Set<String> modes = allowedModeInstrumentsMap.keySet();

    Map<String, List<PaymentInstrument>> filteredModeInstrumentsMap = new HashMap<>();
    modes.forEach(
        mode -> {
          Long instrumentLimit = paymentModeLimits.get(mode);
          if (instrumentLimit > cart.getCartDetail().getCartAmount()) {
            filteredModeInstrumentsMap.put(mode, allowedModeInstrumentsMap.get(mode));
          }
        });

    List<PaymentInstrument> finalInstruments = new ArrayList<>();
    paymentModeOrder.forEach(
        mode -> {
          if (filteredModeInstrumentsMap.containsKey(mode)) {
            List<PaymentInstrument> allowedInstruments = filteredModeInstrumentsMap.get(mode);
            List<PaymentInstrument> sortedInstruments =
                CustomSort.sortByRelevanceScore(allowedInstruments);
            sortedInstruments.forEach(
                instrument -> {
                  finalInstruments.add(instrument);
                });
          }
        });

    return finalInstruments;
  }
}
