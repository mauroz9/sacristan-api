package com.sacristan.api.interfaces.student.services.user;

import com.sacristan.api.global.models.Routine;
import com.sacristan.api.global.models.Sequence;
import com.sacristan.api.global.models.user.User;
import com.sacristan.api.global.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentUserUtilsService {

    private final StudentRepository studentRepository;

    public List<Sequence> getSequencesByUserId(User user) {
        return studentRepository.findAllSequencesByUserId(user.getId());
    }

    public List<Routine> getRoutinesByUserId(User user) {
        return studentRepository.findAllRoutinesByUserId(user.getId());
    }

}
