/*
package radion.app.authoshkola.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import radion.app.authoshkola.config.security.student.StudentUserDetails;
import radion.app.authoshkola.config.security.instructor.InstructorUserDetails;
import radion.app.authoshkola.model.users.Instructors;
import radion.app.authoshkola.model.users.Student;
import radion.app.authoshkola.service.InstructorServiceImpl;
import radion.app.authoshkola.service.StudentServiceImpl;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private StudentServiceImpl studentServiceImpl;
    @Autowired
    private InstructorServiceImpl instructorServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Student> studentOpt = studentServiceImpl.findByEmail(email);
        Optional<Instructors> instructorOpt = instructorServiceImpl.findByEmail(email);

        if (studentOpt.isPresent()) {
            return new StudentUserDetails(studentOpt.get());
        } else if (instructorOpt.isPresent()) {
            return new InstructorUserDetails(instructorOpt.get());
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }

}
*/
