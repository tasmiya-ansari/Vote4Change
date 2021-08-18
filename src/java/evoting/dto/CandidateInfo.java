/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dto;

/**
 *
 * @author tasmi
 */
public class CandidateInfo {
    private String candidateid;
    private String candidatename;
    private String party;
    private String symbol;

    public CandidateInfo() {
    }

    public CandidateInfo(String candidateid, String candidatename, String party, String symbol) {
        this.candidateid = candidateid;
        this.candidatename = candidatename;
        this.party = party;
        this.symbol = symbol;
    }

    public String getCandidateid() {
        return candidateid;
    }

    public void setCandidateid(String candidateid) {
        this.candidateid = candidateid;
    }

    public String getCandidatename() {
        return candidatename;
    }

    public void setCandidatename(String candidatename) {
        this.candidatename = candidatename;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "CandidateInfo{" + "candidateid=" + candidateid + ", candidatename=" + candidatename + ", party=" + party + ", symbol=" + symbol + '}';
    }
}
