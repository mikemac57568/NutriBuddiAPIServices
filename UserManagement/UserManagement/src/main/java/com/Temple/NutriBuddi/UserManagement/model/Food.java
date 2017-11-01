package com.Temple.NutriBuddi.UserManagement.model;

import com.Temple.NutriBuddi.UserManagement.model.Eats;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Food {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	
	@Column(unique = true)
	private String foodName;

	private String servingUnit;

	private Integer calories;

	private Integer protein;

	private Integer totalFat;

	private Integer satFat;

	private Integer transFat;

	private Integer carbohydrates;

	private Integer sodium;

	private Integer sugar;

	private Integer fiber;

	private Integer vitaminC;

	private Integer vitaminD;

	private Integer iron;

	private Integer magnesium;

	private Integer phospherous;

	private Integer potassium;

	private Integer zinc;

	@JsonBackReference
	@OneToMany(mappedBy = "food")
    private List<Eats> eats;

	@OneToMany(mappedBy="food")
	private List<ObjectImage> objectImages;

	public Food(){}

	public Food(String foodName, String servingUnit, Integer calories, Integer protein, Integer totalFat,
				Integer satFat, Integer transFat, Integer carbohydrates, Integer sodium, Integer sugar,
				Integer fiber, Integer vitaminC, Integer vitaminD, Integer iron, Integer magnesium,
				Integer phospherous, Integer potassium, Integer zinc) {
		this.foodName = foodName;
		this.servingUnit = servingUnit;
		this.calories = calories;
		this.protein = protein;
		this.totalFat = totalFat;
		this.satFat = satFat;
		this.transFat = transFat;
		this.carbohydrates = carbohydrates;
		this.sodium = sodium;
		this.sugar = sugar;
		this.fiber = fiber;
		this.vitaminC = vitaminC;
		this.vitaminD = vitaminD;
		this.iron = iron;
		this.magnesium = magnesium;
		this.phospherous = phospherous;
		this.potassium = potassium;
		this.zinc = zinc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getServingUnit() {
		return servingUnit;
	}

	public void setServingUnit(String servingUnit) {
		this.servingUnit = servingUnit;
	}

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public Integer getProtein() {
		return protein;
	}

	public void setProtein(Integer protein) {
		this.protein = protein;
	}

	public Integer getTotalFat() {
		return totalFat;
	}

	public void setTotalFat(Integer totalFat) {
		this.totalFat = totalFat;
	}

	public Integer getSatFat() {
		return satFat;
	}

	public void setSatFat(Integer satFat) {
		this.satFat = satFat;
	}

	public Integer getTransFat() {
		return transFat;
	}

	public void setTransFat(Integer transFat) {
		this.transFat = transFat;
	}

	public Integer getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(Integer carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	public Integer getSodium() {
		return sodium;
	}

	public void setSodium(Integer sodium) {
		this.sodium = sodium;
	}

	public Integer getSugar() {
		return sugar;
	}

	public void setSugar(Integer sugar) {
		this.sugar = sugar;
	}

	public Integer getFiber() {
		return fiber;
	}

	public void setFiber(Integer fiber) {
		this.fiber = fiber;
	}

	public Integer getVitaminC() {
		return vitaminC;
	}

	public void setVitaminC(Integer vitaminC) {
		this.vitaminC = vitaminC;
	}

	public Integer getVitaminD() {
		return vitaminD;
	}

	public void setVitaminD(Integer vitaminD) {
		this.vitaminD = vitaminD;
	}

	public Integer getIron() {
		return iron;
	}

	public void setIron(Integer iron) {
		this.iron = iron;
	}

	public Integer getMagnesium() {
		return magnesium;
	}

	public void setMagnesium(Integer magnesium) {
		this.magnesium = magnesium;
	}

	public Integer getPhospherous() {
		return phospherous;
	}

	public void setPhospherous(Integer phospherous) {
		this.phospherous = phospherous;
	}

	public Integer getPotassium() {
		return potassium;
	}

	public void setPotassium(Integer potassium) {
		this.potassium = potassium;
	}

	public Integer getZinc() {
		return zinc;
	}

	public void setZinc(Integer zinc) {
		this.zinc = zinc;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	
	
}
	
