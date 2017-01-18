package com.tw.tradeaway.domain;


import javax.persistence.*;

@Entity
@Table(name="category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="active")
    private boolean active = true;

    public Category() {
    }

    public Category(Long id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
