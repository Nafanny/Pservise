package com.nafanny.parsingservice.service.impl;

import com.nafanny.parsingservice.provider.xml.StaxProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.nafanny.parsingservice.service.ParseService;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.util.Stack;

@Service
public class ParseServiceImpl implements ParseService {
    @Override
    public void parse(MultipartFile data) throws XMLStreamException, IOException {
        final StaxProvider provider = new StaxProvider(data.getInputStream());
        final XMLStreamReader reader = provider.getReader();

        Stack<String> st = new Stack<>();
        boolean lnFlag = false,flag = false;
        int evt, att=0;
        while (reader.hasNext()) {

            evt = reader.next();

            if (evt == XMLEvent.START_ELEMENT && (att = reader.getAttributeCount())> 0 ){
                st.push(reader.getName().getLocalPart());
                for (String string : st) {
                    System.out.print("/" + string);}
                for (int i = 0; i < att; i++) {
                    System.out.print("-[" + reader.getAttributeName(i)+ "]:\"" + reader.getAttributeValue(i)+"\"");
                    lnFlag=true;
                }

                flag = false;
            } else if (evt == XMLEvent.START_ELEMENT && (att = reader.getAttributeCount())== 0 ) {
                st.push(reader.getName().getLocalPart());
                if (flag){
                    for (String string : st) {
                        System.out.print("/" + string);}

                }

                flag = true;

            }

            if (evt == XMLEvent.END_ELEMENT) {
                st.pop();
                if (!flag)System.out.println("-----");
                flag = false;
            }
            if (reader.hasText() && reader.getText().trim().length() > 0 && evt != XMLEvent.COMMENT) {

                if (att>0){System.out.print(": " + reader.getText()); System.out.println("");lnFlag=false; }

                else {
                    if(lnFlag){System.out.println(""); lnFlag=false;}
                    for (String string : st) {
                        System.out.print("/" + string);}
                    System.out.println(": " + reader.getText());
                }}

        }
    }

}
