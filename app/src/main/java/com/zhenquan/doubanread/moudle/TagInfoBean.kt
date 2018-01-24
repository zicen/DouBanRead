package com.zhenquan.doubanread.moudle

import org.greenrobot.greendao.annotation.Entity
import org.greenrobot.greendao.annotation.Id
import org.greenrobot.greendao.annotation.Generated
import org.greenrobot.greendao.annotation.Unique

/**
 * Created by ry on 2018/1/24.
 */
@Entity
class TagInfoBean {
    @Id(autoincrement = true)
    var id: Long? = null
    var parent_id: Int = 0
    @Unique
    var name: String? = null
    var create_time: String? = null
    var update_time: String? = null


    @Generated(hash = 401339608)
    constructor(id: Long?, parent_id: Int, name: String, create_time: String, update_time: String) {
        this.id = id
        this.parent_id = parent_id
        this.name = name
        this.create_time = create_time
        this.update_time = update_time
    }

    @Generated(hash = 1692947207)
    constructor() {
    }
}
