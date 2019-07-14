package com.przemo.rentcar.users;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Supervisor")
public class Supervisor extends Administration
{

}
