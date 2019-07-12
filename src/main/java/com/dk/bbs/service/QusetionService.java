package com.dk.bbs.service;

import com.dk.bbs.dto.QusetionDTO;
import com.dk.bbs.mapper.QusetionMapper;
import com.dk.bbs.mapper.UserMapper;
import com.dk.bbs.model.Qusetion;
import com.dk.bbs.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : dk
 * @date : 2019/7/10 21:46
 * @description :服务层   qusetion中的属性与qusetionDTO 中的属性赋值
 */
@Service
public class QusetionService {

    @Autowired
    QusetionMapper qusetionMapper;
    @Autowired
    UserMapper userMapper;

    public  List<QusetionDTO> list() {
        List<Qusetion> qusetionList = qusetionMapper.list();
        List<QusetionDTO> qusetionDTOS = new ArrayList<QusetionDTO>();
        for (Qusetion qusetion : qusetionList) {
            User user = userMapper.findId(qusetion.getCreator());
            QusetionDTO qusetionDTO =new QusetionDTO();
            BeanUtils.copyProperties(qusetion,qusetionDTO);
            //把前面对象的属性值赋值给后面的属性值

            qusetionDTO.setUser(user);
            qusetionDTOS.add(qusetionDTO);
    }
        return  qusetionDTOS;
    }
}
