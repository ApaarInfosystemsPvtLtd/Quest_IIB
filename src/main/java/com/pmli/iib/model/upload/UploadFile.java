package com.pmli.iib.model.upload;

import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@NoArgsConstructor
@XmlRootElement(name = "UploadFile")
@XmlAccessorType(XmlAccessType.FIELD)
@Component
@ToString
public class UploadFile {

    @XmlElement(name = "FileHeader")
    FileHeader fileHeader;

    public FileHeader getFileHeader() {
        return fileHeader;
    }
}
