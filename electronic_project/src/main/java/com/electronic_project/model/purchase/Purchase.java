package com.electronic_project.model.purchase;

import com.electronic_project.model.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    @Column(columnDefinition = "date")
    private String orderDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "purchase")
    @JsonBackReference
    private List<PurchaseDetail> purchaseDetails;

    @ManyToOne
    @JoinColumn(name = "purchase_status_id", nullable = false, referencedColumnName = "id")
    private PurchaseStatus purchaseStatus;

    public Purchase() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PurchaseDetail> getPurchaseDetails() {
        return purchaseDetails;
    }

    public void setPurchaseDetails(List<PurchaseDetail> purchaseDetails) {
        this.purchaseDetails = purchaseDetails;
    }

    public PurchaseStatus getPurchaseStatus() {
        return purchaseStatus;
    }

    public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
        this.purchaseStatus = purchaseStatus;
    }
}
