package ru.belov.radioComponentsService.domain.apiFormat;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.List;

@Component
public class ChipFindParser {
    //    public ChipFindFormat parse(String response)  throws JAXBException {
//        JAXBContext jaxbContext = JAXBContext.newInstance(ChipFindFormat.class);
//        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//        StringReader reader = new StringReader(response);
//        ChipFindFormat data = (ChipFindFormat) unmarshaller.unmarshal(reader);
//        return data;
//    }
//    public ChipFindFormats parse(String response) throws JAXBException {
//        JAXBContext jaxbContext = JAXBContext.newInstance(ChipFindFormats.class);
//        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//        StringReader reader = new StringReader(response);
//        return (ChipFindFormats) unmarshaller.unmarshal(reader);
//    }
    public List<ChipFindFormat> parse(String xmlString) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(ChipFindFormatListWrapper.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ChipFindFormatListWrapper wrapper = (ChipFindFormatListWrapper) unmarshaller.unmarshal(new StringReader(xmlString));
        return wrapper.getItems();
    }
}
