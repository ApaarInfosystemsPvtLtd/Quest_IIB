package com.pmli.iib.model.upload;

import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@NoArgsConstructor
@ToString
@XmlRootElement(name = "FileHeader")
@XmlAccessorType(XmlAccessType.FIELD)
public class FileHeader {

    @XmlElement(name = "Status")
    private String status;
    @XmlElement(name = "Remarks")
    private String remarks;
    @XmlElement(name = "TransactionID")
    private String transactionID;

    public String getStatus() {
        return status;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getTransactionID() {
        return transactionID;
    }
}
