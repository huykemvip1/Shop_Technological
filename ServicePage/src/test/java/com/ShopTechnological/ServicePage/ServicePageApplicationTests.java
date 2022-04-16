package com.ShopTechnological.ServicePage;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ShopTechnological.ServicePage.domain.SanPham;
import com.ShopTechnological.ServicePage.domain.SanPhamBan;
import com.ShopTechnological.ServicePage.service.SanPhamBanService;
import com.ShopTechnological.ServicePage.service.SanPhamService;

@SpringBootTest
class ServicePageApplicationTests {
	@Autowired
	private SanPhamService sanPhamService;
	@Autowired
	private SanPhamBanService sanPhamBanService;
	@Test
	@Disabled
	void checkSanPhamHot() {
		List<SanPham> sanPhams=sanPhamService.sanPhamHot(); 
		assertThat(sanPhams).isNull();
	}
	@Test
	@Disabled
	void checkSanPhamMoi() {
		List<SanPham> sanPhams=sanPhamService.sanPhamMoi();
		assertThat(sanPhams).isNull();
	}
	@Test
	void checkChonSLKhachHangMua() {
		int soluong=sanPhamBanService.soLuongMuaTheoTen("huyhomhinh");
		assertThat(soluong).isEqualByComparingTo(0);
	}
}
