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
package com.dcode.greencard.views;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import com.dcode.greencard.helpers.CardType;
import com.dcode.greencard.helpers.CardUtil;
import com.dcode.greencard.helpers.CreditCard;
import com.dcode.greencard.helpers.Type;

/**
 * As of 30/11/2015.
 *
 * @author Miral Desai
 * @since 1.0
 * @version 1.0
 */
public class CardEntryField extends EditText implements TextWatcher {

    private CardType type;
    private boolean valid = false;
    private Context context;
    CardEntryListener entryListener;

    public CardEntryField(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public CardEntryField(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public CardEntryField(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    /**
     * Instantiate the card entry with basic params. The rest is up to you.
     */
    private void init() {
        setInputType(InputType.TYPE_CLASS_NUMBER);
        addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    public void afterTextChanged(Editable s) {
        String number = s.toString();
        if (number.length() >= CardUtil.CC_LEN_FOR_TYPE) {
            CardType type = CardUtil.findCardType(number);

            if (type.equals(CardType.INVALID)) {
                setCardValid(false);
                onBadInput(this);
                return;
            }

            if (this.type != type) {
                onCardTypeChange(type);
            }
            this.type = type;

            String formatted = CardUtil.formatForViewing(number, type);
            if (!number.equalsIgnoreCase(formatted)) {
                removeTextChangedListener(this);
                setText(formatted);
                setSelection(formatted.length());
                addTextChangedListener(this);
            }

            if (formatted.length() >= CardUtil.lengthOfFormattedStringForType(type)) {
                if (CardUtil.isValidNumber(formatted)) {
                    setCardValid(true);
                    onCreditCardNumberValid();
                } else {
                    setCardValid(false);
                    onBadInput(this);
                }
            }
        } else {
            if (this.type != null) {
                this.type = null;
                onCardTypeChange(CardType.INVALID);
            }
        }
    }

    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) { }

    /**
     * Register a callback that gets invoked when certain params are met.
     *
     * @param listener the callback
     */
    public void setCardListener(CardEntryListener listener) {
        entryListener = listener;
    }

    private void onCardTypeChange(CardType type) {
        Type.setType(type);
        entryListener.onCardTypeChange(type);
    }

    private void onBadInput(EditText field) {
        entryListener.onBadInput(field);
    }

    private CreditCard getCreditCard(CardType type) {
        return new CreditCard(this.getText().toString(), type);
    }

    /**
     * TextWatcher calls this when card number is valid, user can override via interface
     */
    private void onCreditCardNumberValid() {
        Type.setType(type);
        entryListener.cardValid(getCreditCard(type));
    }

    private void setCardValid(boolean valid) {
        this.valid = valid;
    }

    /**
     *
     * @return Returns whether the card entered is valid.
     */
    public boolean isCardValid() {
        return valid;
    }

    /**
     * <p>An card number listener receives notifications from the users expiry date entry.
     * Notifications indicate various things such as the card number being valid
     * or the card type.</p>
     */
    public interface CardEntryListener {

        /**
         * Called when data entry is complete and the card is valid
         * @param card the validated card
         */
        void cardValid(CreditCard card);

        /**
         * Called when the card type changes
         * @param type the type of card
         */
        void onCardTypeChange(CardType type);

        /**
         * When the card number entered is not valid, this method gets called, to notify the user.
         * @param field the EditText field
         */
        void onBadInput(EditText field);
    }
}
