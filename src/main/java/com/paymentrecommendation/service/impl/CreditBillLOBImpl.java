package com.paymentrecommendation.service.impl;

import com.paymentrecommendation.enums.LineOfBusiness;
import com.paymentrecommendation.enums.PaymentInstrumentType;
import com.paymentrecommendation.service.LineOfBusinessService;

import java.util.*;

public class CreditBillLOBImpl implements LineOfBusinessService {
  public List<String> paymentModeOrder =
      new ArrayList<>(
          Arrays.asList(
              PaymentInstrumentType.UPI.name(),
              PaymentInstrumentType.NETBANKING.name(),
              PaymentInstrumentType.DEBIT_CARD.name()));
  public Map<String, Long> paymentModeLimit = new HashMap<>();

  public CreditBillLOBImpl() {
    paymentModeLimit.put(PaymentInstrumentType.UPI.name(), 200000L);
    paymentModeLimit.put(PaymentInstrumentType.NETBANKING.name(), 200000L);
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
