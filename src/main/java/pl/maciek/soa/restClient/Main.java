package pl.maciek.soa.restClient;

import pl.maciek.soa.restClient.model.Student;
import pl.maciek.soa.restClient.services.restClientService;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Student s1 = new Student("a", "b", "c", Arrays.asList("1", "2"));
        Student s2 = new Student("aUPDATED", "b", "c", Arrays.asList("1", "2"));
        s2.setId(4);
        restClientService.postStudent(s1);
        Student s = restClientService.getStudent(2);
        List<Student> students = restClientService.getStudents();
        Boolean updateStatus = restClientService.updateStudent(s2);
        Boolean deletionStatus = restClientService.deleteStudent(10);

        //List<Student> students2 = restClientService.getStudents();
        Boolean deletionAuthorizedStatus = restClientService.authorizedDeleteStudent(10);
        System.out.println("OK");
    }
}
