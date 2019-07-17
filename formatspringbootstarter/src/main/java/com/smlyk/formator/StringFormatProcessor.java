package com.smlyk.formator;

import java.util.Objects;

/**
 * @author yekai
 */
public class StringFormatProcessor implements FormatProcessor{


    @Override
    public <T> String format(T obj) {

        return "StringFormatProcessor: "+ Objects.toString(obj);
    }
}
