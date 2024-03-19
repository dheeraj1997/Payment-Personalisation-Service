package com.paymentrecommendation.helper;

import com.paymentrecommendation.models.PaymentInstrument;

import java.util.List;

public class CustomSort {

  public static List<PaymentInstrument> sortByRelevanceScore(
      List<PaymentInstrument> instrumentList) {
    int n = instrumentList.size();
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (instrumentList.get(j).getRelevanceScore()
            < instrumentList.get(j + 1).getRelevanceScore()) {
          PaymentInstrument temp = instrumentList.get(j);
          instrumentList.set(j, instrumentList.get(j + 1));
          instrumentList.set(j + 1, temp);
        }
      }
    }
    return instrumentList;
  }
}
