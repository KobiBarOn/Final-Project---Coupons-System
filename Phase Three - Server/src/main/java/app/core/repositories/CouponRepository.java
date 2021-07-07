package app.core.repositories;

import java.sql.Date;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import app.core.entities.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

	Coupon getById(Long id);

	void deleteByCompanyId(Long id);
	
	Coupon findByTitleIgnoreCase (String title);
	
	List<Coupon> findByEndDateBefore(Date endDate);
	
	List<Coupon> findByCompanyId (Sort sort, Long id);
	
	List<Coupon> findByCustomersId (Sort sort, Long id);
	
}