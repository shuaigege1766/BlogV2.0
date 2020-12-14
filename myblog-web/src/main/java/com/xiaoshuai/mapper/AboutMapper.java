package com.xiaoshuai.mapper;

import com.xiaoshuai.dto.AboutDTO;
import org.apache.ibatis.annotations.Param;

/**
 * @author 傅帅  QQ:1766281636
 * @creat 2020- 12-01-上午 10:00
 **/
public interface AboutMapper {

    AboutDTO getAbout();
     int updateAbout(@Param("aboutContent") String aboutContent);
}
