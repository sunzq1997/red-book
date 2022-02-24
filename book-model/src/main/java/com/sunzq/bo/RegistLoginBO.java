package com.sunzq.bo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author sunzq
 */
@Data
public class RegistLoginBO {
    @NotBlank(message = "手机号不能为空")
    @Length(min = 11, max = 11, message = "手机号格式不正确")
    private String mobile;
    @NotBlank(message = "验证码不能为空")
    private String smsCode;
}
