package validator;

import dto.CreateUserDto;
import entity.Gender;
import util.LocalDateFormatter;

public class CreateUserValidator implements Validator<CreateUserDto> {
    private static final CreateUserValidator INSTANCE = new CreateUserValidator();
    @Override
    public ValidationResult isValid(CreateUserDto object) {

        ValidationResult validationResult = new ValidationResult();

        if(!LocalDateFormatter.isValid(object.getBirthday())){
            validationResult.add(Error.of("invalid.birthday" , "Birthday is invalid"));
        }
        if(object.getGender() == null || Gender.valueOf(object.getGender()) == null){
            validationResult.add(Error.of("invalid.gender" , "Gender is invalid"));
        }

        return validationResult;
    }

    public static CreateUserValidator getInstance(){
        return INSTANCE;
    }
}
