package com.sacristan.api.interfaces.student.reproduccion.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StepStatsRequest {
    
    private Integer stepIndex;
    private Integer viewCount;
    private Long totalViewTimeMs;
    
}
