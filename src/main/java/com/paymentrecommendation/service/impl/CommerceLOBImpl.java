package com.paymentrecommendation.service.impl;

import com.paymentrecommendation.enums.PaymentInstrumentType;
import com.paymentrecommendation.service.LineOfBusinessService;
import java.util.*;

public class CommerceLOBImpl implements LineOfBusinessService {
  public List<String> paymentModeOrder =
      new ArrayList<>(
          Arrays.asList(
              PaymentInstrumentType.CREDIT_CARD.name(),
              PaymentInstrumentType.UPI.name(),
              PaymentInstrumentType.DEBIT_CARD.name()));
  public Map<String, Long> paymentModeLimit = new HashMap<>();

  public CommerceLOBImpl() {
    paymentModeLimit.put(PaymentInstrumentType.CREDIT_CARD.name(), 250000L);
    paymentModeLimit.put(PaymentInstrumentType.UPI.name(), 100000L);
    paymentModeLimit.put(PaymentInstrumentType.DEBIT_CARD.name(), 200000L);
  }

  @Override
  public List<String> getPaymentModeOrder() {
    return paymentModeOrder;
  }

  @Override
  public Map<String, Long> getPaymentModeLimit() {
    return paymentModeLimit;
  }
}
