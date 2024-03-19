package com.paymentrecommendation.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.paymentrecommendation.enums.LineOfBusiness;
import com.paymentrecommendation.enums.PaymentInstrumentType;

/** lineOfBusinessRelevanceOrder */
public class LineOfBusinessRelevanceOrder {
  public static Map<String, List<String>> relevanceOrder = new HashMap<>();

  public LineOfBusinessRelevanceOrder() {
    relevanceOrder.put(
        LineOfBusiness.CREDIT_CARD_BILL_PAYMENT.name(),
        new ArrayList<>(
            Arrays.asList(
                PaymentInstrumentType.UPI.name(),
                PaymentInstrumentType.NETBANKING.name(),
                PaymentInstrumentType.DEBIT_CARD.name())));
    relevanceOrder.put(
        LineOfBusiness.COMMERCE.name(),
        new ArrayList<>(
            Arrays.asList(
                PaymentInstrumentType.CREDIT_CARD.name(),
                PaymentInstrumentType.UPI.name(),
                PaymentInstrumentType.DEBIT_CARD.name())));
    relevanceOrder.put(
        LineOfBusiness.INVESTMENT.name(),
        new ArrayList<>(
            Arrays.asList(
                PaymentInstrumentType.UPI.name(),
                PaymentInstrumentType.NETBANKING.name(),
                PaymentInstrumentType.DEBIT_CARD.name())));
  }
}
