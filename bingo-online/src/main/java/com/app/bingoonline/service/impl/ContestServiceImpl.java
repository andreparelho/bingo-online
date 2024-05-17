package com.app.bingoonline.service.impl;

import com.app.bingoonline.entity.RaffleEntity;
import com.app.bingoonline.controller.response.ContestResponseList;
import com.app.bingoonline.controller.response.CreateContestResponse;
import com.app.bingoonline.repository.ContestRepository;
import com.app.bingoonline.repository.RaffleRepository;
import com.app.bingoonline.service.ContestService;
import com.app.bingoonline.entity.ContestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class ContestServiceImpl implements ContestService {
    private final ContestRepository contestRepository;
    private final RaffleRepository raffleRepository;
    private Random random;

    @Autowired
    public ContestServiceImpl(ContestRepository contestRepository, RaffleRepository raffleRepository, Random random) {
        this.contestRepository = contestRepository;
        this.raffleRepository = raffleRepository;
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
    public ContestEntity createContest(int contestNumber) { //DELETAR METODO
        ContestEntity contestEntity = ContestEntity.builder()
                .number(contestNumber)
                .contestNumber(contestNumber)
                .build();

        this.contestRepository.saveContest(contestEntity);
        return contestEntity;
    }

    @Override
    public CreateContestResponse createContest() {
        int contestNumber = this.generateContestNumber();

        ContestEntity contestEntity = ContestEntity.builder()
                .number(contestNumber)
                .contestNumber(contestNumber)
                .initiated(false)
                .createdAt(Instant.now())
                .build();

        RaffleEntity raffle = RaffleEntity.builder()
                .contestId(((long) contestNumber))
                .raffleNumbers(new RaffleEntity().generateRaffleNumbers())
                .build();

        this.contestRepository.saveContest(contestEntity);
        this.raffleRepository.saveRaffle(raffle);

        Map<String, Set<Integer>> response = new HashMap<>();
        response.put("contest", Collections.singleton(contestEntity.getContestNumber()));

        return new CreateContestResponse(response);
    }

    @Override
    public int findContestById(int contestNumber) {
        ContestEntity contestEntity = this.checkContestNumber(contestNumber);
        return contestEntity.getContestNumber();
    }

    @Override
    public ContestEntity findContest(int contestNumber) {
        return this.contestRepository.findContestNumber(contestNumber);
    }

    @Override
    public ContestEntity getAllRaffleNumbers(int contestNumber) {
        return this.contestRepository.findContestNumber(contestNumber);
    }

    @Override
    public ContestEntity saveRaffleNumber(int contestNumber, String raffleNumber) {
        return this.contestRepository.saveRaffleNumberOnContest(contestNumber, raffleNumber);
    }

    @Override
    public ContestResponseList getAllContests() {
        List<ContestEntity> contestList = this.contestRepository.getAllContests();
        List<Integer> allContests = new ArrayList<>();

        for (ContestEntity contest : contestList) {
            allContests.add(contest.getContestNumber());
        }

        return new ContestResponseList(allContests);
    }

    private ContestEntity checkContestNumber(int numContest){
        return this.contestRepository.findContestNumber(numContest);
    }

    private boolean checkContestNumber(long numContest){
        ContestEntity hasContest = this.contestRepository.findContestNumber((int) numContest);
        return hasContest != null;
    }
}
