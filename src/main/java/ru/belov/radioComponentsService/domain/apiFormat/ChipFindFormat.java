package ru.belov.radioComponentsService.domain.apiFormat;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ChipFindFormat {
    private String mfr;
    @XmlElement(required = true)
    private String part;
    private String note;
    private String pack;
    private String min;
    private String img;
    private String pdf;
    private String dc;
    private String cur;
    private String p1;
    private String p2;
    private String p3;
    private String stock;
    private String instock;
}

