package com.example.DaoTranHuyHoang;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

@Controller
public class IntroController {

    private final ProfileRepository profileRepository;

    public IntroController(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @GetMapping("/")
    public String intro(Model model) {
        Profile profile = profileRepository.findFirstByOrderByIdAsc()
                .orElseGet(() -> profileRepository.save(Profile.builder()
                        .fullName("Đào Trần Huy Hoàng")
                        .groupName("Thất kiếm anh hùng")
                        .studentId("2280601019")
                        .build()));
        model.addAttribute("profile", profile);
        return "intro";
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        Profile profile = profileRepository.findFirstByOrderByIdAsc()
                .orElseGet(() -> profileRepository.save(Profile.builder()
                        .fullName("Đào Trần Huy Hoàng")
                        .groupName("Thất kiếm anh hùng")
                        .studentId("2280601019")
                        .build()));
        model.addAttribute("profile", profile);
        return "edit";
    }

    @PostMapping("/edit")
    public String save(@ModelAttribute Profile form) {
        Profile profile = profileRepository.findById(form.getId() != null ? form.getId() : -1L)
                .orElseGet(() -> new Profile());
        profile.setFullName(form.getFullName());
        profile.setGroupName(form.getGroupName());
        profile.setStudentId(form.getStudentId());
        profileRepository.save(profile);
        return "redirect:/";
    }
}
