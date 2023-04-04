package com.electronic_project.dto.cart;

public class UpdateQuantityCartDto {
    private Integer id;
    private String name;
    private Double price;
    private Integer quantity;
    private String ulr;
    private Double total;

    public UpdateQuantityCartDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUlr() {
        return ulr;
    }

    public void setUlr(String ulr) {
        this.ulr = ulr;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
