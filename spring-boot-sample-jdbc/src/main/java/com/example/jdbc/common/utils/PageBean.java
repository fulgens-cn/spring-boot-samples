package com.example.jdbc.common.utils;

import com.example.jdbc.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageBean<T> {

    /**
     * 当前页数
     */
    private Integer page;

    /**
     * 每页显示的记录数
     */
    private Integer pageSize;

    /**
     * 总记录数
     */
    private Integer totalCount;

    /**
     * 总页数
     */
    private Integer totalPage;

    /**
     * 每页显示数据的集合
     */
    private List<T> list;

    public static <T> PageBean<T> newInstance(Integer page, Integer pageSize, Integer totalCount, List<T> list) {
        Integer totalPage = totalCount / pageSize + (totalCount % pageSize > 0 ? 1 : 0);
        return PageBean.<T>builder().page(page).pageSize(pageSize).totalCount(totalCount).list(list).totalPage(totalPage).build();
    }
}
