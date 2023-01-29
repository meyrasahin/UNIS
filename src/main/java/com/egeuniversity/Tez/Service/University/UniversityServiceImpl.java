package com.egeuniversity.Tez.Service.University;

import com.egeuniversity.Tez.Model.University.University;
import com.egeuniversity.Tez.Model.University.UniversityRequestDto;
import com.egeuniversity.Tez.Repository.University.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService{

    private final UniversityRepository universityRepository;

    @Override
    public University addUniversity(UniversityRequestDto universityRequestDto) {
        return universityRepository.save(assembleAddUniversity(universityRequestDto));
    }

    public University assembleAddUniversity(UniversityRequestDto universityRequestDto) {
        return University.builder()
                .universityName(universityRequestDto.getUniversityName())
                .address(universityRequestDto.getAddress())
                .phone(universityRequestDto.getPhone())
                .build();
    }
    @Override
    public List<University> getAllUniversity() {
        return universityRepository.findAll();
    }
    @Override
    public void deleteUniversity(Integer id) {
        universityRepository.deleteById(id);
    }
}
