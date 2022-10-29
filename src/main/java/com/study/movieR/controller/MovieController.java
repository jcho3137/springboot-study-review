package com.study.movieR.controller;

import com.study.movieR.dto.MovieDTO;
import com.study.movieR.dto.MovieImageDTO;
import com.study.movieR.dto.PageRequestDTO;
import com.study.movieR.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequestMapping("/movie/")
@RequiredArgsConstructor
public class MovieController
{
    private final MovieService movieService;

    @GetMapping("/register")
    public void register() {
        log.info("register get...");
    }

    @PostMapping("/register")
    public String register(MovieDTO movieDTO, RedirectAttributes redirectAttributes) {
        log.info("movieDTO : " + movieDTO);
        Long mno = movieService.register(movieDTO);
        redirectAttributes.addFlashAttribute("msg", mno);
        return "redirect:/movie/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        log.info("pageRequestDTO : " + pageRequestDTO);
        model.addAttribute("result", movieService.getList(pageRequestDTO));
    }

    @GetMapping({"read", "/modify"})
    public void read(long mno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO
    ,Model model) {
        log.info("read mno : " + mno);
        MovieDTO movieDTO = movieService.getMovie(mno);

        for(MovieImageDTO dto : movieDTO.getImageDTOList()) {
            log.info("read ############### 1");
            log.info(dto.getThumbnailURL());
            log.info("#######################1");
        }

        model.addAttribute("dto", movieDTO);
    }
}
