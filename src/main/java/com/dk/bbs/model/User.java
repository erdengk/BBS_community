package com.dk.bbs.model;

import lombok.Data;

/**
 * @author : dk
 * @date : 2019/7/8 16:31
 * @description :
 */
@Data
public class User {
    private  Integer id;
    private  String name;
    private  String account_Id;
    private  String token;
    private  Long gmt_create;
    private  Long gmt_modeified;
}
