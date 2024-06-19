package com.kiemtra.TranTienDat.repository;

import com.kiemtra.TranTienDat.model.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, Long> {
}
