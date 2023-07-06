package com.werun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.werun.dao.NoteBookDao;
import com.werun.dao.NotebookTypeDao;
import com.werun.entity.NoteBook;
import com.werun.entity.NotebookType;
import com.werun.entity.R;
import com.werun.service.INotebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotebookServiceImpl extends ServiceImpl<NoteBookDao, NoteBook> implements INotebookService {

    @Autowired
    private NoteBookDao noteBookDao;
    @Autowired
    private NotebookTypeDao notebookTypeDao;


    @Override
    public Boolean modifyState(Integer id,Integer state) {
        UpdateWrapper<NoteBook> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("id",id).set("notebook_state",state);
        return noteBookDao.update(null,updateWrapper)>0;
    }

    @Override
    public Boolean modifyState(List<Integer> ids, Integer state) {
        for (Integer id:ids) {
            UpdateWrapper<NoteBook> updateWrapper=new UpdateWrapper<>();
            updateWrapper.eq("id",id).set("notebook_state",state);
            noteBookDao.update(null,updateWrapper);
        }
        return true;
    }

    @Override
    public Boolean modifyAll(Integer id,NoteBook noteBook) {
//        UpdateWrapper<NoteBook> updateWrapper=new UpdateWrapper<>();
//        updateWrapper.eq("id",id).setEntity(noteBook);
        UpdateWrapper<NoteBook> updateWrapper=new UpdateWrapper<>();
        updateWrapper.eq("id",id).set("notebook_state",noteBook.getNotebookState()).set("notebook_title",noteBook.getNotebookTitle()).set("notebook_type",noteBook.getNotebookType()).set("notebook_content",noteBook.getNotebookContent()).set("notebook_description",noteBook.getNotebookDescription()).set("notebook_edited_time",noteBook.getNotebookEditedTime());
        return noteBookDao.update(null,updateWrapper)>0;

    }

    @Override
    public Boolean saveType(String notebookType) {
        NotebookType oneType=new NotebookType(notebookType);
        return notebookTypeDao.insert(oneType)>0;
    }

    @Override
    public List<NoteBook> selectByTitle(String notebookTitle) {
        QueryWrapper<NoteBook> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("notebook_title",notebookTitle);
        return noteBookDao.selectList(queryWrapper);
    }

    @Override
    public List<String> showType() {
        return notebookTypeDao.selectAllByNotebookTypeStrings();
    }


    @Override
    public List<NoteBook> selectAll() {
        List<String> types = notebookTypeDao.selectAllByNotebookTypeStrings();
        System.out.println(types);
        List<NoteBook> noteBooks =new ArrayList<>();
        for(String x:types)
        {
            QueryWrapper<NoteBook> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("notebook_type",x);
            noteBooks.addAll(noteBookDao.selectList(queryWrapper));
        }
        return noteBooks;
    }

    @Override
    public NoteBook selectById(Integer id) {
        return noteBookDao.selectById(id);
    }

    @Override
    public Integer sumNoteName() {
        QueryWrapper<NoteBook> queryWrapper=new QueryWrapper<>();
        return noteBookDao.selectCount(queryWrapper);
    }

    @Override
    public Integer sumWords() {
        Integer sum = 0;
        List<Integer> words= noteBookDao.selectWords();
        for(Integer x:words) {
            sum += x;
        }
        return sum;
    }

}
