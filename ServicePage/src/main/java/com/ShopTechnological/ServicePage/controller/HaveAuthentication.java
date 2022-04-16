package com.ShopTechnological.ServicePage.controller;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ShopTechnological.ServicePage.domain.SanPham;
import com.ShopTechnological.ServicePage.domain.SanPhamBan;
import com.ShopTechnological.ServicePage.service.SanPhamBanService;
import com.ShopTechnological.ServicePage.service.SanPhamService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/ShopTechnological/have")
public class HaveAuthentication {
	private final SanPhamService sanPhamService;
	private final SanPhamBanService sanPhamBanService;
	
	@DeleteMapping("/XSP/{id}") // ---- Xoa San Pham
	public ResponseEntity<?> xoaSanPham(@Param(value = "id") long id){
		sanPhamService.xoaSanPham(id);
		return ResponseEntity.ok().build();
	}
	//--------------------------- San Pham Ban
	
	@GetMapping("/TBSPB") // ---- Toan bo san pham ban
	public ResponseEntity<List<SanPhamBan>> toanBoSanPhamBan(){
		List<SanPhamBan> sanPhamBans=sanPhamBanService.toanBoSanPhamDaBan();
		return ResponseEntity.ok(sanPhamBans);
	}
	
	@PostMapping(value="/TSP",
			consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}) // ------ Them San Pham
	@ResponseBody
	public ResponseEntity<?> themSanPham(@RequestBody SanPham sanPham){
		sanPhamService.themSanPham(sanPham);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/XTC") // ------- Xoa Tat Ca Thong Tin San Pham Ban
	public ResponseEntity<?> xoaTatCaSanPhamBan(){
		sanPhamBanService.xoaTatCa();
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/XSPB/{id}") // ------- Xoa Tung San Pham Ban
	public ResponseEntity<?> xoaChiTungSanPhamBan(@PathVariable("id") long id){
		sanPhamBanService.xoaTheoId(id);
		return ResponseEntity.ok().build();
	}
	
}
