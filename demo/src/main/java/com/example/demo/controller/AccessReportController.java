package com.example.demo.controller;

import com.example.demo.model.AccessReport;
import com.example.demo.service.GitHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccessReportController {

    @Autowired
    private GitHubService gitHubService;

    @GetMapping("/access-report")
    public List<AccessReport> getAccessReport() {

        return gitHubService.getAccessReport();
    }
}