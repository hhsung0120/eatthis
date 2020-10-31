package co.kr.heeseong.eatthis.service;

import co.kr.heeseong.eatthis.domain.test.TestEntity;
import co.kr.heeseong.eatthis.domain.test.TestRepository;
import co.kr.heeseong.eatthis.dto.Test;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class TestService {

    private TestRepository testRepository;

    public Long insertTestUser(Test test) {
        return testRepository.save(test.toEntity()).getIdx();
    }

    @Transactional
    public void updateTestUser(Test test) {
        TestEntity t = testRepository.findById(test.getIdx()).orElseThrow(() -> new IllegalArgumentException("게시물이 없습니다." + test.getIdx()));
        t.update(test.getUserId(),test.getUserName());
    }

    public void deleteTestUser(Test test) {
        testRepository.deleteById(test.getIdx());
    }


    public List<Test> getTestList() {
        List<Test> testList = new ArrayList<>();
        Page<TestEntity> testEntity = testRepository.findAll(PageRequest.of(0,10, Sort.by(Sort.Direction.DESC,"regDate")));

        for(TestEntity entity : testEntity){
            Test testDto = Test.builder()
                    .idx(entity.getIdx())
                    .userId(entity.getUserId())
                    .userName(entity.getUserName())
                    .build();

            testList.add(testDto);
        }
        return testList;
    }
}
