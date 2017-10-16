package UserValidation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import com.Temple.NutriBuddi.UserManagement.repository.UserRepository;

public class UserValidation {
	
	//response string
	//response http.status
	
	private ValidationResponse validateRegistration( String email
			,  String password
			,  String password2
			,  String userName
			,  String first
			,  String last
			,  String height
			,  String weight
			,  String age
			,  String gender
			, UserRepository ur) {
		
		ValidationResponse response;
		
		response = validateNewEmail(email, ur);
		if (!response.getResponseBody().equals("A-ok!"))
			return response;
		
		
		/*if (!password.equals("")) {
			if (password.equals(password2)) {
                user.setPassword(password);
			} else {
				return "Passwords must match";
			}
		} else {
			return "Password required";
		}

		if (!userName.equals("")) {
            user.setUserName(userName);
		} else {
			return "Username required";
		}

		if (!first.equals("")) {
            user.setFirstName(first);
		} else {
			return "First name required";
		}

		if (!last.equals("")) {
            user.setLastName(last);
		} else {
			return "Last name required";
		}

		if (!height.equals("")) {
            user.setHeight(Integer.parseInt(height));
		} else {
			return "Height required";
		}

		if (!weight.equals("")) {
            user.setWeight(Integer.parseInt(weight));
		} else {
			return "Weight required";
		}

		if (!age.equals("")) {
            user.setAge(Integer.parseInt(age));
		} else {
			return "Age required";
		}

		if (!gender.equals("")) {
            user.setGender(Integer.parseInt(gender));
		} else {
			return "Gender required";
		}*/
		
		
		return null;
	}
	
	private ValidationResponse validateNewEmail(String email, UserRepository userRepository) {
		if (!email.equals("")) {
			if (userRepository.findByEmail(email) == null) {
				return new ValidationResponse("A-ok!", HttpStatus.OK);
			} else {
				return new ValidationResponse("Email is already registered", HttpStatus.NOT_ACCEPTABLE);
			}
		} else {
			return new ValidationResponse("Valid email required", HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
