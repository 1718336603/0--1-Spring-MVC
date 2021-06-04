package com.mvc.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @DESC
 * @AUTHOR zzf
 * @DATA 2021/6/3 14:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PathInfo {
    /**
     * http请求方法
     */
    private String httpMethod;

    /**
     * http请求路径
     */
    private String httpPath;
}
