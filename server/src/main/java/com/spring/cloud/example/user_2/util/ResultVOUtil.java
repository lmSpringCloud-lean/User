package com.spring.cloud.example.user_2.util;

import com.spring.cloud.example.user_2.constant.Constants;
import com.spring.cloud.example.user_2.vo.ResultVO;
import org.springframework.util.StringUtils;

public class ResultVOUtil {
    public static ResultVO success(Object data) {
        ResultVO result = new ResultVO();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(data);
        return result;
    }

    public static ResultVO success() {
        ResultVO result = new ResultVO();
        result.setCode(0);
        result.setMsg("成功");
        return result;
    }

    public static ResultVO error(Integer code, String Msg) {
        ResultVO result = new ResultVO();
        result.setCode(code);
        if (!StringUtils.isEmpty(Msg)) {
            result.setMsg(Msg);
        } else {
            result.setMsg("失败");
        }
        return result;
    }

    public static ResultVO error(Constants.Result result) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(result.getCode());
        resultVO.setMsg(result.getMsg());
        return resultVO;
    }
}
