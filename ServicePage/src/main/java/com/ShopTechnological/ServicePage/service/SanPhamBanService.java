package com.ShopTechnological.ServicePage.service;

import java.util.List;

import com.ShopTechnological.ServicePage.domain.SanPhamBan;


public interface SanPhamBanService {
	void sanPhamDaBan(long id_sanpham,long id_user,String tennguoiban,int soluong);
	// ------ manager
	List<SanPhamBan> toanBoSanPhamDaBan();
	void xoaTatCa();
	void xoaTheoId(long id);
	int soLuongMuaTheoTen(String ten);
}
