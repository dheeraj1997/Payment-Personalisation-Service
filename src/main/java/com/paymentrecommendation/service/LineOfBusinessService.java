package com.paymentrecommendation.service;

import java.util.List;
import java.util.Map;

public interface LineOfBusinessService {
    public abstract List<String> getPaymentModeOrder();
    public abstract Map<String, Long> getPaymentModeLimit();
}
