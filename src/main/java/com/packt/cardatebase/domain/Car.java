package com.packt.cardatebase.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Car {
	//기본키는 @Id 어노테이션으로 지정 한다. 
	//Identity 
	@Id
	//기본키 생성 전략 Id값이 자동으로 생성 되도록 지정 GeneratedValue
	@GeneratedValue(strategy=GenerationType.AUTO) //시퀀스와는 다르다.
	private long id;
	private String brand, model, color, registerNumber;
	@Column(name="`year`")//''이 아니라 `` 이다 
	private int year;
	private int price;
	//FetchType은 데이터베이스에서 데이터를 검색하는 전략을 정의 한다
	//즉시 검개 : EAGER, 자연검색 :LAZy --> 지연검색은 
	//데이터 베이스에서 해당 컬럼을 검색하면 필요할 떄에 해당 컬럼과
	//연결된 값을 검색한다.
	//대다관계에서는 LAZY가 기본 값이다.
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn()
	private Owner owner;
	//열의 이름, 열의 길이, null허용 여부(not null과 같다) 
//	@Column(name="exdesc", nullable=false, length=512)
//	private String desc;
	
	//생성자 
	public Car() {} 
	public Car(String brand, String model, String color,
			String registerNumber, int year, int price, Owner owner) {
		super();
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.registerNumber = registerNumber;
		this.year = year;
		this.price = price;
		this.owner = owner;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getRegisterNumber() {
		return registerNumber;
	}
	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}
