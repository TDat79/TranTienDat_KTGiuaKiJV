package com.kiemtra.TranTienDat.controller;

import com.kiemtra.TranTienDat.model.NhanVien;
import com.kiemtra.TranTienDat.service.NhanVienService;
import com.kiemtra.TranTienDat.service.PhongBanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/nhanvien")
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private PhongBanService phongBanService;
    @GetMapping
    public String showNhanVienList(Model model) {
        model.addAttribute("nhanviens", nhanVienService.getAll());
        return "/nhanvien/nhanvien-list";
    }
    // For adding a new product
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("nhanvien", new NhanVien());
        model.addAttribute("phongban", phongBanService.getAll()); //Load categories
        return "/nhanvien/add-nhanvien";
    }
    // Process the form for adding a new product
    @PostMapping("/add")
    public String addNhanVien(@Valid NhanVien nhanVien, BindingResult result) {
        if (result.hasErrors()) {
            return "/nhanvien/add-nhanvien";
        }
        nhanVienService.addNhanVien(nhanVien);
        return "redirect:/nhanvien";
    }
    /*private String saveImageStatic(MultipartFile image) throws IOException {
        File saveFile = new ClassPathResource("static/images").getFile();
        String fileName = UUID.randomUUID()+ "." + StringUtils.getFilenameExtension(image.getOriginalFilename());
        Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + fileName);
        Files.copy(image.getInputStream(), path);
        return fileName;
    }*/



    // For editing a product
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        NhanVien nhanVien = nhanVienService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("nhanvien", nhanVien);
        model.addAttribute("phongbans", phongBanService.getAll()); //Load categories
        return "/nhanvien/update-nhanvien";
    }
    // Process the form for updating a product
    @PostMapping("/update/{id}")
    public String updateNhanVien(@PathVariable Long id, @Valid NhanVien nhanVien,
                                BindingResult result) {
        if (result.hasErrors()) {
            nhanVien.setId(id); // set id to keep it in the form in case of errors
            return "/nhanvien/update-nhanvien";
        }
        /*if (!imageFile.isEmpty()) {
            try {
                String imageName = saveImageStatic(imageFile);
                nhanVien.setImgUrl("/images/" +imageName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        nhanVienService.updateNhanVien(nhanVien);
        return "redirect:/nhanvien";
    }
    // Handle request to delete a product
    @GetMapping("/delete/{id}")
    public String deleteNhanVien(@PathVariable Long id) {
        nhanVienService.deleteById(id);
        return "redirect:/nhanvien";
    }
}
