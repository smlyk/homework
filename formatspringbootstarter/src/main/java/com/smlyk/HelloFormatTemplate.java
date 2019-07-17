package com.smlyk;

import com.smlyk.autoconfiguration.HelloProperties;
import com.smlyk.formator.FormatProcessor;

/**
 * @author yekai
 */
public class HelloFormatTemplate {

    private FormatProcessor formatProcessor;

    private HelloProperties helloProperties;

    public HelloFormatTemplate( HelloProperties helloProperties, FormatProcessor formatProcessor) {
        this.helloProperties = helloProperties;
        this.formatProcessor = formatProcessor;
    }

    public <T> String doFormat(T obj){

        StringBuilder sb = new StringBuilder();
        sb.append("begin:Execute format").append("<br/>");
        sb.append("HelloProperties: ").append(formatProcessor.format(helloProperties.getInfo())).append("<br/>");
        sb.append("Obj result format: ").append(formatProcessor.format(obj)).append("<br/>");
        return sb.toString();
    }

}
