package com.example.search.controller;

import com.example.search.repository.TitleSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class TitleSearchController {
    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "/get/{keyword}/{size}", method = RequestMethod.GET)
    public List<String> getTitle(@PathVariable String keyword, @PathVariable int size) throws IOException {
        TitleSearchRepository titleSearchRepository = context.getBean(TitleSearchRepository.class);
        return titleSearchRepository.getTitle(keyword, size);
    }

}
