package ru.belov.radioComponentsService.domain.apiFormat;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "List")
public class ChipFindFormatListWrapper {
    private List<ChipFindFormat> items;

    @XmlElement(name = "item")
    public List<ChipFindFormat> getItems() {
        return items;
    }

    public void setItems(List<ChipFindFormat> items) {
        this.items = items;
    }
}
