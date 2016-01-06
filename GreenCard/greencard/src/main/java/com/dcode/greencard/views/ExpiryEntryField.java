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

import com.dcode.greencard.helpers.CardUtil;

/**
 * As of 30/11/2015.
 *
 * @author Miral Desai
 * @since 1.0
 * @version 1.0
 */
public class ExpiryEntryField extends EditText implements TextWatcher {

    String previousString;
    private ExpiryDateListener expiryListener;

    public ExpiryEntryField(Context context) {
        super(context);
        init();
    }

    public ExpiryEntryField(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ExpiryEntryField(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    /**
     * Instantiate the expiry entry with basic params. The rest is up to you.
     */
    private void init() {
        setInputType(InputType.TYPE_CLASS_NUMBER);
        addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        previousString = s.toString();
    }

    @Override
    public void afterTextChanged(Editable s) {
        String updatedString = s.toString();
        // if delete occurred do not format
        if (updatedString.length() > previousString.length()) {
            removeTextChangedListener(this);
            String formatted = CardUtil.formatExpirationDate(s.toString());
            setText(formatted);
            setSelection(formatted.length());
            addTextChangedListener(this);

            if(formatted.length() == 5) {
                onExpiryDateValid();
            }
        }
    }

    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) { }

    /**
     * Register a callback that gets invoked when certain params are met. For the
     * time being this is only when the expiry date is valid.
     *
     * @param listener the callback
     */
    public void setExpiryListener(ExpiryDateListener listener) {
        expiryListener = listener;
    }

    /**
     * Called when expiry date entered is valid
     */
    private void onExpiryDateValid() {
        expiryListener.expiryDateValid();
    }

    /**
     * <p>An expiry date listener receives notifications from the users expiry date entry.
     * Notifications indicate the expiry date is valid.</p>
     */
    public interface ExpiryDateListener {

        /**
         * called when data entry is complete and the expiry date is valid
         */
        void expiryDateValid();
    }
}
