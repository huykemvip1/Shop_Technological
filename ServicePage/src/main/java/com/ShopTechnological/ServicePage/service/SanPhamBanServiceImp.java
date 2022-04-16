package com.ShopTechnological.ServicePage.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ShopTechnological.ServicePage.domain.SanPhamBan;
import com.ShopTechnological.ServicePage.reponsitory.SanPhamBanRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Service
@AllArgsConstructor
@Slf4j
public class SanPhamBanServiceImp implements SanPhamBanService{
	private final SanPhamBanRepository sanPhamBanRepository;
	private final SanPhamService sanPhamService;
	@Override
	public void sanPhamDaBan(long id_sanpham, long id_user,String tennguoiban,int soluong) {
		try {
			sanPhamBanRepository.themDuLieu(id_sanpham, id_user, LocalDateTime.now(),tennguoiban);
			sanPhamService.capNhatSoLuong(id_sanpham,soluong);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
	@Override
	public List<SanPhamBan> toanBoSanPhamDaBan() {
		return sanPhamBanRepository.findAll();
	}
	@Override
	public void xoaTatCa() {
		if (sanPhamBanRepository.findAll().size() >= 1) {
			sanPhamBanRepository.deleteAll();
		}
	}
	@Override
	public void xoaTheoId(long id) {
		if (sanPhamBanRepository.findById(id).isPresent()) {
			sanPhamBanRepository.deleteById(id);
		}
	}
	@Override
	public int soLuongMuaTheoTen(String ten) {
		
		if (sanPhamBanRepository.checkTen(ten).size() >=1) {
			return sanPhamBanRepository.soluong(ten);
		}
		return 0;
	}
	
}
