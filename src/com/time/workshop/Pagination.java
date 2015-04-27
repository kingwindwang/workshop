package com.time.workshop;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页实体
 * Created by calvin on 2014/7/25.
 */
public class Pagination<T> {

    public List<T> list = new ArrayList<T>();

    /**
     * 当前页码
      */
    public int page = BaseConstant.PAGE;
    /**
     * 每页条数
     */
    public int num = BaseConstant.PAGE_NUM;

    /**
     * 重置当前页码和每页条�?
     */
    public void reset() {
        page = BaseConstant.PAGE;
        num = BaseConstant.PAGE_NUM;
    }
}
