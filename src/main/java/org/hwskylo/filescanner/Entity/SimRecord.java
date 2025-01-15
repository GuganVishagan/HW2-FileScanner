package org.hwskylo.filescanner.Entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Data
public class SimRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(unique = true, nullable = false)
    private Long imsi;

    private String msisdn;

    private LocalDateTime createdAt = LocalDateTime.now();

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public void setImsi(Long imsi) {
        this.imsi = imsi;
    }

}

