package com.wizzstudio.substitute.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wizzstudio.substitute.enums.Gender;
import com.wizzstudio.substitute.enums.Role;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "user")
@Data
@NamedQuery(name = "getAllApprentice", query = "select u from user u where" +
        " u.masterId =: account")
public class User implements Serializable {

    //用户Id,由26位大小写字母+10位数组随机生成
    @Id
    private String id;

    //用户微信openid
    @NotNull
    private String openid;

    //师傅Id
    private String masterId;

    //用户昵称，微信名
    @NotNull
    private String userName;

    //用户头像url
    @NotNull
    private String avatar;

    //用户性别，男：”MALE”,女：”FAMALE”,未知：”NO_LIMITED”
    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;

    /**
     * 用户级别: 普通用户：”ROLE_USER”,一级管理员:”ROLE_ADMIN_1” ,二级管理员:”ROLE_ADMIN_2”
     */
    @Enumerated(EnumType.STRING)
    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Role role;

    //用户真实姓名
    private String trueName;

    //电话号码
    private Long phone;

    //学校Id
    private Integer schoolId;

    /**
     * 余额
     */
    @NotNull
    private BigDecimal balance;

    /**
     * 累计收入
     */
    @NotNull
    private BigDecimal allIncome;

    //师傅收入：当推荐人获得的收入
    private BigDecimal masterIncome;

    @Column(insertable = false,updatable = false)
    private Date createTime;

    @Column(updatable = false,insertable = false)
    private Date updateTime;

    public User() {
    }

    private User(Builder builder) {
        setId(builder.id);
        setOpenid(builder.openid);
        setMasterId(builder.masterId);
        setUserName(builder.userName);
        setTrueName(builder.trueName);
        setPhone(builder.phone);
        setAvatar(builder.avatar);
        setSchoolId(builder.schoolId);
        setGender(builder.gender);
        setRole(builder.role);
        setBalance(builder.balance);
        setAllIncome(builder.allIncome);
        setMasterIncome(builder.masterIncome);
        setCreateTime(builder.createTime);
        setUpdateTime(builder.updateTime);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(User copy) {
        Builder builder = new Builder();
        builder.id = copy.getId();
        builder.openid = copy.getOpenid();
        builder.masterId = copy.getMasterId();
        builder.userName = copy.getUserName();
        builder.trueName = copy.getTrueName();
        builder.phone = copy.getPhone();
        builder.avatar = copy.getAvatar();
        builder.schoolId = copy.getSchoolId();
        builder.gender = copy.getGender();
        builder.role = copy.getRole();
        builder.balance = copy.getBalance();
        builder.allIncome = copy.getAllIncome();
        builder.masterIncome = copy.getMasterIncome();
        builder.createTime = copy.getCreateTime();
        builder.updateTime = copy.getUpdateTime();
        return builder;
    }


    public static final class Builder {
        private String id;
        private @NotNull String openid;
        private String masterId;
        private @NotNull String userName;
        private String trueName;
        private Long phone;
        private @NotNull String avatar;
        private Integer schoolId;
        private @NotNull Gender gender;
        private @NotNull Role role;
        private @NotNull BigDecimal balance;
        private @NotNull BigDecimal allIncome;
        private BigDecimal masterIncome;
        private Date createTime;
        private Date updateTime;

        private Builder() {
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setOpenid(@NotNull String openid) {
            this.openid = openid;
            return this;
        }

        public Builder setMasterId(String masterId) {
            this.masterId = masterId;
            return this;
        }

        public Builder setUserName(@NotNull String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setTrueName(String trueName) {
            this.trueName = trueName;
            return this;
        }

        public Builder setPhone(Long phone) {
            this.phone = phone;
            return this;
        }

        public Builder setAvatar(@NotNull String avatar) {
            this.avatar = avatar;
            return this;
        }

        public Builder setSchool(Integer schoolId) {
            this.schoolId = schoolId;
            return this;
        }

        public Builder setGender(@NotNull Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder setRole(@NotNull Role role) {
            this.role = role;
            return this;
        }

        public Builder setBalance(@NotNull BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public Builder setAllIncome(@NotNull BigDecimal allIncome) {
            this.allIncome = allIncome;
            return this;
        }

        public Builder setMasterIncome(BigDecimal masterIncome) {
            this.masterIncome = masterIncome;
            return this;
        }

        public Builder setCreateTime(Date createTime) {
            this.createTime = createTime;
            return this;
        }

        public Builder setUpdateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}