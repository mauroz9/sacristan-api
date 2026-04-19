package com.sacristan.api.interfaces.student.home;

import com.sacristan.api.global.entities.content.rotuine.Routine;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeService {

    // * ROUTINES
    public Page<Routine> getRoutines(Pageable pageable) {
        // TODO: Implement get routines logic
        return null;
    }
}

