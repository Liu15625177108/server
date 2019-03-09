package com.example.server.security.core.validatorcode.basecode;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName ValidatorCode
 * @Author:Jerry.Liu;
 * @Description://TODO
 * @Package com.example.conferencedemo.security.core.validatorcode.basecode
 * @Date 2018/9/19 21:03
 */
public class ValidatorCode implements Serializable {

        /** 验证框中验证码的数值*/
        private String code;

        /** 验证码的过期时间*/
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        private LocalDateTime expireTime;


        /**construtors*/

        public ValidatorCode(String code, LocalDateTime expireTime) {
            this.code = code;
            this.expireTime = expireTime;
        }

        public ValidatorCode(String code, int expireSecond) {
            this.code = code;
            this.expireTime = LocalDateTime.now().plusSeconds(expireSecond);
        }
        public  ValidatorCode(){}


        /**getter setter 方法 */

        public String getCode() {
            return code;
        }
        public void setCode(String code) {
            this.code = code;
        }

        public LocalDateTime getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(LocalDateTime expireTime) {
            this.expireTime = expireTime;
        }

        public BufferedImage showImage() {
            return null;
        }

    }
