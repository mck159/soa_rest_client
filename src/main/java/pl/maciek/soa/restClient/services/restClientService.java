package pl.maciek.soa.restClient.services;


import org.apache.http.util.EntityUtils;
import pl.maciek.soa.restClient.model.Student;
import pl.maciek.soa.restClient.model.Student;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import javax.ws.rs.GET;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by maciek on 16.06.15.
 */
public class restClientService {
    final private static String WEBSERVICE_URL = "http://localhost:8080/lab2-web-1.0/rest";
    final private static ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    final private static ObjectMapper om = new ObjectMapper();
    final private static Client client = ClientBuilder.newClient();

    static public void postStudent(Student student) {
        WebTarget target = client.target(String.format("%s/%s", WEBSERVICE_URL, "student"));

        try {
            target.request().build("POST", Entity.json(ow.writeValueAsString(student))).invoke().readEntity(String.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public Boolean updateStudent(Student student) {
        WebTarget target = client.target(String.format("%s/%s/%s", WEBSERVICE_URL, "student", student.getId()));

        try {
            Invocation invocation = target.request().build("PUT", Entity.json(ow.writeValueAsString(student)));
            Response response = invocation.invoke();
            response.readEntity(String.class);
            return response.getStatusInfo() == Response.Status.OK;
        } catch (IOException e) {
            return false;
        }
    }
    static public Student getStudent(int id) {
        WebTarget target = client.target(String.format("%s/%s/%s", WEBSERVICE_URL, "student", id));
        Invocation invocation = target.request().accept(MediaType.APPLICATION_JSON).build("GET");
        Response response = invocation.invoke();
        if(response.getStatusInfo() != Response.Status.OK) {
            return null;
        }
        String responseString = response.readEntity(String.class);
        Student s = null;
        try {
            return om.readValue(responseString, Student.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static public List<Student> getStudents() {
        WebTarget target = client.target(String.format("%s/%s", WEBSERVICE_URL, "students"));
        Invocation invocation = target.request().accept(MediaType.APPLICATION_JSON).build("GET");
        Response response = invocation.invoke();
        if(response.getStatusInfo() != Response.Status.OK) {
            return null;
        }
        String responseString = response.readEntity(String.class);
        try {
            return Arrays.asList(om.readValue(responseString, Student[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static public Boolean deleteStudent(int id) {
        WebTarget target = client.target(String.format("%s/%s/%s", WEBSERVICE_URL, "student", id));
        Invocation invocation = target.request().build("DELETE");
        Response response = invocation.invoke();
        response.readEntity(String.class);
        return response.getStatusInfo() == Response.Status.OK;
    }

    static public Boolean authorizedDeleteStudent(int id) {
        WebTarget target = client.target(String.format("%s/%s", WEBSERVICE_URL, "auth"));
        Invocation invocation = target.request().header("Auth", "haslo").build("POST");
        invocation.invoke().close();
        WebTarget targetDelete = client.target(String.format("%s/%s/%s", WEBSERVICE_URL, "student", id));
        Invocation invocationDelete = targetDelete.request().build("DELETE");
        Response response = invocationDelete.invoke();
        response.readEntity(String.class);
        return response.getStatusInfo() == Response.Status.OK;
    }
}
