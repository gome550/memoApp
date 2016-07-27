/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.javaee.memoapp.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import lombok.Getter;
import sample.javaee.memoapp.ejb.MemoFacade;
import sample.javaee.memoapp.entity.Memo;

/**
 *
 * @author 07930
 */
@Named(value = "memoEditionBean")
@ViewScoped
public class MemoEditionBean implements Serializable {

    /**
     * Creates a new instance of MemoEditionBean
     */
    public MemoEditionBean() {
    }
    
    /**
     * 編集するメモのエンティティ
     */
    @Getter
    private Memo editMemo;
    
    /**
     * メモのデータベースアクセス用EJB
     */
    @Inject
    private MemoFacade memoFacade;
    
    /**
     * 画面初期化処理
     */
    @PostConstruct
    public void init() {
	Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
	Integer key = (Integer)flash.get("editMemo");
	editMemo = memoFacade.find(key);
    }
    
    /**
     * メモの更新
     */
    public void updateMemo() {
	memoFacade.edit(editMemo);
    }
    
}
