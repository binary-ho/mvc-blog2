package com.mvcblog.mvc.board.controller;

import com.mvcblog.mvc.board.domain.BoardVO;
import com.mvcblog.mvc.board.service.BoardService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@SessionAttributes("boardVO")
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

    // 수정
    @RequestMapping(value = "/board/edit/{seq}", method = RequestMethod.GET)
    public String edit(@PathVariable int seq, Model model) {
        BoardVO boardVO = boardService.read(seq);
        model.addAttribute("boardVO", boardVO);
        return "/board/edit";
    }

    @RequestMapping(value = "/board/edit/{seq}", method = RequestMethod.POST)
    public String edit(@Valid @ModelAttribute BoardVO boardVO, BindingResult result, int pwd,
                       SessionStatus sessionStatus, Model model) {
        if (result.hasErrors()) {
            return "/board/edit";
        } else {
            if (boardVO.getPassword() == pwd) {
                boardService.edit(boardVO);
                sessionStatus.setComplete();
                return "redirect:/board/list";
            }
            model.addAttribute("msg", "비밀번호 틀림.");
            return "/board/edit";
        }
    }

    // delete
    @RequestMapping(value = "/board/delete/{seq}", method = RequestMethod.GET)
    public String delete(@PathVariable int seq, Model model) {
        model.addAttribute("seq", seq);
        return "/board/delete";
    }

    @RequestMapping(value = "/board/delete", method = RequestMethod.POST)
    public String delete(int seq, int pwd, Model model) {
        int rowCount;
        BoardVO boardVO = new BoardVO();
        boardVO.setSeq(seq);
        boardVO.setPassword(pwd);

        rowCount = boardService.delete(boardVO);

        if (rowCount == 0) {
            model.addAttribute("seq", seq);
            model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
            return "/board/delete";
        } else {
            return "redirect:/board/list";
        }
    }
}