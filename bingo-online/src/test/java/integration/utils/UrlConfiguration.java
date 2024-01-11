package integration.utils;

import org.springframework.beans.factory.annotation.Value;

public class UrlConfiguration {
    @Value("${integration.url.create-contest}")
    private String createContest;
    @Value("${integration.url.create-tickets-by-contest}")
    private String createTicketByContest;
    @Value("${integration.url.get-all-contests}")
    private String getAllContests;
    @Value("${integration.url.get-all-tickets-by-contest}")
    private String getAllTicketsByContest;
    @Value("${integration.url.get-raffle-number-by-contest}")
    private String getRaffleNumberByContest;

    public String getCreateContest() {
        return createContest;
    }

    public void setCreateContest(String createContest) {
        this.createContest = createContest;
    }

    public String getCreateTicketByContest() {
        return createTicketByContest;
    }

    public void setCreateTicketByContest(String createTicketByContest) {
        this.createTicketByContest = createTicketByContest;
    }

    public String getGetAllContests() {
        return getAllContests;
    }

    public void setGetAllContests(String getAllContests) {
        this.getAllContests = getAllContests;
    }

    public String getGetAllTicketsByContest() {
        return getAllTicketsByContest;
    }

    public void setGetAllTicketsByContest(String getAllTicketsByContest) {
        this.getAllTicketsByContest = getAllTicketsByContest;
    }

    public String getGetRaffleNumberByContest() {
        return getRaffleNumberByContest;
    }

    public void setGetRaffleNumberByContest(String getRaffleNumberByContest) {
        this.getRaffleNumberByContest = getRaffleNumberByContest;
    }
}
