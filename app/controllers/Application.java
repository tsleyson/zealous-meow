package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Person;
import play.*;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result getAll() {
        return ok(Json.toJson(Person.getAll()));
    }

    public static Result delete(Long id)
    {
        Result result;
        Person person = Person.getById(id);
        if (person != null)
        {
            person.delete();
            result = ok(person.name + " deleted");
        }
        else
        {
            result = notFound(String.format("Person with ID [%d] not found", id));
        }
        return result;
    }

    public static Result create()
    {
        JsonNode json = request().body().asJson();
        Person person = Json.fromJson(json, Person.class);
        person.save();
        return ok(Json.toJson(person));
    }

    public static Result jsRoutes() {
        response().setContentType("text/javascript");
        return ok(Routes.javascriptRouter("appRoutes",
                routes.javascript.Application.getAll(),
                routes.javascript.Application.delete(),
                routes.javascript.Application.create()));
    }
}
