package org.hwskylo.filescanner.Repository;


import org.hwskylo.filescanner.Entity.SimRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimRecordRepository extends JpaRepository<SimRecord, Long> {
    boolean existsByImsi(Long imsi);
}
