package com.mvcblog.mvc.board.controller;

import com.mvcblog.mvc.board.domain.BoardVO;
import com.mvcblog.mvc.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    /*
    @RequestMapping(value = "/board/list")
    @ResponseBody   // 諛섑솚 臾몄옄�뿴�쓣 洹몃�濡� �쎒 釉뚮씪�슦���뿉 �쟾�떖
    public String list() {
        return boardService.list().toString();
    }
    */

    // 글 목록
    @RequestMapping(value = "/board/list")
    public String list(Model model) {
    	model.addAttribute("boardList", boardService.list());
        return "/board/list";
        // main/webapp/WEB-INF/views/board/list.jsp
    }

    // 글 읽기
    @RequestMapping(value = "/board/read/{seq}")
    public String read(Model model, @PathVariable int seq) {
        model.addAttribute("boardVO", boardService.read(seq));
        return "/board/read";
    }

    // 새 글 쓰기
    @RequestMapping(value = "/board/write", method= RequestMethod.GET)
    public String write(Model model) {
        model.addAttribute("boardVO", new BoardVO());
        return "/board/write";
    }

    // 새 글 DB에 저장
    @RequestMapping(value = "/board/write", method= RequestMethod.POST)
    public String write(@Valid BoardVO boardVO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "/board/write";
        } else {
            boardService.write(boardVO);
            return "redirect:/board/list";
        }
    }
}