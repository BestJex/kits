package com.ptyt.haiguan.kits.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yq
 * @date: 2019/11/15 09:18
 * @description:
 */
@Data
public class FileVo {

    private int type;
    private String name;
    private List dirList = new ArrayList();
    private String path;
    private String modifyTime;
    private long length;
}
