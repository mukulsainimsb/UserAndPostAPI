package com.api.play.play_with_api.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;
import com.api.play.play_with_api.Userr;


@Service
public class UserService {
	
	static List<Userr> lists=new ArrayList<>();
	 static int count=0;
	static {
		lists.add(new Userr(++count,"Mukul",LocalDate.now().minusYears(12)));
		lists.add(new Userr(++count,"saini",LocalDate.now().minusYears(18)));
		lists.add(new Userr(++count,"Vansh",LocalDate.now().minusYears(17)));
		lists.add(new Userr(++count,"naman",LocalDate.now().minusYears(40)));
		lists.add(new Userr(++count,"varinder",LocalDate.now().minusYears(25)));
	}
	public List<Userr> getAll() {
		return lists;
	}
	public Userr getOne(int id) {
	Predicate<? super Userr> predicate= user -> user.getId()==id;
	
	return lists.stream().filter(predicate).findFirst().orElse(null);
	 
	}
	public void deleteOne(int id) {
		Predicate<? super Userr> predicate=user->user.getId()==id;
		lists.removeIf(predicate);
	}
	public Userr postOne(Userr user) {
	Userr userr=new Userr(++count,user.getName(),user.getBirthDate());
	lists.add(user);
	return userr;
	}
}
