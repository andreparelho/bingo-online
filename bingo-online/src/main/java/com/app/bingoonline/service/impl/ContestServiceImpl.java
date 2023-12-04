package com.app.bingoonline.service.impl;

import com.app.bingoonline.repository.ContestRepository;
import com.app.bingoonline.repository.TicketRepository;
import com.app.bingoonline.service.ContestService;
import com.app.entity.ContestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ContestServiceImpl implements ContestService {
    private final TicketRepository ticketRepository;
    private final ContestRepository contestRepository;
    private Random random;

    @Autowired
    public ContestServiceImpl(TicketRepository ticketRepository, ContestRepository contestRepository, Random random) {
        this.ticketRepository = ticketRepository;
        this.contestRepository = contestRepository;
        this.random = random;
    }

    @Override
    public int generateContestNumber() {
        long numContest = this.random.nextInt(1000, 9999);
        boolean hasContest = checkContestNumber(numContest);

        while (hasContest) {
            numContest = this.random.nextInt(1000, 9999);
            hasContest = checkContestNumber(numContest);
        }

        return (int) numContest;
    }

    @Override
    public ContestEntity createContest(int contestNumber) {
        ContestEntity contestEntity = new ContestEntity();
        contestEntity.setContestNumber(contestNumber);
        this.contestRepository.saveContest(contestEntity);
        return contestEntity;
    }

    @Override
    public int findContestById(int contestNumber) {
        ContestEntity contestEntity =  checkContestNumber(contestNumber);
        return contestEntity.getContestNumber();
    }

    private ContestEntity checkContestNumber(int numContest){
        return this.contestRepository.findContestNumber(numContest);
    }

    private boolean checkContestNumber(long numContest){
        ContestEntity hasContest = this.contestRepository.findContestNumber((int) numContest);
        return hasContest != null;
    }
}
