package com.ShopTechnological.ServicePage.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="sanpham")
public class SanPham {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String ten;
	private String thongtin;
	private BigDecimal gia;
	private int soluongnhap;
	private int soluongconlai;
	private LocalDateTime thoigiannhap;
}
