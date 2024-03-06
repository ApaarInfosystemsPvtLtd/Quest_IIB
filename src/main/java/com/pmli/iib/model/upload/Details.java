package com.pmli.iib.model.upload;

import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class Details {

    @XmlElement(name = "Policy_Number")
    private String policyNumber = "";

    @XmlElement(name = "Proposal_Number")
    private String proposalNumber = "";

    @XmlElement(name = "Query_Type")
    private String queryType = "";

    @XmlElement(name = "DoP_DoC")
    private String doPDoC = "";

    @XmlElement(name = "Sum_Assured")
    private String sumAssured = "";

    @XmlElement(name = "LA_First_Name")
    private String laFirstName = "";

    @XmlElement(name = "LA_Middle_Name")
    private String laMiddleName = "";

    @XmlElement(name = "LA_Last_Name")
    private String laLastName = "";

    @XmlElement(name = "LA_DoB")
    private String laDob = "";

    @XmlElement(name = "LA_Gender")
    private String laGender = "";

    @XmlElement(name = "LA_Current_Address")
    private String laCurrentAddress = "";

    @XmlElement(name = "LA_Permanent_Address")
    private String laPermanentAddress = "";

    @XmlElement(name = "LA_Pin_Code")
    private String laPinCode = "";

    @XmlElement(name = "LA_PAN")
    private String laPan = "";

    @XmlElement(name = "LA_Aadhar")
    private String laAadhar = "";

    @XmlElement(name = "LA_Ckyc")
    private String laCkyc = "";

    @XmlElement(name = "LA_Passport")
    private String laPassport = "";

    @XmlElement(name = "LA_DL")
    private String laDl = "";

    @XmlElement(name = "LA_Voter_Id")
    private String laVoterId = "";

    @XmlElement(name = "LA_Phone_Number_1")
    private String laPhoneNumber1 = "";

    @XmlElement(name = "LA_Phone_Number_2")
    private String laPhoneNumber2 = "";

    @XmlElement(name = "LA_Email_1")
    private String laEmail1 = "";

    @XmlElement(name = "LA_Email_2")
    private String laEmail2 = "";

    @XmlElement(name = "Date_of_Death")
    private String dateOfDeath = "";

    @XmlElement(name = "Company_Number")
    private String companyNumber ="";

    @XmlElement(name = "Product_Type")
    private String productType ="";

    @XmlElement(name = "Product_UIN")
    private String productUIN ="";

    @XmlElement(name = "Annual_Income")
    private String annualIncome ="";

    @XmlElement(name = "Cause_of_Death")
    private String causeOfDeath ="";

    public String getPolicyNumber() {
        return policyNumber;
    }

    public String getProposalNumber() {
        return proposalNumber;
    }

    public String getQueryType() {
        return queryType;
    }

    public String getDoPDoC() {
        return doPDoC;
    }

    public String getSumAssured() {
        return sumAssured;
    }

    public String getLaFirstName() {
        return laFirstName;
    }

    public String getLaMiddleName() {
        return laMiddleName;
    }

    public String getLaLastName() {
        return laLastName;
    }

    public String getLaDob() {
        return laDob;
    }

    public String getLaGender() {
        return laGender;
    }

    public String getLaCurrentAddress() {
        return laCurrentAddress;
    }

    public String getLaPermanentAddress() {
        return laPermanentAddress;
    }

    public String getLaPinCode() {
        return laPinCode;
    }

    public String getLaPan() {
        return laPan;
    }

    public String getLaAadhar() {
        return laAadhar;
    }

    public String getLaCkyc() {
        return laCkyc;
    }

    public String getLaPassport() {
        return laPassport;
    }

    public String getLaDl() {
        return laDl;
    }

    public String getLaVoterId() {
        return laVoterId;
    }

    public String getLaPhoneNumber1() {
        return laPhoneNumber1;
    }

    public String getLaPhoneNumber2() {
        return laPhoneNumber2;
    }

    public String getLaEmail1() {
        return laEmail1;
    }

    public String getLaEmail2() {
        return laEmail2;
    }

    public String getDateOfDeath() {
        return dateOfDeath;
    }

    public String getCompanyNumber() {
        return companyNumber;
    }

    public String getProductType() {
        return productType;
    }

    public String getProductUIN() {
        return productUIN;
    }

    public String getAnnualIncome() {
        return annualIncome;
    }

    public String getCauseOfDeath() {
        return causeOfDeath;
    }
}
