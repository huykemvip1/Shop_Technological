package com.ShopTechnological.ServicePage.service;

import java.util.List;

import com.ShopTechnological.ServicePage.domain.SanPham;

public interface SanPhamService {
	List<SanPham> toanBoSanPham();
	SanPham chonSanPham(String ten);
	void capNhatSoLuong(long id,int soluong);
	List<SanPham> sanPhamHot();
	List<SanPham> sanPhamMoi();
	// ----- manager moi duoc truy cap
	void themSanPham(SanPham sanPham);
	void xoaSanPham(long id);
}
