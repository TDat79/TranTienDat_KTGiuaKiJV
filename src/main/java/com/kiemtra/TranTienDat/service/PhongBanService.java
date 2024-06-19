package com.kiemtra.TranTienDat.service;

import com.kiemtra.TranTienDat.model.PhongBan;
import com.kiemtra.TranTienDat.repository.PhongBanRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional
public class PhongBanService {
    private final PhongBanRepository phongBanRepository;

    public List<PhongBan> getAll() {
        return phongBanRepository.findAll();
    }

    public Optional<PhongBan> getById(Long id) {
        return phongBanRepository.findById(id);
    }

    public void addPhongBan(PhongBan category) {
        phongBanRepository.save(category);
    }

    public void updatePhongBan(@NotNull PhongBan category) {
        PhongBan existingCategory = phongBanRepository.findById(category.getId())
                .orElseThrow(() -> new IllegalStateException("PhongBan with ID " +
                        category.getId() + " does not exist."));
        existingCategory.setMaPhong(category.getMaPhong());
        existingCategory.setName(category.getName());
        phongBanRepository.save(existingCategory);
    }

    public void deleteById(Long id) {
        if (!phongBanRepository.existsById(id)) {
            throw new IllegalStateException("PhongBan with ID " + id + " does not exist.");
        }
        phongBanRepository.deleteById(id);
    }
}
