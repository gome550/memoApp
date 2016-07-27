/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.javaee.memoapp.bean;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import sample.javaee.memoapp.ejb.MemoFacade;
import sample.javaee.memoapp.entity.Memo;

/**
 *
 * @author 07930
 */
@Named(value = "memoBean")
@ViewScoped
public class MemoBean implements Serializable {

    /**
     * Creates a new instance of MemoBean
     */
    public MemoBean() {
    }
    
    /**
     * 入力されたメモ
     */
    @Getter
    @Setter
    private String memo;

    /**
     * 一覧表示するメモのリスト
     */
    @Getter
    @Setter
    private List<Memo> memoList;
    
    /**
     * メモのデータベースアクセス用EJB
     */
    @Inject
    private MemoFacade memoFacade;
    
    /**
     * 画面初期化
     */
    @PostConstruct
    public void init() {
	getAllMemo();
    }
    
    /**
     * メモの新規登録
     */
    public void createMemo() {
	if (memo.length() > 0) {
	    Memo newMemo = new Memo();
	    newMemo.setMemo(memo);
	    memoFacade.create(newMemo);
	    getAllMemo();
	}
    }
    
    /**
     * メモの更新
     */
    public String updateMemo(Memo editMemo) {
	Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
	flash.put("editMemoId", editMemo.getId());
	return "memoEdit.xhtml";
    }
    
    /**
     * メモの削除
     */
    public void deleteMemo(Memo delMemo) {
	memoFacade.remove(delMemo);
	getAllMemo();
    }
    
    /**
     * 登録されたすべてのメモを取得
     */
    private void getAllMemo() {
	memoList = memoFacade.findAll();
    }
    
}
