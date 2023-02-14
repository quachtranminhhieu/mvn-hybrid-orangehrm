package utilities;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

public class DataUtil {
	private Faker faker;
	
	public DataUtil(){
		faker = new Faker();
	}
	
	public static DataUtil getData() {
		return new DataUtil();
	}
	
	public String getFirstName() {
		return faker.name().firstName();
	}
	
	public String getLastName() {
		return faker.name().lastName();
	}
	
	public String getFullName() {
		return faker.name().fullName();
	}
	
	public String getEmailAddress() {
		return faker.internet().emailAddress();
	}
	
	public String getUserName() {
		return faker.name().username();
	}
	
	public String getPassword() {
		return faker.internet().password(9, 10);
	}
	
	public String getStrongPassword() {
		String password = "";
		String shuffledPassword = "";
		int expectedLength = 15;
		char[] upperCharacters = faker.lorem().fixedString(20).toUpperCase().replace(" ", "").replace(".", "").toCharArray();
		char[] lowerCharacters = faker.lorem().fixedString(20).toLowerCase().replace(" ", "").replace(".", "").toCharArray();
		char[] specialCharacters = new char[]{'!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '+', '=', '.', ',', ':', ';', '>', '<', '/', '?'};
		
		password += String.valueOf(upperCharacters[faker.random().nextInt(upperCharacters.length)]);
		password += String.valueOf(lowerCharacters[faker.random().nextInt(lowerCharacters.length)]);
		password += String.valueOf(specialCharacters[faker.random().nextInt(specialCharacters.length)]);
		password += String.valueOf(faker.random().nextInt(10));
		
		while (password.length() != expectedLength) {
			int randomNumber = faker.random().nextInt(1,4);
			
			if (randomNumber == 1) {
				password += String.valueOf(upperCharacters[faker.random().nextInt(upperCharacters.length)]);
			} else if (randomNumber == 2) {
				password += String.valueOf(specialCharacters[faker.random().nextInt(specialCharacters.length)]);
			} else if (randomNumber == 3) {
				password += String.valueOf(faker.random().nextInt(10));
			} else {
				password += String.valueOf(lowerCharacters[faker.random().nextInt(lowerCharacters.length)]);
			}
			
		}
		
		List<String> letters = Arrays.asList(password.split(""));
		Collections.shuffle(letters);
		for (String letter : letters) {
			shuffledPassword += letter;
		}
		
		return shuffledPassword;
	}
	
	public String getStrongPassword(int minimumLength, int maximumLength, boolean includeUppercase, boolean includeSpecial, boolean includeDigit) {
			String password = "";
			String shuffledPassword = "";
			int expectedLength = faker.number().numberBetween(minimumLength, maximumLength);
			char[] upperCharacters = {};
			char[] lowerCharacters = {};
			char[] specialCharacters = {};
			
			lowerCharacters = faker.lorem().fixedString(50).toLowerCase().replace(" ", "").replace(".", "").toCharArray();
			password += String.valueOf(lowerCharacters[faker.random().nextInt(lowerCharacters.length)]);
			
			if (includeUppercase) {
				upperCharacters = faker.lorem().fixedString(50).toUpperCase().replace(" ", "").replace(".", "").toCharArray();
				password += String.valueOf(upperCharacters[faker.random().nextInt(upperCharacters.length)]);
			}
			
			if (includeSpecial) {
				specialCharacters = new char[]{'!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '+', '=', '.', ',', ':', ';', '>', '<', '/', '?'};
				password += String.valueOf(specialCharacters[faker.random().nextInt(specialCharacters.length)]);
			}
			
			if (includeDigit) {
				password += String.valueOf(faker.random().nextInt(10));
			}
		    
			while (password.length() != expectedLength) {
		    	int randomNumber = faker.random().nextInt(1,4);
		    	
		    	if (randomNumber == 1 && includeUppercase) {
		    		password += String.valueOf(upperCharacters[faker.random().nextInt(upperCharacters.length)]);
		    	} else if (randomNumber == 2 && includeSpecial) {
		    		password += String.valueOf(specialCharacters[faker.random().nextInt(specialCharacters.length)]);
		    	} else if (randomNumber == 3 && includeDigit) {
		    		password += String.valueOf(faker.random().nextInt(10));
				} else {
					password += String.valueOf(lowerCharacters[faker.random().nextInt(lowerCharacters.length)]);
				}
		    	
			}
			
		    List<String> letters = Arrays.asList(password.split(""));
		    Collections.shuffle(letters);
		    for (String letter : letters) {
		    	shuffledPassword += letter;
		    }
		    
		    return shuffledPassword;
	}
	
	public String getGender() {
		return faker.demographic().sex();
	}
	
	public String getBirthDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(faker.date().birthday());
	}
	
	public String getPastDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(faker.date().past(365, TimeUnit.DAYS));
	}
	
	public String getFutureDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(faker.date().future(365, TimeUnit.DAYS));
	}
	
	public String getAddress() {
		return faker.address().streetAddress();
	}
	
	public String getCity() {
		return faker.address().city();
	}
	
	public String getState() {
		return faker.address().state();
	}
	
	public String getPostalCode() {
		return faker.address().zipCode();
	}
	
	public String getRandomNumber(int min, int max) {
		return String.valueOf(faker.number().numberBetween(min, max));
	}
	
	public String getRandomDigit() {
		return String.valueOf(faker.number().digit());
	}
	
	public String getRandomDouble(int maxNumberOfDecimals, int min, int max) {
		return String.valueOf(faker.number().randomDouble(maxNumberOfDecimals, min, max));
	}
}
