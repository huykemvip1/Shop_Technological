package com.ShopTechnological.ServicePage.reponsitory;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ShopTechnological.ServicePage.domain.SanPham;

@Transactional
public interface SanPhamRepository extends CrudRepository<SanPham, Long>{
	List<SanPham> findAll();
	Optional<SanPham> findByTen(String ten);
	@Query( value = "select * from SanPham s "
			+ "group by s.ten "
			+ "order by s.soluongnhap - s.soluongconlai desc ",nativeQuery = true)
	List<SanPham> SlBanGiamDan();
	@Query("select s from SanPham s")
	List<SanPham> findAll(PageRequest pageRequest);
	@Modifying
	@Query(nativeQuery = true,value = "alter table sanpham auto_increment=0")
	void resetId();
}
