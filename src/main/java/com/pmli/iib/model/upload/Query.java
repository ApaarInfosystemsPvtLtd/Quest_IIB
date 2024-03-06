package com.pmli.iib.model.upload;

import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Query")
@XmlAccessorType(XmlAccessType.FIELD)
@Component
@ToString
public class Query {

    @XmlElement(name = "Details")
    Details details;

    public Details getDetails() {
        return this.details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }
}
