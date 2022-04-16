package com.ShopTechnological.ServicePage.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "sanphamban")
public class SanPhamBan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private LocalDateTime ngayban;
	private String tennguoiban;
	@ManyToOne
	@JoinColumn(name = "id_sanpham")
	private SanPham sanPham;
}