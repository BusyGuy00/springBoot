package com.packt.cardatebase.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

//Car엔티티 클래스의 리포지토리이다. 
//ID필드 형식은 Long 타입으로 지정 되어 있다 
//가져올 클래스와 프라이머리키(기본키)의 타입을 넣어 주면 된다 <Car, Long>
@RepositoryRestResource
public interface CarRepository extends CrudRepository<Car, Long>{
	//@Query어노테이션을 이용하면 SQL문으로 쿼리를 만들 수 있다 
	//브랜드로 자동차를 검색 
	@Query("select c from Car c where c.brand = ?1")
	List<Car> findByBrand(String brand);
	@Query("select c from Car c where c.brand like %?1")
	List<Car> findByBrandEndWith(String brand);
	//http://localhost:8010/searchcars?
	//색상으로 자동차를 검색 
	List<Car> findByColor(String color);
	//년도로 자동차를 검색
	List<Car> findByYear(int year);
	//브랜드와 모델로 자동차를 검색 And사용
	List<Car> findByBrandAndModel(String brand, String model);
	//브랜드나 색상으로 자동차를 검색 Or 사용 
	List<Car> findByBrandOrColor(String brand, String color);
	//정렬하기 OrderBy필드명오름차순지정 Asc, Desc
	List<Car> findByBrandOrderByYearAsc(String brand);
}





