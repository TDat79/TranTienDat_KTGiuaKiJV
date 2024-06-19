package com.kiemtra.TranTienDat.service;

import com.kiemtra.TranTienDat.model.NhanVien;
import com.kiemtra.TranTienDat.repository.NhanVienRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional
public class NhanVienService {
    private final NhanVienRepository nhanVienRepository;
    // Retrieve all NhanViens from the database
    public List<NhanVien> getAll() {
        return nhanVienRepository.findAll();
    }
    // Retrieve a NhanVien by its id
    public Optional<NhanVien> getById(Long id) {
        return nhanVienRepository.findById(id);
    }
    // Add a new NhanVien to the database
    public NhanVien addNhanVien(NhanVien nhanVien) {
        return nhanVienRepository.save(nhanVien);
    }
    // Update an existing NhanVien
    public NhanVien updateNhanVien(@NotNull NhanVien nhanVien) {
        NhanVien existingNhanVien = nhanVienRepository.findById(nhanVien.getId())
                .orElseThrow(() -> new IllegalStateException("Product with ID " +
                        nhanVien.getId() + " does not exist."));
        existingNhanVien.setMaNV(nhanVien.getMaNV());
        existingNhanVien.setTen(nhanVien.getTen());
        existingNhanVien.setPhai(nhanVien.getPhai());
        existingNhanVien.setNoiSinh(nhanVien.getNoiSinh());
        existingNhanVien.setLuong(nhanVien.getLuong());
        existingNhanVien.setPhongBan(nhanVien.getPhongBan());
        return nhanVienRepository.save(existingNhanVien);
    }
    // Delete a NhanVien by its id
    public void deleteById(Long id) {
        if (!nhanVienRepository.existsById(id)) {
            throw new IllegalStateException("Product with ID " + id + " does not exist.");
        }
        nhanVienRepository.deleteById(id);
    }


}
