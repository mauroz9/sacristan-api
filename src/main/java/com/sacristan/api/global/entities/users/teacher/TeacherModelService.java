package com.sacristan.api.global.entities.users.teacher;

import com.sacristan.api.global.dtos.SortParamDTO;
import com.sacristan.api.global.entities.users.user.User;
import com.sacristan.api.global.services.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherModelService extends BaseServiceImpl<Teacher, Long, TeacherRepository> {

    public static List<SortParamDTO> getSortParams() {
        return List.of(
                new SortParamDTO("Nombre", "user.name"),
                new SortParamDTO("Apellidos", "user.lastName"),
                new SortParamDTO("Email", "user.email"),
                new SortParamDTO("Usuario", "user.username")
        );
    }

}



