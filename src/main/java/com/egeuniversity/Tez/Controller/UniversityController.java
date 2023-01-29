package com.egeuniversity.Tez.Controller;

import com.egeuniversity.Tez.Model.University.University;
import com.egeuniversity.Tez.Model.University.UniversityRequestDto;
import com.egeuniversity.Tez.Service.University.UniversityServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UniversityController {
    private final UniversityServiceImpl universityServiceImpl;

    @PostMapping(path = "/university/add")
    public ResponseEntity<Void> addUniversity(@RequestBody UniversityRequestDto universityRequestDto) {
        universityServiceImpl.addUniversity(universityRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/university/getAll")
    public List<University> getAllUniversity() {
        return universityServiceImpl.getAllUniversity();
    }

    @DeleteMapping(path= "/university/delete")
    public ResponseEntity<Void> deleteUniversity(@RequestBody Integer id) {
        universityServiceImpl.deleteUniversity(id);
        return ResponseEntity.ok().build();
    }
}