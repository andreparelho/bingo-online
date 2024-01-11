package integration.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

public class DataMapper {
    @Value("classpath:mappings/contests/create-constest-ok.json")
    private Resource createContestOk;

    @Value("classpath:mappings/tickets/create-ticket-by-contest-ok.json")
    private Resource createTicketByContestOk;

    @Value("classpath:mappings/tickets/get-all-contests-ok.json")
    private Resource getAllContestsOk;

    @Value("classpath:mappings/tickets/get-all-tickets-by-contest-ok.json")
    private Resource getAllTicketsByContestOk;

    @Value("classpath:mappings/raffle/get-raffle-number-by-contest-ok.json")
    private Resource getRaffleNumberByContestOk;

    public Resource getCreateContestOk() {
        return createContestOk;
    }

    public void setCreateContestOk(Resource createContestOk) {
        this.createContestOk = createContestOk;
    }

    public Resource getCreateTicketByContestOk() {
        return createTicketByContestOk;
    }

    public void setCreateTicketByContestOk(Resource createTicketByContestOk) {
        this.createTicketByContestOk = createTicketByContestOk;
    }

    public Resource getGetAllContestsOk() {
        return getAllContestsOk;
    }

    public void setGetAllContestsOk(Resource getAllContestsOk) {
        this.getAllContestsOk = getAllContestsOk;
    }

    public Resource getGetAllTicketsByContestOk() {
        return getAllTicketsByContestOk;
    }

    public void setGetAllTicketsByContestOk(Resource getAllTicketsByContestOk) {
        this.getAllTicketsByContestOk = getAllTicketsByContestOk;
    }

    public Resource getGetRaffleNumberByContestOk() {
        return getRaffleNumberByContestOk;
    }

    public void setGetRaffleNumberByContestOk(Resource getRaffleNumberByContestOk) {
        this.getRaffleNumberByContestOk = getRaffleNumberByContestOk;
    }
}
