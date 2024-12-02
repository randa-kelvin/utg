package com.untucapital.usuite.utg.utils;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class PhoneNumberUtils {

    private static final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    public static String normalizePhoneNumber(String phoneNumber, String defaultRegion) {
        try {
            PhoneNumber numberProto = phoneNumberUtil.parse(phoneNumber, defaultRegion);
            return phoneNumberUtil.format(numberProto, PhoneNumberUtil.PhoneNumberFormat.E164);
        } catch (NumberParseException e) {
            throw new IllegalArgumentException("Invalid phone number", e);
        }
    }
}

