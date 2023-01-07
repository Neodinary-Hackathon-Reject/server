package com.example.cloudtypetest.service.contest;

import com.example.cloudtypetest.domain.Contest;
import com.example.cloudtypetest.repository.ContestRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ContestServiceImpl implements ContestService{

    private final ContestRepository contestRepository;

    public List<Contest> findAll() {
        return contestRepository.findAll();
    }
}
