
package com.expensetracker.personal.repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.expensetracker.personal.entity.ExpenditureEntity;

public interface ExpenditureRepo extends JpaRepository<ExpenditureEntity, Integer> {

	List<ExpenditureEntity> findByCategory_CatcodeAndPayment_User_uname(String catcode, String string, PageRequest pageRequest);

	List<ExpenditureEntity> findByPayment_User_unameAndPayment_Paycode(String string, String code, PageRequest pageRequest);

	List<ExpenditureEntity> findByPayment_User_unameAndSpentonBetween(String uname,Date d1, Date d2,
			PageRequest of);

	List<ExpenditureEntity> findByTagsContainingAndPayment_User_uname(String tag, String user);
	
	List<ExpenditureEntity> findTop3ByPayment_User_unameOrderByAmountDesc(String string);

	@Query("select e.category.catcode , sum(e.amount) from ExpenditureEntity e where month(spenton)=:month and e.payment.user.uname=:uname group by e.category.catcode")
	List<String> totalAmount(@Param("month") int month,@Param("uname") String uname);
	
	
	ExpenditureEntity findByIdAndPayment_User_Uname(int id, String user);

}
