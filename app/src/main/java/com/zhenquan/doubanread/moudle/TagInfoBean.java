package com.zhenquan.doubanread.moudle;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ry on 2018/1/24.
 */
@Entity
public class TagInfoBean {
    @Id(autoincrement = true)
    private Long id;
    private int parent_id;
    private String name;
    private String create_time;
    private String update_time;


    @Generated(hash = 401339608)
    public TagInfoBean(Long id, int parent_id, String name, String create_time, String update_time) {
        this.id = id;
        this.parent_id = parent_id;
        this.name = name;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    @Generated(hash = 1692947207)
    public TagInfoBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
