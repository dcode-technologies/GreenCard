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

import android.support.annotation.DrawableRes;

import com.dcode.greencard.R;

import java.io.Serializable;

/**
 * * As of 30/11/2015.
 *
 * @author Miral Desai
 * @since 1.0
 * @version 1.0
 */
class CardRegex {
    // See: http://www.regular-expressions.info/creditcard.html
    static final String REGX_VISA = "^4[0-9]{15}?"; // VISA 16
    static final String REGX_MC = "^5[1-5][0-9]{14}$"; // MC 16
    static final String REGX_AMEX = "^3[47][0-9]{13}$"; // AMEX 15
    static final String REGX_DISCOVER = "^6(?:011|5[0-9]{2})[0-9]{12}$"; // Discover 16
    static final String REGX_DINERS_CLUB = "^3(?:0[0-5]|[68][0-9])[0-9]{11}$"; // DinersClub 14

    static final String REGX_VISA_TYPE = "^4[0-9]{3}?"; // VISA 16
    static final String REGX_MC_TYPE = "^5[1-5][0-9]{2}$"; // MC 16
    static final String REGX_AMEX_TYPE = "^3[47][0-9]{2}$"; // AMEX 15
    static final String REGX_DISCOVER_TYPE = "^6(?:011|5[0-9]{2})$"; // Discover 16
    static final String REGX_DINERS_CLUB_TYPE = "^3(?:0[0-5]|[68][0-9])[0-9]$"; // DinersClub
}

/**
 * represents the type of card the user used
 */
public enum CardType implements Serializable {
    VISA("VISA", R.drawable.ic_visa, CardRegex.REGX_VISA, CardRegex.REGX_VISA_TYPE),
    MASTERCARD("MasterCard", R.drawable.ic_mastercard, CardRegex.REGX_MC, CardRegex.REGX_MC_TYPE),
    AMEX("American Express", R.drawable.ic_amex, CardRegex.REGX_AMEX, CardRegex.REGX_AMEX_TYPE),
    DISCOVER("Discover", R.drawable.ic_unknown, CardRegex.REGX_DISCOVER, CardRegex.REGX_DISCOVER_TYPE),
    INVALID("Unknown", R.drawable.ic_unknown, null, null);

    /** name for humans */
    public final String name;

    /** regex that matches the entire card number */
    public final String fullRegex;

    /** regex that will match when there is enough of the card to determine type */
    public final String typeRegex;

    /** drawable for the front of the card */
    public final int frontResource;

    CardType(String name, @DrawableRes int imageResource, String fullRegex, String typeRegex) {
        this.name = name;
        this.frontResource = imageResource;
        this.fullRegex = fullRegex;
        this.typeRegex = typeRegex;
    }

    @Override
    public String toString() {
        return name;
    }
}