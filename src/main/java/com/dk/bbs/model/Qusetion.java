package com.dk.bbs.model;

import lombok.Data;

/**
 * @author : dk
 * @date : 2019/7/9 19:33
 * @description :
 */
@Data
public class Qusetion {
    private Integer id;
    private String title;
    private String description;
    private Long gmt_create;
    private Long gmt_modified;
    private Integer creator;
    private Integer comment_count;
    private Integer view_count;
    private Integer like_count;
    private String tag;
}
