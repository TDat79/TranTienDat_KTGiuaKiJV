package com.kiemtra.TranTienDat.controller;

import com.kiemtra.TranTienDat.model.PhongBan;
import com.kiemtra.TranTienDat.service.PhongBanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PhongBanController {
    @Autowired
    private final PhongBanService phongBanService;

    @GetMapping("/phongban/add")
    public String showAddForm(Model model) {
        model.addAttribute("phongban", new PhongBan());
        return "/phongban/add-phongban";
    }

    @PostMapping("/phongban/add")
    public String addCategory(@Valid PhongBan phongban, BindingResult result) {
        if (result.hasErrors()) {
            return "/phongban/add-phongban";
        }
        phongBanService.addPhongBan(phongban);
        return "redirect:/phongban";
    }

    // Hiển thị danh sách danh mục
    @GetMapping("/phongban")
    public String listCategories(Model model) {
        List<PhongBan> phongbans = phongBanService.getAll();
        model.addAttribute("phongbans", phongbans);
        return "/phongban/phongban-list";
    }
    @GetMapping("/phongban/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        PhongBan phongban = phongBanService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid phongban Id:"
                        + id));
        model.addAttribute("phongban", phongban);
        return "/phongban/update-phongban";
    }
    // POST request to update category
    @PostMapping("/phongban/update/{id}")
    public String updateCategory(@PathVariable("id") Long id, @Valid PhongBan phongban,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            phongban.setId(id);
            return "/phongban/update-phongban";
        }
        phongBanService.updatePhongBan(phongban);
        model.addAttribute("phongbans", phongBanService.getAll());
        return "redirect:/phongban";
    }
    // GET request for deleting category
    @GetMapping("/phongban/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id, Model model) {
        PhongBan phongban = phongBanService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid phongban Id:"
                        + id));
        phongBanService.deleteById(id);
        model.addAttribute("phongbans", phongBanService.getAll());
        return "redirect:/phongban";
    }
}
