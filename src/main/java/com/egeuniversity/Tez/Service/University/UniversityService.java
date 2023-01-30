package com.egeuniversity.Tez.Service.University;


import com.egeuniversity.Tez.Model.University.University;
import com.egeuniversity.Tez.Model.University.UniversityRequestDto;
import java.util.List;

public interface UniversityService {

    University addUniversity(UniversityRequestDto universityRequestDto);
    List<University> getAllUniversity();
    void deleteUniversity(Integer id);
    University get(Integer id);
}
