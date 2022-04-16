package com.ShopTechnological.ServicePage.reponsitory;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ShopTechnological.ServicePage.domain.SanPhamBan;

@Transactional
public interface SanPhamBanRepository extends CrudRepository<SanPhamBan, Long>{
	List<SanPhamBan> findAll();
	
	@Modifying
	@Query(nativeQuery = true, value = "insert into sanphamban(id_sanpham,id_account_user,ngayban,tennguoiban) "
			+ "value(:id_sanpham,:id_account_user,:ngayban,:tennguoiban)")
	
	void themDuLieu(@Param("id_sanpham") long id_sanpham,
			@Param("id_account_user") long id_account_user,
			@Param("ngayban") LocalDateTime ngayban,
			@Param("tennguoiban") String tennguoiban);
	
	@Query(nativeQuery = true,value = "select case "
			+   "when a.username= :ten "
			+   "then true else false "
			+ "end as 'test'"
			+ "from sanpham sp,sanphamban sbp,account a "
			+ "where sp.id=sbp.id_sanpham "
			+ "and sbp.id_account_user=a.id"
			+ " having test = 1")
	List<Integer> checkTen(@Param("ten") String ten);
	
	@Query(value ="select count(sbp.id) from sanpham sp,sanphamban sbp,account a where "
			+ "sp.id=sbp.id_sanpham "
			+ "and "
			+ "sbp.id_account_user=a.id "
			+ "and "
			+ "a.username = :ten",nativeQuery = true)
	int soluong(@Param("ten") String ten);
}
