package com.wizzstudio.substitute.dto.wx;

import lombok.Data;

import java.io.Serializable;

@Data
public class WxInfo implements Serializable {

    private String code;

    private String encryptedData;

    private String iv;


}
