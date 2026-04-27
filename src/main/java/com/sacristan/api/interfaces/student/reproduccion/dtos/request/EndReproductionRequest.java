package com.sacristan.api.interfaces.student.reproduccion.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EndReproductionRequest {
    
    private Long reproductionId;
    private List<StepStatsRequest> stepStats;
    private ButtonClicksRequest buttonClicks;
    private Integer totalClicks;
    private Integer totalSteps;
    
}
