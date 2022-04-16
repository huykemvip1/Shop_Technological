package com.ShopTechnological.ServicePage.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ShopTechnological.ServicePage.domain.SanPham;
import com.ShopTechnological.ServicePage.service.SanPhamBanService;
import com.ShopTechnological.ServicePage.service.SanPhamService;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@RestController
@RequestMapping("/ShopTechnological/no")
public class NoAuthenticion {
	private final SanPhamService sanPhamService;
	private final SanPhamBanService sanPhamBanService;
	
	@GetMapping("/TBSP") // ----- toan bo san pham
	public ResponseEntity<List<SanPham>> toanBoSanPham(){
		List<SanPham> sanPhams=sanPhamService.toanBoSanPham();
		return ResponseEntity.ok(sanPhams);
	}
	
	@GetMapping("/SPH") // ----- San Pham Hot
	public ResponseEntity<List<SanPham>> sanPhamHot(){
		List<SanPham> sanPhams=sanPhamService.sanPhamHot();
		return ResponseEntity.ok(sanPhams);
	}
	
	@GetMapping("/SPM") // ----- San Pham Moi
	public ResponseEntity<List<SanPham>> sanPhamMoi(){
		List<SanPham> sanPhams=sanPhamService.sanPhamMoi();
		return ResponseEntity.ok(sanPhams);
	}
	@GetMapping("/{ten}") //---- Chi tiet san pham
	public ResponseEntity<SanPham> chiTietSanPham(@PathVariable String ten){
		SanPham sanPham=sanPhamService.chonSanPham(ten);
		return ResponseEntity.ok(sanPham);
	}
	@PostMapping(value="/SPB",
			consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}) // ---------- Them San Pham Ban
	@ResponseBody
	public ResponseEntity<?> themSanPhamBan(@RequestBody ThongTinSanPhamBan thongTinSanPhamBan){
		sanPhamBanService.sanPhamDaBan(thongTinSanPhamBan.getId_sanpham(),
				thongTinSanPhamBan.getId_user(),
				thongTinSanPhamBan.getTennguoiban(),
				thongTinSanPhamBan.getSoluong());
		return ResponseEntity.ok().build();
	}
	@Data
	static class ThongTinSanPhamBan{
		private long id_sanpham;
		private long id_user;
		private String tennguoiban;
		private int soluong;
	}
	
}
