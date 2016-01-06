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
import com.dcode.greencard.helpers.Type;

/**
 * As of 30/11/2015.
 *
 * @author Miral Desai
 * @since 1.0
 * @version 1.0
 */
public class CvvEntryField extends EditText implements TextWatcher {

    private CardType type;
    private CvvListener cvvListener;

    public CvvEntryField(Context context) {
        super(context);
        init();
    }

    public CvvEntryField(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CvvEntryField(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    /**
     * Instantiate the cvv entry with basic params. The rest is up to you.
     */
    private void init() {
        setInputType(InputType.TYPE_CLASS_NUMBER);
        addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        type = Type.getType();
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (type != null) {
            if (s.length() == CardUtil.securityCodeValid(type)) {
                onCvvValid();
            }
        }
    }

    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {

    }

    /**
     * Register a callback that gets invoked when certain params are met.
     *
     * @param listener the callback
     */
    public void setCvvListener(CvvListener listener) {
        cvvListener = listener;
    }

    private void onCvvValid() {
        cvvListener.cvvValid();
    }


    /**
     * <p>An cvv listener receives notifications from the users expiry date entry.
     * Notifications indicate the cvv is valid.</p>
     */
    public interface CvvListener {

        /**
         * called when data entry is complete and the cvv is valid
         */
        void cvvValid();
    }
}
