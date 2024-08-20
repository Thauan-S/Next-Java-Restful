package com.tropical.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_roles")
public class Role implements  Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Long roleId;
	@Column
	private String name;

	public enum Values {
		 ADMIN(1L),BASIC(2L),EMPRESA(3L);

		long roleId;

		Values(long roleId) {
			this.roleId = roleId;
		}
		public Long getRoleId() {
			return roleId;
		}
	}

	

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", name=" + name + "]";
	}
	
}
