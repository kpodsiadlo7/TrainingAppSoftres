package com.mainapp.web;

import com.mainapp.service.MainService;
import com.mainapp.service.data.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {

    private final MainService mainService;

    @GetMapping
    public String getMain(@AuthenticationPrincipal User user, ModelMap modelMap) {
        return mainService.getMain(user, modelMap);
    }
}
