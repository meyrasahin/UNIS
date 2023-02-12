package com.egeuniversity.Tez.Service.University;

import com.egeuniversity.Tez.Model.Address.Address;
import com.egeuniversity.Tez.Model.University.University;
import com.egeuniversity.Tez.Model.University.UniversityRequestDto;
import com.egeuniversity.Tez.Repository.University.UniversityRepository;
import com.egeuniversity.Tez.Service.Address.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl implements UniversityService{

    private final UniversityRepository universityRepository;
    private final AddressService addressService;

    @Override
    public University addUniversity(UniversityRequestDto universityRequestDto) {
        return universityRepository.save(assembleAddUniversity(universityRequestDto));
    }

    public University assembleAddUniversity(UniversityRequestDto universityRequestDto) {
        Address address = addressService.assembleAddress(universityRequestDto.getAddressRequestDTO());
        return University.builder()
                .universityName(universityRequestDto.getUniversityName())
                .phone(universityRequestDto.getPhone())
                .address(address)
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

    @Override
    public University get(Integer id) {
        return universityRepository.get(id);
    }
}
