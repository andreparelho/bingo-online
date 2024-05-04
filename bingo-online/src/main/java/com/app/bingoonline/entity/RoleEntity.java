package com.app.bingoonline.entity;

import com.app.bingoonline.enums.RoleName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Table(name = "roles")
@Entity(name = "Role")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName name;

    public Long getId() {
        return id;
    }

    public RoleName getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}
