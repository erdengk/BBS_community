package com.dk.bbs.dto;

import com.dk.bbs.model.User;
import lombok.Data;

/**
 * @author : dk
 * @date : 2019/7/10 21:34
 * @description :
 */

@Data
public class QusetionDTO {
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
    private User user;
}
