/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dto;

import java.io.InputStream;

/**
 *
 * @author tasmi
 */
public class CandidateDTO {
    private String candidateId;
    private String party;
    private String user_Id;
    private InputStream symbol ;
    private String city;

    public CandidateDTO() {
    }

    public CandidateDTO(String candidateId, String party, String user_Id, InputStream symbol, String city) {
        this.candidateId = candidateId;
        this.party = party;
        this.user_Id = user_Id;
        this.symbol = symbol;
        this.city = city;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public InputStream getSymbol() {
        return symbol;
    }

    public void setSymbol(InputStream symbol) {
        this.symbol = symbol;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "CandidateDTO{" + "candidateId=" + candidateId + ", party=" + party + ", user_Id=" + user_Id + ", symbol=" + symbol + ", city=" + city + '}';
    }
    
    
}
