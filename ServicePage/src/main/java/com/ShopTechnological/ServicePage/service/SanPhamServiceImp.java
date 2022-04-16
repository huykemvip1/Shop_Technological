package com.ShopTechnological.ServicePage.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ShopTechnological.ServicePage.domain.SanPham;
import com.ShopTechnological.ServicePage.domain.SanPhamMuaNhieu;
import com.ShopTechnological.ServicePage.reponsitory.SanPhamMuaNhieuRepository;
import com.ShopTechnological.ServicePage.reponsitory.SanPhamRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class SanPhamServiceImp implements SanPhamService{
	private final SanPhamRepository sanPhamRepository;
	private final SanPhamMuaNhieuRepository sanPhamMuaNhieuRepository;
	@Override
	public List<SanPham> toanBoSanPham() {
		return sanPhamRepository.findAll();
	}

	@Override
	public SanPham chonSanPham(String ten) {
		SanPham sanPham=sanPhamRepository.findByTen(ten).get();
		if (sanPham.getSoluongconlai() >=1) {
			return sanPham;
		}
		return null;
	}
	
	@Override
	public void capNhatSoLuong(long id,int soluong) {
		Optional<SanPham> sanpham= sanPhamRepository.findById(id);
		SanPham newSanPham=sanpham.get();
		if (newSanPham.getSoluongconlai() >=1 ) {
			newSanPham.setSoluongnhap(newSanPham.getSoluongconlai()-soluong);
			sanPhamRepository.save(newSanPham);
		}else { 
			log.info("Infor  : ----So Luong San Pham Da Het");
		}
	}

	@Override
	public List<SanPham> sanPhamHot() {
	     int thoigianmoi=LocalDateTime.now().getSecond();
	     List<SanPham> sanList=new ArrayList<SanPham>();
	     if (sanPhamMuaNhieuRepository.findAll().size() < 1) {
	    	 
				sanPhamRepository.SlBanGiamDan().stream()
				   .forEach(s ->{
					   SanPhamMuaNhieu sanPhamMuaNhieu=new SanPhamMuaNhieu();
					   sanPhamMuaNhieu.setSanPham(s);
					   sanPhamMuaNhieu.setThoigiancu(LocalDateTime.now());
					   sanPhamMuaNhieuRepository.save(sanPhamMuaNhieu);
				   });
				 sanPhamMuaNhieuRepository.topSanPhamHot().stream()
		          .forEach(s ->{
		        	  sanList.add(s.getSanPham());
		          });
		    	 
	    	 
	     }else {
	    	 sanPhamMuaNhieuRepository.topSanPhamHot().stream()
	          .forEach(s ->{
	        	  sanList.add(s.getSanPham());
	          });
	    	 
	    	 int thoigiancu =sanPhamMuaNhieuRepository.findAll().get(0).getThoigiancu().getSecond();
	    	 if (thoigianmoi - thoigiancu >= 24*60*60) {
				    // ---- reset
			    	sanPhamMuaNhieuRepository.deleteAll();
					sanPhamMuaNhieuRepository.resetId();
					// --- them tat ca san pham
					sanPhamRepository.SlBanGiamDan().stream()
					   .forEach(s ->{
						   SanPhamMuaNhieu capnhat=new SanPhamMuaNhieu();
						   capnhat.setSanPham(s);
						   capnhat.setThoigiancu(LocalDateTime.now());
						   sanPhamMuaNhieuRepository.save(capnhat);
					   });
					  
					
				      return sanList;
			      }
	     }
		 return sanList;
	}

	@Override
	public List<SanPham> sanPhamMoi() {
		
		return sanPhamRepository.findAll(PageRequest.
				of(0,5
						,Sort.by(Sort.Direction.DESC,"thoigiannhap")));
	}

	@Override
	public void themSanPham(SanPham sanPham) {
		Optional<SanPham> sanPhamCu=sanPhamRepository.findByTen(sanPham.getTen());
		if (sanPhamCu.isPresent()) {
			sanPham.setId(sanPhamCu.get().getId());
			sanPham.setSoluongconlai(sanPhamCu.get().getSoluongconlai()+sanPham.getSoluongconlai());
			sanPham.setSoluongnhap(sanPhamCu.get().getSoluongnhap()+sanPham.getSoluongnhap());
		}else {
			sanPham.setThoigiannhap(LocalDateTime.now());
			log.info("San Pham Duoc Luu tru : "+sanPham);
			sanPhamRepository.save(sanPham);
		}
	}

	@Override
	public void xoaSanPham(long id) {
		sanPhamRepository.deleteById(id);
		sanPhamRepository.resetId();
	}
	
}
