package com.electronic_project.dto.image;

public class AddImageDto {
    private String url;
    private Integer productId;

    public AddImageDto() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
