package models;

import play.db.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Person extends Model
{
    private static Finder FIND = new Finder<>(Long.class, Person.class);
															
    @Id
    public Long id;
	
    public String name;
	
    public static List getAll()
    {
        return FIND.all();
    }
	
    public static Person getById(Long id)
    {
        return (Person)FIND.byId(id);
    }
}
