package com.werun.controller;

import com.werun.entity.Account;
import com.werun.entity.NoteBook;
import com.werun.entity.R;
import com.werun.service.INotebookService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/notebooks")
public class NoteController {
    @Autowired
    private INotebookService iNotebookService;

    /*
     *1.2.1.1
     * 新增笔记分类
     */
    @PostMapping("/saveType/{notebookType}")
    public R saveNotebookType(@PathVariable String notebookType){
        return new R(iNotebookService.saveType(notebookType));
    }
    /*
     *1.2.1.2
     * 展示笔记分类
     */
    @GetMapping("/showType")
    public R showNotebookType(){
        return new R(true,iNotebookService.showType());
    }
    /*
     *1.2.2
     * 新增笔记
     */
    @PostMapping("/save")
    public R save (@RequestBody NoteBook noteBook){
        return new R(iNotebookService.save(noteBook));
    }
    /*
     *1.2.2
     * 查看笔记详情
     */
    @GetMapping("/showNote/{id}")
    public R showOneNote(@PathVariable Integer id){
        return new R(true,iNotebookService.selectById(id));
    }
    /*
     *1.2.3
     * 删除笔记
     */
    @DeleteMapping("/delete/{id}")
    public R delete(@PathVariable Integer id){
        return new R(iNotebookService.removeById(id));
    }
    /*
     *1.2.3.1
     * 批量删除笔记
     */
    @DeleteMapping("/deleteAll")
    public R deleteAll(@RequestParam(value = "id") List<Integer> id){
        return new R(iNotebookService.removeByIds(id));
    }
    /*
     *1.2.4.1
     * 修改笔记状态
     */
    @PutMapping("/modifyState/{id}/{state}")
    public R modifyState(@PathVariable Integer id,@PathVariable Integer state){
        return new R(iNotebookService.modifyState(id,state));
    }
    /*
     *1.2.4.2
     * 批量修改笔记状态
     */
    @PutMapping("/modifyState/{state}")
    public R modifyState(@RequestParam(value = "id") List<Integer> id,@PathVariable Integer state){
        return new R(iNotebookService.modifyState(id,state));
    }
    /*
     *1.2.4.3
     * 编辑笔记
     */
    @PutMapping("/modifyAll/{id}")
    public R modifyAll(@PathVariable Integer id,@RequestBody NoteBook noteBook){
        return new R(iNotebookService.modifyAll(id,noteBook));
    }
    /*
     *1.2.5
     * 主页：查看所有笔记
     */
    @GetMapping("/showAllNote")
    public R showAllNote(){
        return new R(true,iNotebookService.selectAll());
    }

    /*
     *1.2.6
     * 模糊查询，标题搜索
     */
    @GetMapping("/getNotebookByTitle/{notebookTitle}")
    public R getNotebookByTitle(@PathVariable String notebookTitle){
        return new R(true,iNotebookService.selectByTitle(notebookTitle));
    }


}
