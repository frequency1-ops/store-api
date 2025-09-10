package com.tech2tech.store.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "email")
    private String email;
    
    @Column(nullable = false, name = "password")
    private String password;

    @OneToMany(mappedBy="user")
    private List<Address> addresses = new ArrayList<>();

     public void addAddresses(Address address) {
        addresses.add(address);
        address.setUser(this);
    }

    public void removeAddresses(Address address) {
        addresses.remove(address);
        address.setUser(null);
    }

    @ManyToMany
    @JoinTable(name = "user_tags", 
    joinColumns= @JoinColumn(name = "user_id"), 
    inverseJoinColumns=@JoinColumn(name="tag_id"))
    private Set<Tag> tags = new HashSet<>();

    public void addTag(String tagName){
        var tag = new Tag();
        tags.add(tag);
        tag.getUsers().add(this);
    }
    public void removeTag(Tag tag){
        tags.remove(tag);
    }

    @OneToOne(mappedBy="user")
    private Profile profile;

}
