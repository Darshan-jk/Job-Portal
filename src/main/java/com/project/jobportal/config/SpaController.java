package com.project.jobportal.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpaController {

    @GetMapping({
            "/login",
            "/register",
            "/jobs/{id}",
            "/candidate/dashboard",
            "/recruiter/dashboard",
            "/admin/dashboard"
    })
    public String forwardToReact() {
        return "forward:/index.html";
    }
}