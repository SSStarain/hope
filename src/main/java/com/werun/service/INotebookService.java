package com.werun.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.werun.entity.NoteBook;


import java.util.List;

public interface INotebookService extends IService<NoteBook> {
    Boolean modifyState(Integer id,Integer state);
    Boolean modifyState(List<Integer> ids,Integer state);
    Boolean modifyAll(Integer id,NoteBook noteBook);
    Boolean saveType(String notebookType);
    List<NoteBook> selectByTitle(String notebookTitle);
    List<String> showType();
    List<NoteBook> selectAll();
    NoteBook selectById(Integer id);
    Integer sumNoteName();
    Integer sumWords();

}
