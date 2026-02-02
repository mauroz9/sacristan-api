package com.sacristan.api.global.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "reproduction")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Reproduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    @ElementCollection
    @CollectionTable(name = "reproduction_clicks", joinColumns = @JoinColumn(name = "reproduction_id"))
    @MapKeyColumn(name = "button_name")
    @Column(name = "times_clicked")
    private Map<String, Integer> buttonsClicks = new HashMap<>();
}
