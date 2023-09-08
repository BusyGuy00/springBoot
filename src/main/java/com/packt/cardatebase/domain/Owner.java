package com.packt.cardatebase.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Owner {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ownerid;
	private String firstname, lastname;
	//cascade속성은 삭제 또는 업데이트시 현속 효과가 적용 되는 방법이다. 
	//ALL모든 작업이 연속 효과 적용이 될수 있다.
	//ex> 소유자 삭제시 연관된 자동차 레코드도 같이 삭제 제이슨을 만들어 문자를 전송 화면 
	// 제이슨 전송시 문자로 전송 되는데 
	//카 클래스와 오너클래스가 가 직렬화가 되는데 
	//오너 안에 카가 더 있기 때문에 무한 루프에 빠질 수 있어서 
	//@JsonIgnore을 넣어 준다 
	@JsonIgnore // @JsonIgnore로 직렬화?를 방지하는데 직렬화로 인해 무한 루프에 빠질 수 있다. 
	@OneToMany(cascade = CascadeType.ALL, mappedBy="owner")
	private List<Car> cars;
	
	public Owner() {}
	
	public Owner(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
	}


	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public long getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(long ownerid) {
		this.ownerid = ownerid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
}
