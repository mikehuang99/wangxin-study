package com.wangxin.langshu.business.edu.vo.course.old.service.common.dto.auth;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 章节信息-审核
 *
 *  
 */
@Data
@Accessors(chain = true)
public class AuthChapterApplyViewBO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<AuthChapterApplyDTO> userChapterApplyListDTO;
}
