package ua.cn.stu.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "producttable")
public class Product {

    //------------------------------------------------------ constructors
    public Product() {
    }

    public Product(String name, String description) {
        this.name = name;
        this.description = description;
    }

    //------------------------------------------------------ columns
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "productname")
    private String name;

    @Column(name = "productdescription")
    private String description;


    //------------------------------------------------------ methods (getters setters)
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}