package com.nafanny.parsingservice.handler.parser;

import com.nafanny.parsingservice.handler.context.Contextable;
import com.nafanny.parsingservice.provider.xml.StaxProvider;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.util.Stack;

final class XmlParser implements Parseable, Contextable {
    @Override
    public boolean tryParse(final InputStream stream) {

        try (final StaxProvider provider = new StaxProvider(stream)) {
            final XMLStreamReader reader = provider.getReader();
            while (reader.hasNext()) {
                final int eventType = reader.next();
                if (eventType == XMLEvent.START_ELEMENT) {
                    reader.getAttributeCount();
                }
            }
        } catch (XMLStreamException e) {
            System.out.println("Stream Exception " + e);
        }

        return false;
    }

    public String parseXML() {
        Stack<String> st = new Stack<>();
        boolean lnFlag = false, flag = false;
        int evt, att = 0;

        return null;
    }

    private String getAttributes(final XMLStreamReader reader) {
        return null;
    }
}
