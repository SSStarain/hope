package com.werun.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@TableName("notebook")
public class NoteBook implements Serializable {
    private Integer id;
    private String notebookTitle;
    private String notebookType;
    private Integer notebookState;
    @TableField(fill = FieldFill.INSERT)
    private Date notebookCreatedTime;
    @TableField(fill = FieldFill.INSERT)
    private Date notebookEditedTime;
    private String notebookContent;
    private String notebookDescription;

    public NoteBook(NoteBook noteBook) {
        if(noteBook !=null ){
            this.id =noteBook.getId();
            this.notebookTitle =noteBook.getNotebookTitle();
            this.notebookType = noteBook.getNotebookType();
            this.notebookState = noteBook.getNotebookState();
            this.notebookCreatedTime = noteBook.getNotebookCreatedTime();
            this.notebookContent = noteBook.getNotebookTitle();
            this.notebookDescription = noteBook.getNotebookDescription();
        }
    }
}
