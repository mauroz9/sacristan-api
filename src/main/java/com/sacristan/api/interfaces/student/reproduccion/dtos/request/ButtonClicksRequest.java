package com.sacristan.api.interfaces.student.reproduccion.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ButtonClicksRequest {
    
    private Integer previous;
    private Integer next;
    private Integer complete;
    private Integer exit;
    
}
