package com.electronic_project.dto.user;

public class UserAvatarDto {
    private Integer userId;
    private String avatar;

    public UserAvatarDto() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
