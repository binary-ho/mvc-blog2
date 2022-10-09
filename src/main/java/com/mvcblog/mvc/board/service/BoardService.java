package com.mvcblog.mvc.board.service;

import com.mvcblog.mvc.board.dao.BoardDao;
import com.mvcblog.mvc.board.domain.BoardVO;

import java.util.List;

public interface BoardService {
    public abstract List<BoardVO> list();
    public abstract int delete(BoardVO boardVo);
    public abstract int edit(BoardVO boardVO);
    public abstract void write(BoardVO boardVO);
    public abstract BoardVO read(int seq);
}
