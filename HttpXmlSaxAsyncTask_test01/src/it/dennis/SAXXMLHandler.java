package it.dennis;


import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
 
public class SAXXMLHandler extends DefaultHandler {
    private List<Laptop> laptops;
    private String tempVal;
    // to maintain context
    private Laptop laptop;
 
    public SAXXMLHandler() {
        laptops = new ArrayList<Laptop>();
    }
 
    public List<Laptop> getLaptops() {
        return laptops;
    }
 
    // Event Handlers
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        // reset
        tempVal = "";
        if (qName.equalsIgnoreCase("laptop")) {
            // create a new instance of Laptop
            laptop = new Laptop();
            laptop.setModel(attributes.getValue("model"));
        }
    }
 
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        tempVal = new String(ch, start, length);
    }
 
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
 
        if (qName.equalsIgnoreCase("laptop")) {
            // add it to the list
            laptops.add(laptop);
        } else if (qName.equalsIgnoreCase("brand")) {
            laptop.setBrand(tempVal);
        } else if (qName.equalsIgnoreCase("description")) {
            laptop.setDescription(tempVal);
        } else if (qName.equalsIgnoreCase("technical-details")) {
            laptop.setTechDetails(tempVal);
        } else if (qName.equalsIgnoreCase("image-url")) {
            laptop.setImageURL(tempVal);
        } else if (qName.equalsIgnoreCase("price")) {
            laptop.setPrice(tempVal);
        }
    }
}