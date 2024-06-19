package com.kiemtra.TranTienDat.repository;

import com.kiemtra.TranTienDat.model.PhongBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhongBanRepository extends JpaRepository<PhongBan, Long> {
}
