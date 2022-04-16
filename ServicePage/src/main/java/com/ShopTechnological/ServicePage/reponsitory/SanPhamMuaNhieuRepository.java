package com.ShopTechnological.ServicePage.reponsitory;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ShopTechnological.ServicePage.domain.SanPhamMuaNhieu;

@Transactional
public interface SanPhamMuaNhieuRepository extends CrudRepository<SanPhamMuaNhieu, Long>{
	List<SanPhamMuaNhieu> findAll();
	@Query(value = "select * from sanphammuanhieu limit 5",nativeQuery = true)
	List<SanPhamMuaNhieu> topSanPhamHot();
	@Modifying
	@Query(nativeQuery = true,value = "alter table sanphammuanhieu auto_increment=0")
	public void resetId();
}

