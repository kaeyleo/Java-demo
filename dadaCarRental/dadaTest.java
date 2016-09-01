package com.dadaCarRental;
import java.util.Scanner;

public class dadaTest {
	private static int carNum, days, price, passengerNum, goodsNum;
	public static void main(String[] args) {
		//初始化系统车辆 Car:Array[index] -> new PassengerCar(parameters);
		Car[] carList = {
				new PassengerCar(1,"奥迪A4",500,4),
				new PassengerCar(2,"马自达6",400,4),
				new Pickup(3, "皮卡雪6", 450, 4, 2),
				new PassengerCar(4, "金龙", 800, 20),
				new GoodsCar(5, "松花江", 400, 4),
				new GoodsCar(6, "依维柯", 1000, 20)	
		};
		//系统开始提示
		System.out.println("-----------欢迎使用答答租车系统-----------");
	
		while (true) {
			System.out.println("\n\n\t您是否要租车？\n\t[1]是\t[0]否\n");	
			try {
				Scanner sInput = new Scanner(System.in);
				int userOpt = sInput.nextInt();
				
				if (userOpt == 0) {
					System.out.print("退出程序");
					System.exit(0);
				}else if (userOpt == 1) {
					break;
				}else if( !( userOpt==0 && userOpt==1 ) ){
					System.out.print("输入0或1，请重新尝试。。。");
					continue;
				}

			} catch (Exception e) {
				System.out.print("请输入合法字符，重新尝试。。。");
			}
		}
				
		//进入程序
		System.out.println("-----------租车价目表一览-----------\n序号\t品牌\t租金\t  容量");
		//**************显示可租用车型信息******************
		for (int i = 0; i < carList.length; i++) {
			carList[i].printInfo();
		}
		//声明 用户选择的车辆列表
		Car[] selectedList;
		//声明 价格
		price = 0;	
		//**************用户选择租用车辆******************
		while (true) {
			try {
				//设定租车数量
				System.out.print("\n输入您租车的数量：");
				Scanner carNumInput = new Scanner(System.in);
				carNum = carNumInput.nextInt();
				
				if (carNum==0) {
					System.out.println("不能为0");
					continue;
				}else{
					//选择具体车辆
					selectedList = new Car[carNum];
					for(int i = 0; i < carNum; i++){
						System.out.print("请输入第"+(i+1)+"辆车的序号：");
						Scanner perCarInput = new Scanner(System.in);
						int id = perCarInput.nextInt();
						//将选择的车辆添加到选择列表
						selectedList[i] = carList[id-1];
						//计算单位时间的租车总价
						price+=selectedList[i].getPrice();
					}
					break;
				}
			} catch (Exception e) {
				System.out.println("输入不合法，请重新尝试。。。");
			}
		}
		//**************用户设置租车天数******************
		while (true) {
			try {
				//用户输入租车天数
				System.out.print("请输入租车天数：");
				Scanner daysInput = new Scanner(System.in);
				days = daysInput.nextInt();
				if (days==0) {
					System.out.println("不能为0");
					continue;
				}else{
					//租赁总价
					price *= days;
					break;
				}
			} catch (Exception e) {
				System.out.println("输入不合法，请重新尝试。。。");
			}
		}
		
		//**************输出账单******************
		System.out.println("-----------您的账单-----------");
		//声明载客人数和载重
		passengerNum = 0;
		goodsNum = 0;
		
		for(int i = 0; i < carNum; i++){
			if (selectedList[i] instanceof PassengerCar || selectedList[i] instanceof Pickup) {	
				passengerNum += selectedList[i].getBusload();
			}
			if (selectedList[i] instanceof GoodsCar || selectedList[i] instanceof Pickup) {
				goodsNum += selectedList[i].getGoodsload();
			}
			System.out.println(selectedList[i].getBrand() + "\t" + selectedList[i].getPrice()+"元/天");
		}
		
		System.out.println("\n可载客" + passengerNum + "人\t载货" + goodsNum  + "吨\n" + days + "天租车总价 "+ price + " 元");	
		
	}
}

abstract class Car{
	//声明汽车类的参数
	public int id, price, busload;
	public double goodsload;
	public String brand;
	//获取汽车唯一编号
	public int getId() {
		return id;
	}
	public String info() {
		// TODO Auto-generated method stub
		return null;
	}
	//获取单位时间的租赁价格
	public int getPrice() {
		return price;
	}
	//获取载客数
	public int getBusload() {
		return busload;
	}
	//获取载货量
	public double getGoodsload() {
		return goodsload;
	}
	//获取汽车品牌
	public String getBrand() {
		return brand;
	}
	public abstract void printInfo();
}

//客车
class PassengerCar extends Car{
	public PassengerCar (int id, String brand, int price, int busload) {
		this.id = id;
		this.brand = brand;
		this.price = price;
		this.busload = busload;
	}
	@Override
	public void printInfo() {
		System.out.println(this.id + "\t" + this.brand + "\t" + this.price + "元/天   " + this.busload +"人");
	}
}

//货车
class GoodsCar extends Car{
	public GoodsCar (int id, String brand, int price, double goodsload) {
		this.id = id;
		this.brand = brand;
		this.price = price;
		this.goodsload = goodsload;
	}
	@Override
	public void printInfo() {
		System.out.println(this.id + "\t" + this.brand + "\t" + this.price + "元/天   " + this.goodsload +"吨");
	}
}

//皮卡
class Pickup extends Car{
	public Pickup (int id, String brand, int price, int busload, double goodsload) {
		this.id = id;
		this.brand = brand;
		this.price = price;
		this.busload = busload;
		this.goodsload = goodsload;
	}
	@Override
	public void printInfo() {
		System.out.println(this.id + "\t" + this.brand + "\t" + this.price + "元/天   " + this.busload + "人  " + this.goodsload +"吨");
	}
}

