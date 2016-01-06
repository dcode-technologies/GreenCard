/*
 * The MIT License (MIT)
 * Copyright (C) 2016 DCODE TECHNOLOGIES LTD
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.dcode.greencard.helpers;

import java.io.Serializable;

/**
 * * As of 30/11/2015.
 *
 * @author Miral Desai
 * @since 1.0
 * @version 1.0
 */
public class CreditCard implements Serializable {

    private final String cardNumber;
    private final CardType cardType;

    public CreditCard(String cardNumber, CardType cardType) {
        this.cardNumber = cardNumber;
        this.cardType = cardType;
    }

    @SuppressWarnings("unused")
    public String getCardNumber() {
        return cardNumber;
    }

    @SuppressWarnings("unused")
    public CardType getCardType() {
        return cardType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreditCard{");
        sb.append("cardNumber='").append(cardNumber).append('\'');
        sb.append(", cardType=").append(cardType);
        sb.append('}');
        return sb.toString();
    }
}
