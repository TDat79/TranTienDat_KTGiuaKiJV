package com.kiemtra.TranTienDat.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "nhanviens")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Mã Nhân Viên là bắt buộc")
    private String maNV;
    @NotBlank(message = "Tên là bắt buộc")
    private String ten;
    private String phai;
    private String noiSinh;
    private double luong;
    @ManyToOne
    @JoinColumn(name = "phongban_id")
    private PhongBan phongBan;
}
