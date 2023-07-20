package com.expensetracker.personal.entity;

import java.io.Serializable;
import java.util.Objects;

public class PaymentmodeId implements Serializable {
private String paycode;
private String user;


public PaymentmodeId() {
	super();
}
public PaymentmodeId(String paycode, String uname) {
	super();
	this.paycode = paycode;
	this.user = uname;
}
@Override
public int hashCode() {
	return Objects.hash(paycode, user); 
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	PaymentmodeId other = (PaymentmodeId) obj;
	return Objects.equals(paycode, other.paycode) && Objects.equals(user, other.user);
}

}