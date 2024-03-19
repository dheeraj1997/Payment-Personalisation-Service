package com.paymentrecommendation.factories;

import com.paymentrecommendation.enums.LineOfBusiness;
import com.paymentrecommendation.service.LineOfBusinessService;
import com.paymentrecommendation.service.impl.CommerceLOBImpl;
import com.paymentrecommendation.service.impl.CreditBillLOBImpl;
import com.paymentrecommendation.service.impl.InvestmentLOBImpl;

public class LOBFactory {

    public static LineOfBusinessService getLOB(String lob) {
        if (lob.equals(LineOfBusiness.CREDIT_CARD_BILL_PAYMENT.name())) {
            return new CreditBillLOBImpl();
        } else if (lob.equals(LineOfBusiness.COMMERCE.name())) {
            return new CommerceLOBImpl();
        } else if (lob.equals(LineOfBusiness.INVESTMENT.name())) {
            return new InvestmentLOBImpl();
        }
        return null;
    }
}
